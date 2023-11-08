package com.ssafy.chat.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObjectSerializer {
    private final ObjectMapper objectMapper;

    public String serialize(Object response){
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T deserialize(String object,Class<T> clazz){
        try {
            return objectMapper.readValue(object,clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T deserialize(String object, TypeReference<T> typeReference){
        try {

            return objectMapper.readValue(object,typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

