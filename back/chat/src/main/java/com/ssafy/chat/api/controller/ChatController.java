package com.ssafy.chat.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.chat.api.mapper.ChatMapper;
import com.ssafy.chat.api.request.ChatPayload;
import com.ssafy.chat.api.request.ChatPublish;
import com.ssafy.chat.api.response.FormattedResponse;
import com.ssafy.chat.api.service.ChatServerManageService;
import com.ssafy.chat.api.service.ChatSaveService;
import com.ssafy.chat.db.entity.ChatEntity;
import com.ssafy.chat.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatSaveService chatSaveService;

    private final ChatServerManageService chatServerManageService;

    private final RedisTemplate<String, Object> redisTemplate;

    private final ChatMapper chatMapper;

    private final ObjectSerializer objectSerializer;

    @PostMapping("/chat/send")
    public ResponseEntity<?> createChatRoom(@RequestBody ChatPayload chatPayload){
        ChatEntity chatEntity = chatMapper.createRequestToEntity(chatPayload);
        try {
            chatSaveService.saveChat(chatEntity, chatPayload.getChatRoomId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ChatPublish chatPublish = chatMapper.createChatPublish(chatPayload);

        Optional.ofNullable(chatServerManageService.getChatServerIdByMemberId(chatPayload.getReceiveMemberId()))
                .ifPresentOrElse((chatServerId)->{
                            redisTemplate.convertAndSend(
                                    "1",
                                    objectSerializer.serialize(chatPublish)
                            );
                        }
                        ,()->{});

        FormattedResponse response = FormattedResponse.builder()
                .status("success")
                .message("GET HELP COUNT")
                .result("chatRoomId", chatPayload.getChatRoomId())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
