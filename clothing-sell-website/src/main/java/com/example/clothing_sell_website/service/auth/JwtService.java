package com.example.clothing_sell_website.service.auth;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Collections;
import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET_KEY = "Y2FzaGFzZGZhc2hkaW92Y2xvY3RlcnByb2plY3RhcGlrY2hyb3Bva2VzZWRsaGZsa21uYXZsb2RhbWZpcGVkY3J5cA==";

    // Lấy Key từ SECRET_KEY
    private Key getSigningKey() {
        byte[] keyAsBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyAsBytes);  // Dùng helper function của jjwt để tạo hmacSHA256 key
    }

    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))  // Thời gian tạo token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) // Hết hạn sau 12 giờ
                .signWith(SignatureAlgorithm.HS256, getSigningKey())  // Sử dụng HS256 để ký token
                .compact();
    }

    public String extractUsername(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Token extraction failed: " + e.getMessage(), e);  // Thêm thông báo lỗi chi tiết
        }
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .parseClaimsJws(token);  // Xác thực chữ ký của token
            return !isTokenExpired(token);  // Kiểm tra nếu token đã hết hạn chưa
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }
        return false;  // Nếu có lỗi, token không hợp lệ
    }

    public Authentication getAuthentication(String token) {
        String username = extractUsername(token);

        return new UsernamePasswordAuthenticationToken(
                username,  // principal
                null,     // credentials
                Collections.emptyList()  // authorities rỗng
        );
    }

    public boolean isTokenValid(String token) {
        try {
            return validateToken(token);  // Xác thực token
        } catch (Exception e) {
            System.out.println("Error validating token: " + e.getMessage());
            return false;
        }
    }
}
