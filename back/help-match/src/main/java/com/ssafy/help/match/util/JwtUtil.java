package com.ssafy.help.match.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;

@Component
public class JwtUtil {
    @Value("${jwt.salt}")
    private final String SALT;
    private final Key key;

    @Autowired
    public JwtUtil(@Value("${jwt.salt}") String SALT) {
        this.SALT = SALT;

        byte[] keyBytes = Base64.getDecoder().decode(Base64.getEncoder().encode(SALT.getBytes()));
        this.key = Keys.hmacShaKeyFor(keyBytes);
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
