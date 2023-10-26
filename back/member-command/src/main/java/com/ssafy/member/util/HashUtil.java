package com.ssafy.member.util;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class HashUtil {
    private final String SALT;

    public HashUtil(@Value("${PASSWORD_HASH_SALT}")String salt) {
        this.SALT = Base64.getEncoder().encodeToString(salt.getBytes());
    }

    public String hash(String password){
        return Hashing.sha256()
                .hashString(password+SALT, StandardCharsets.UTF_8)
                .toString();
    }
}
