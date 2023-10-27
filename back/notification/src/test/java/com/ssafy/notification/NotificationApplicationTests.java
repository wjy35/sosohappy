package com.ssafy.notification;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.ssafy.notification.db.entity.MemberDeviceEntity;
import com.ssafy.notification.db.repository.MemberDeviceEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class NotificationApplicationTests {

    @Autowired
    FirebaseMessaging firebaseMessaging;

    @Autowired
    MemberDeviceEntityRepository memberDeviceEntityRepository;

    @Test
    void contextLoads() {

    }

    @Test
    void testNotification(){
        Notification notification = Notification
                .builder()
                .setTitle("소소행")
                .setBody("응애 나 애기 석주")
                .build();

        Message message = Message
                .builder()
                .setToken(memberDeviceEntityRepository.findByMemberId(1l).getDeviceToken())
                .setNotification(notification)
                .build();

        try {
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
