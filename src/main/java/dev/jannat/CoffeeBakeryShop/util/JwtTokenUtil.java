package dev.jannat.CoffeeBakeryShop.util;

import io.jsonwebtoken.*;
import java.util.Date;

public class JwtTokenUtil {

    private String jwtSecret = "brewandbite";  // Keep this secret key safe

    // JWT Token Expiration Time (1 hour)
    private long jwtExpiration = 3600000L;

    // Method to create a JWT token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())  // Set the issue date
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))  // Set expiration time
                .signWith(SignatureAlgorithm.HS512, jwtSecret)  // Sign with the secret key
                .compact();  // Generate the token
    }

    // Method to get Claims from the JWT token
    public Claims getClaimsFromToken(String token) throws JwtException {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)  // Set signing key to validate the token
                    .parseClaimsJws(token)  // Parse JWT to get claims
                    .getBody();  // Return claims
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new JwtException("Invalid or expired token", e);  // Handle exceptions
        }
    }

    // Method to get the username (subject) from the token
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();  // Extract username from the claims
    }

    // Method to check if the token has expired
    public boolean isTokenExpired(String token) {
        return getClaimsFromToken(token).getExpiration().before(new Date());  // Compare expiration date with current date
    }

    // Method to validate the token
    public boolean validateToken(String token, String username) {
        return (username.equals(getUsernameFromToken(token)) && !isTokenExpired(token));  // Validate token by checking username and expiration
    }
}
