package com.ssafy.member.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    private final Long EXPIRE_MIN;
    private final Key key;

    @Autowired
    public JwtUtil(@Value("${JWT_SALT}")String salt,@Value("${JWT_EXPIRE_MIN}")Long expireMin) {
        this.EXPIRE_MIN = expireMin;
        byte[] keyBytes = Base64.getDecoder().decode(Base64.getEncoder().encode(salt.getBytes()));
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    private String generateToken(Long memberId,Long expireMin){
        return Jwts.builder()
                .claim("memberId", memberId)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(new Date(System.currentTimeMillis()+expireMin))
                .compact();
    }

    public String generateAccessToken(Long memberId){
        return generateToken(memberId,this.EXPIRE_MIN);
    }

    public String generateRefreshToken(Long memberId){
        return generateToken(memberId,this.EXPIRE_MIN);
    }

    public Long getMemberIdByToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("memberId",Long.class);
    }

}

