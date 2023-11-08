package com.ssafy.chat.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.chat.api.request.ChatPayload;

public interface ChatService {
    void saveChat(ChatPayload chatPayload) throws JsonProcessingException;
}
