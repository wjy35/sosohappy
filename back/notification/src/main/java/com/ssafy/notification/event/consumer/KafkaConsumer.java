package com.ssafy.notification.event.consumer;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.ssafy.notification.db.repository.MemberDeviceEntityRepository;
import com.ssafy.notification.event.dto.FortuneCookieCreateEventDTO;
import com.ssafy.notification.util.KafkaEventMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final MemberDeviceEntityRepository memberDeviceEntityRepository;
    private final FirebaseMessaging firebaseMessaging;
    private final KafkaEventMapper kafkaEventMapper;

    @KafkaListener(topics = "fortune-cookie.create")
    public void consumeFortuneCookieCreateEvent(ConsumerRecord<?,?> consumerRecord){
        if(isEmptyEvent(consumerRecord)) return;

        FortuneCookieCreateEventDTO eventDTO = kafkaEventMapper.toEvent(consumerRecord, FortuneCookieCreateEventDTO.class);

        notification(eventDTO.getMemberId(),fortuneCookieMessageList[random.nextInt(fortuneCookieMessageList.length)]);
    }

    private boolean isEmptyEvent(ConsumerRecord<?,?> consumerRecord){
        return Optional.ofNullable(consumerRecord.value()).isEmpty();
    }

    private void notification(Long memberId, String body){
        Notification notification = Notification
                .builder()
                .setTitle("소소행")
                .setBody(body)
                .build();

        Message message = Message
                .builder()
                .setToken(memberDeviceEntityRepository.findByMemberId(memberId).getDeviceToken())
                .setNotification(notification)
                .build();

        try {
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
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
