package com.ssafy.chat.api.controller;

import com.ssafy.chat.api.request.ChatRoomCreateRequest;
import com.ssafy.chat.api.response.ChatRoomListParam;
import com.ssafy.chat.api.response.FormattedResponse;
import com.ssafy.chat.api.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping("/chatroom")
    public ResponseEntity<?> createChatRoom(@RequestBody ChatRoomCreateRequest chatRoomCreateRequest){
        Integer chatRoomId = chatRoomService.creatChatRoom(chatRoomCreateRequest);

        FormattedResponse response = FormattedResponse.builder()
                .status("success")
                .message("GET HELP COUNT")
                .result("chatRoomId", chatRoomId)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/chatroom")
    public ResponseEntity<?> getChatRoomList(@RequestHeader("memberId") long memberId){
        List<ChatRoomListParam> chatRoomListParams;

        FormattedResponse response = FormattedResponse.builder()
                .status("success")
                .message("GET CHATROOM lIST")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
