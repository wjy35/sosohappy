package com.ssafy.apigateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class JwtUtil {
    @Value("${jwt.salt}")
    private String SALT;

    @Value("${jwt.expire-min}")
    private Long EXPIRE_MIN;

    public String generateToken(Long memberId){
        Claims claims = Jwts.claims()
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_MIN));
        claims.put("memberId",memberId);

        String jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SALT.getBytes())
                .compact();

        return jwt;
    }

    public Long getMemberIdByToken(final String token){

         Object memberId = Jwts.parser()
                 .setSigningKey(SALT.getBytes())
                 .parseClaimsJws(token)
                 .getBody()
                 .get("memberId");

         return Long.parseLong(memberId.toString());
    }

}
