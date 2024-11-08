package com.ecommerce.jwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.Function;

@Service
public class JWTUtil {

    private final String secret;
    private String expiration = "3600000";

    private Key key;

    public JWTUtil() {
        SecureRandom random = new SecureRandom();
        byte[] secretBytes = new byte[32];
        random.nextBytes(secretBytes);
        this.secret = Base64.getEncoder().encodeToString(secretBytes);
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public JWTUtil(String secret, String expiration) {
        this.secret = secret;
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        if (expiration != null) this.expiration = expiration;
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
    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(key).build().parseClaimsJwt(token).getBody();
    }

    public Boolean isTokenExpired(String token) {
        try {
            final Date expiration = extractExpiration(token);
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String generateToken(String userId, JWTTokenType tokenType) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userId);
        return createToken(claims, tokenType);
    }

    public String generateToken(Map<String, Object> claims, String userId, JWTTokenType tokenType) {
        if (claims == null) claims = new HashMap<>();
        claims.put("id", userId);
        return createToken(claims, tokenType);
    }

    public String generateToken(String userId, String role, JWTTokenType tokenType) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("id", userId);
        return createToken(claims, tokenType);
    }

    public String generateToken(String userId, Set<String> role, JWTTokenType tokenType) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("id", userId);
        return createToken(claims, tokenType);
    }

    private String createToken(Map<String, Object> claims,JWTTokenType tokenType) {
        long expMillis = JWTTokenType.ACCESS_TOKEN.name().equalsIgnoreCase(tokenType.name())
                ? Long.parseLong(expiration) * 1000
                : Long.parseLong(expiration) * 1000 * 5;

        final Date now = new Date();
        final Date exp = new Date(now.getTime() + expMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject((String) claims.get("id"))
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith( key)
                .compact();
    }
}
