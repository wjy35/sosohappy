package com.ssafy.chat.api.controller;

import com.ssafy.chat.api.dto.ChatPublish;
import com.ssafy.chat.api.service.ChatService;
import com.ssafy.chat.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatMessageListener implements MessageListener {

    private final ChatService chatService;

    private final ObjectSerializer objectSerializer;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        ChatPublish chatPublish = objectSerializer.deserialize(message.toString(), ChatPublish.class);

        chatService.sendForDetail(chatPublish);
        chatService.sendForList(chatPublish);
    }

}
