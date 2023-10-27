package com.ssafy.notification.api.controller;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.ssafy.notification.api.mapper.MemberDeviceMapper;
import com.ssafy.notification.api.request.MemberDeviceSaveRequest;
import com.ssafy.notification.api.response.FormattedResponse;
import com.ssafy.notification.api.service.MemberDeviceManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final MemberDeviceManageService memberDeviceManageService;
    private final FirebaseMessaging firebaseMessaging;

    @PostMapping("/")
    ResponseEntity<FormattedResponse> save(@RequestBody MemberDeviceSaveRequest memberDeviceSaveRequest){
        memberDeviceManageService.save(MemberDeviceMapper.INSTANCE.toEntity(memberDeviceSaveRequest));

        FormattedResponse response = FormattedResponse
                .builder()
                .status("success")
                .message("SUCCESS SAVE")
                .build();

        // Todo Test 용 임시 코드 삭제
        Notification notification = Notification
                .builder()
                .setTitle("소소행")
                .setBody("응애 나 애기 석주")
                .build();

        Message message = Message
                .builder()
                .setToken(memberDeviceSaveRequest.getDeviceToken())
                .setNotification(notification)
                .build();
        try {
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
