package com.example.employeemanagement.security;

import com.example.employeemanagement.controller.AccessDeniedException;
import com.example.employeemanagement.dto.auth.JwtResponse;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.entity.Role;
import com.example.employeemanagement.exception.EntityNotFoundException;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.security.props.JwtProperties;
import com.example.employeemanagement.service.EmployeeService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private final UserDetailsService userDetailsService;
    private final EmployeeRepository employeeRepository;
    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public String createAccessToken(Long employeeId, String email, Role role) {
        Claims claims = Jwts.claims()
                .subject(email)
                .add("id", employeeId)
                .add("role", role.getName())
                .build();

        Instant validity = Instant.now()
                .plus(jwtProperties.getAccess(), ChronoUnit.MINUTES);

        return Jwts.builder()
                .claims(claims)
                .expiration(Date.from(validity))
                .signWith(key)
                .compact();
    }

    public String createRefreshToken(Long employeeId, String email) {
        Claims claims = Jwts.claims()
                .subject(email)
                .add("id", employeeId)
                .build();

        Instant validity = Instant.now()
                .plus(jwtProperties.getRefresh(), ChronoUnit.HOURS);

        return Jwts.builder()
                .claims(claims)
                .expiration(Date.from(validity))
                .signWith(key)
                .compact();
    }

    public JwtResponse refreshEmployeeTokens(String refreshToken) {
        if(!tokenValid(refreshToken)){
            throw new AccessDeniedException();
        }

        Long employeeId = getId(refreshToken);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException(employeeId));

        return new JwtResponse(
                employee.getId(),
                employee.getEmail(),
                createAccessToken(employeeId, employee.getEmail(), employee.getRole()),
                createRefreshToken(employeeId, employee.getEmail())
        );
    }

    public boolean tokenValid(String token) {
        Jws<Claims> claims = Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);

        return claims.getPayload()
                .getExpiration()
                .after(new Date());
    }

    private Long getId(String token) {
        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("id", Long.class);
    }

    private String getEmail(String token) {
        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Authentication getAuthentication(String token) {
        String email = getEmail(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}
