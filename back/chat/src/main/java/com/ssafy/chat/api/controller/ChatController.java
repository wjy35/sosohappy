package com.ssafy.chat.api.controller;

import com.ssafy.chat.api.mapper.ChatMapper;
import com.ssafy.chat.api.request.ChatPayload;
import com.ssafy.chat.api.request.ChatPublish;
import com.ssafy.chat.api.response.ChatResponse;
import com.ssafy.chat.api.response.FormattedResponse;
import com.ssafy.chat.api.service.ChatServerManageService;
import com.ssafy.chat.api.service.ChatService;
import com.ssafy.chat.db.entity.ChatEntity;
import com.ssafy.chat.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    private final ChatServerManageService chatServerManageService;

    private final RedisTemplate<String, Object> redisTemplate;

    private final ChatMapper chatMapper;

    private final ObjectSerializer objectSerializer;

    @PostMapping("/chat/send")
    public ResponseEntity<?> sendChat(@RequestBody ChatPayload chatPayload){
        ChatEntity chatEntity = chatMapper.createRequestToEntity(chatPayload);

        chatService.saveChat(chatEntity, chatPayload.getChatRoomId());

        ChatPublish chatPublish = chatMapper.createChatPublish(chatPayload);

        Optional.ofNullable(chatServerManageService.getChatServerIdByMemberId(chatPayload.getReceiveMemberId()))
                .ifPresentOrElse((chatServerId)->{
                            System.out.println("chatServerId: "+chatServerId);
                            redisTemplate.convertAndSend(
                                    chatServerId,
                                    objectSerializer.serialize(chatPublish)
                            );
                        }
                        ,()->{});

        FormattedResponse response = FormattedResponse.builder()
                .status("success")
                .message("SUCCESS SEND CHAT")
                .result("chatRoomId", chatPayload.getChatRoomId())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/chat/{chatRoomId}")
    public ResponseEntity<?> getChatList(@PathVariable int chatRoomId){

        List<ChatResponse> chatResponseList = chatService.getChatList(chatRoomId);

        FormattedResponse response = FormattedResponse.builder()
                .status("success")
                .message("GET Chat List")
                .result("chatResponseList", chatResponseList)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
