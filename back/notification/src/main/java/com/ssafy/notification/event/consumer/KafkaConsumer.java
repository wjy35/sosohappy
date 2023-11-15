package com.ssafy.notification.event.consumer;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.ssafy.notification.cloud.openfeign.MemberOpenFeign;
import com.ssafy.notification.db.entity.MemberDeviceEntity;
import com.ssafy.notification.db.repository.MemberDeviceEntityRepository;
import com.ssafy.notification.event.dto.ChatSendEventDTO;
import com.ssafy.notification.event.dto.FortuneCookieCreateEventDTO;
import com.ssafy.notification.util.KafkaEventMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final MemberDeviceEntityRepository memberDeviceEntityRepository;
    private final FirebaseMessaging firebaseMessaging;
    private final KafkaEventMapper kafkaEventMapper;
    private final MemberOpenFeign memberOpenFeign;

    @KafkaListener(topics = "fortune-cookie.create")
    public void consumeFortuneCookieCreateEvent(ConsumerRecord<?,?> consumerRecord){
        if(isEmptyEvent(consumerRecord)) return;

        FortuneCookieCreateEventDTO eventDTO = kafkaEventMapper.toEvent(consumerRecord, FortuneCookieCreateEventDTO.class);

        notificationBySystem(eventDTO.getMemberId(),fortuneCookieMessageList[random.nextInt(fortuneCookieMessageList.length)]);
    }

    @KafkaListener(topics = "help-match.push")
    public void consumeHelpMatchPushEvent(ConsumerRecord<?,?> consumerRecord){
        if(isEmptyEvent(consumerRecord)) return;

        notificationBySystem(Long.parseLong((String) consumerRecord.value()),"새로운 도움 요청이 도착했어요!");
    }

    @KafkaListener(topics = "chat.send")
    public void consumeChatSendEvent(ConsumerRecord<?,?> consumerRecord){
        if(isEmptyEvent(consumerRecord)) return;

        ChatSendEventDTO eventDTO = kafkaEventMapper.toEvent(consumerRecord, ChatSendEventDTO.class);

        notificationByMember(
                eventDTO.getSendMemberId(),
                eventDTO.getReceiveMemberId(),
                eventDTO.getContent(),
                Map.of("timestamp",eventDTO.getTimestamp().toString())
        );
    }

    private boolean isEmptyEvent(ConsumerRecord<?,?> consumerRecord){
        return Optional.ofNullable(consumerRecord.value()).isEmpty();
    }

    private void notificationByMember(Long sendMemberId, Long receiveMemberId, String body, Map<String,String> data){
        MemberDeviceEntity memberDeviceEntity = memberDeviceEntityRepository.findByMemberId(receiveMemberId);
        if(Optional.ofNullable(memberDeviceEntity).isEmpty()) return;

        String nickname = memberOpenFeign.getMemberDto(sendMemberId).getNickname();

        Notification notification = Notification
                .builder()
                .setTitle(nickname)
                .setBody(body)
                .build();

        Message message = Message
                .builder()
                .setToken(memberDeviceEntity.getDeviceToken())
                .putAllData(data)
                .setNotification(notification)
                .build();

        send(message);
    }

    private void notificationBySystem(Long receiveMemberId, String body){
        MemberDeviceEntity memberDeviceEntity = memberDeviceEntityRepository.findByMemberId(receiveMemberId);
        if(Optional.ofNullable(memberDeviceEntity).isEmpty()) return;

        Notification notification = Notification
                .builder()
                .setTitle("소소행")
                .setBody(body)
                .build();

        Message message = Message
                    .builder()
                    .setToken(memberDeviceEntity.getDeviceToken())
                    .setNotification(notification)
                    .build();

        send(message);
    }

    private void send(Message message){
        try {
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            return;
        }
    }

    private Random random = new Random();
    private String[] fortuneCookieMessageList={
            "포춘쿠키가 도착했습니다. 행운메시지를 확인해보세요.",
            "당신의 선행에 소소행이 보답합니다!",
            "낭만 도착",
            "포춘쿠키 짠!!",
            "새로운 포춘 쿠키 츄라이~ 츄라이~",
            "포춘 쿠키가 왔어요."
    };

}
