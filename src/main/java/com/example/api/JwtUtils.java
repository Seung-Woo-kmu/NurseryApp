package com.example.api;

import com.example.domain.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}") // application.properties 또는 application.yml에 설정된 시크릿 키 값을 가져옵니다.
    private String secret;

    private static final Long expirationMs = 60 * 60 * 1000L * 24 * 365;

    public String generateToken(Long memberId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(memberId.toString())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Long getMemberId(String token) {
        Claims body = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token).getBody();
        return Long.parseLong(body.getSubject());
    }

}