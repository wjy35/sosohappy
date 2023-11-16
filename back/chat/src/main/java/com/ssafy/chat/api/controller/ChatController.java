package com.ssafy.chat.api.controller;

import com.ssafy.chat.api.mapper.ChatMapper;
import com.ssafy.chat.api.request.ChatRequest;
import com.ssafy.chat.api.dto.ChatPublish;
import com.ssafy.chat.api.response.ChatResponse;
import com.ssafy.chat.api.response.FormattedResponse;
import com.ssafy.chat.api.service.ChatServerManageService;
import com.ssafy.chat.api.service.ChatService;
import com.ssafy.chat.db.entity.ChatEntity;
import com.ssafy.chat.event.dto.ChatSendEventDTO;
import com.ssafy.chat.event.producer.KafkaEventProducer;
import com.ssafy.chat.event.producer.KafkaEventTopic;
import com.ssafy.chat.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
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

    private final KafkaEventProducer kafkaEventProducer;

    @PostMapping("/chat/send")
    public ResponseEntity<?> sendChat(@RequestBody ChatRequest chatRequest){
        ChatEntity chatEntity = chatMapper.createRequestToEntity(chatRequest);

        chatService.saveChat(chatEntity, chatRequest.getChatRoomId());

        ChatPublish chatPublish = chatMapper.createChatPublish(chatRequest);

        Optional.ofNullable(chatServerManageService.getChatServerIdByMemberId(chatRequest.getReceiveMemberId()))
                .ifPresentOrElse((chatServerId)->{
                            redisTemplate.convertAndSend(
                                    chatServerId,
                                    objectSerializer.serialize(chatPublish)
                            );
                        }
                        ,()->{
                            chatService.sendForSelf(chatPublish);

                            ChatSendEventDTO chatSendEventDTO = ChatSendEventDTO.builder()
                                    .sendMemberId(chatRequest.getSendMemberId())
                                    .receiveMemberId(chatRequest.getReceiveMemberId())
                                    .content(chatRequest.getContent())
                                    .timestamp(chatRequest.getTimestamp())
                                    .build();

                            kafkaEventProducer.produce(KafkaEventTopic.CHAT_NOTIFICATION,chatSendEventDTO);
                        });
        ChatResponse chatResponse = ChatResponse.builder()
                .memberId(chatPublish.getSendMemberId())
                .content(chatPublish.getContent())
                .timestamp(chatPublish.getTimestamp()).build();

        FormattedResponse response = FormattedResponse.builder()
                .status("success")
                .message("SUCCESS SEND CHAT")
                .result("chatRoomId", chatRequest.getChatRoomId())
                .result("chat", chatResponse)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @SubscribeMapping("/topic/{memberId}/{chatRoomId}")
    public void subscribeChatRoom(@DestinationVariable("memberId") long memberId,
                                  @DestinationVariable("chatRoomId") int chatRoomId){

        List<ChatResponse> chatResponseList = chatService.getChatList(chatRoomId);
        chatService.sendChatList(chatResponseList, memberId, chatRoomId);
    }

}
