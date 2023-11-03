package com.ssafy.chat.api.controller;

import com.ssafy.chat.api.request.ChatRoomCreateRequest;
import com.ssafy.chat.api.response.FormattedResponse;
import com.ssafy.chat.api.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping
    public ResponseEntity<?> createChatRoom(@RequestBody ChatRoomCreateRequest chatRoomCreateRequest){
        chatRoomService.creatChatRoom(chatRoomCreateRequest);

        FormattedResponse response = FormattedResponse.builder()
                .status("success")
                .message("GET HELP COUNT")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
