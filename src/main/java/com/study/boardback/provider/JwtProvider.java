package com.study.boardback.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    public String create(String email){
        Date expireDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS)); // 1시간
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .compact();
    }

    public String validate(String jwt){
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(jwt).getBody();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return claims.getSubject();
    }
}
