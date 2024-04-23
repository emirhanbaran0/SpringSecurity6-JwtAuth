package com.emirhanbaran.springsecurity6jwt.service;


import com.emirhanbaran.springsecurity6jwt.messages.InvalidTokenMessages;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService {


    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration.ms}")
    private Long expiration;

    public String generateToken(String username,  Set<String> roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(expiration, ChronoUnit.MILLIS)))
                .signWith(getKey())
                .compact();
    }

    public Boolean validateToken(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey getKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.info(InvalidTokenMessages.INVALID_JWT_SIGNATURE.getDescription());
        } catch (MalformedJwtException e) {
            log.info(InvalidTokenMessages.INVALID_JWT_TOKEN.getDescription());
        } catch (ExpiredJwtException e) {
            log.info(InvalidTokenMessages.EXPIRED_JWT_TOKEN.getDescription());
        } catch (UnsupportedJwtException e) {
            log.info(InvalidTokenMessages.UNSUPPORTED_JWT_TOKEN.getDescription());
        } catch (IllegalArgumentException e) {
            log.info(InvalidTokenMessages.ILLEGAL_ARGUMENT_EXCEPTION.getDescription());
        }
        return false;
    }
}

