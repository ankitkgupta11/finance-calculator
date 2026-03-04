//package com.finapp.finance_calculator.security;
//
//import java.security.Key;
//import java.util.Date;
//
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//
//@Component
//public class JwtUtil {
//
//    private final String SECRET = "my_super_secure_secret_key_that_is_32_chars_long_123";
//    private final long EXPIRATION = 1000 * 60 * 60 ; // 1 hour
//
//    /**
//     * @return
//     */
//    private Key getSigningKey() {
//        return Keys.hmacShaKeyFor(SECRET.getBytes());
//    }
//
//    /**
//     * @param email
//     * @param role
//     * @return
//     */
//    public String generateToken(String email, String role) {
//        return Jwts.builder()
//                .setSubject(email)
//                .claim("role", role)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
//                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//    
//    /**
//     * @param token
//     * @return
//     */
//    public String extractUsername(String token) {
//
//        return Jwts.parserBuilder()
//                .setSigningKey(getSigningKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    
//    /**
//     * @param token
//     * @return
//     */
//    public boolean validateToken(String token) {
//        try {
//        	Jwts.parserBuilder()
//            .setSigningKey(getSigningKey())
//            .build()
//            .parseClaimsJws(token);
//        	return true;
//        } catch (JwtException e) {
//            return false;
//        }
//    }
//}
