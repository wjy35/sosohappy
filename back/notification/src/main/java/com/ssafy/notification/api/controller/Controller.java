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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final MemberDeviceManageService memberDeviceManageService;
    private final FirebaseMessaging firebaseMessaging;

    @PostMapping("/")
    ResponseEntity<FormattedResponse> save(@RequestHeader Long memberId, @RequestBody  MemberDeviceSaveRequest memberDeviceSaveRequest){
        memberDeviceManageService.save(MemberDeviceMapper.INSTANCE.toEntity(memberId, memberDeviceSaveRequest.getDeviceToken()));

        FormattedResponse response = FormattedResponse
                .builder()
                .status("success")
                .message("SUCCESS SAVE")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
