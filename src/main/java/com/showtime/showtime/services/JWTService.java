package com.showtime.showtime.services;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTService {
    private String SECRET_KEY = "KhansaarKaSalaarSalaarDevarathaRaisar";

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    
    public String createToken(Map<String, Object> claims, String subject) {
        return Jwts
                    .builder()
                    .claims(claims)
                    .subject(subject)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 8_64_00_00_000L))
                    .signWith(getSecretKey())
                    .compact();

    }

    public Map<String,Object> extractAllClaims(String token) {
        Claims claims = (Claims) Jwts.parser().verifyWith(getSecretKey()).build().parse(token).getPayload();
        return claims;
    }

    public Object extractClaim(String token, String field) {
        Map<String,Object> claims = extractAllClaims(token);
        return claims.get(field);
    }

    public boolean isValidToken(String token) {
        Long expiryInSec = (Long) extractClaim(token, "exp");
        Date expiration = new Date(expiryInSec * 1000);
        boolean isTokenValid = expiration != null && expiration.after(new Date());
        return isTokenValid;
    }
}
