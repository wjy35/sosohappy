package com.ssafy.apigateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.salt}")
    private final String SALT;
    @Value("${jwt.expire-min}")
    private final Long EXPIRE_MIN;

    private final Key key;

    @Autowired
    public JwtUtil(
            @Value("${jwt.salt}") String SALT,
            @Value("${jwt.expire-min}") Long EXPIRE_MIN) {
        this.SALT = SALT;
        this.EXPIRE_MIN = EXPIRE_MIN;

        byte[] keyBytes = Base64.getDecoder().decode(Base64.getEncoder().encode(SALT.getBytes()));
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Long memberId){
        return Jwts.builder()
                .claim("memberId", memberId)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_MIN))
                .compact();
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
