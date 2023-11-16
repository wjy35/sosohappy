package com.ssafy.chat.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.chat.api.request.ChatRoomCreateRequest;
import com.ssafy.chat.api.response.ChatRoomList;
import com.ssafy.chat.api.response.FormattedResponse;
import com.ssafy.chat.api.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping("/chatroom")
    public ResponseEntity<?> createChatRoom(@RequestBody ChatRoomCreateRequest chatRoomCreateRequest){
        Integer chatRoomId = chatRoomService.creatChatRoom(chatRoomCreateRequest);

        FormattedResponse response = FormattedResponse.builder()
                .status("success")
                .message("SUCCESS CREATE CHATROOM")
                .result("chatRoomId", chatRoomId)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @SubscribeMapping("/topic/{memberId}")
    public void subscribeChatRoomList(@DestinationVariable("memberId") long memberId) throws JsonProcessingException {
        List<ChatRoomList> chatRoomLists;
        try {
            chatRoomLists = chatRoomService.getChatRoomListParams(memberId);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        chatRoomService.sendChatRoomList(chatRoomLists, memberId);
    }
}
