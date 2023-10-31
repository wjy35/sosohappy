package com.ssafy.monster.controller.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.monster.common.exception.CustomException;
import com.ssafy.monster.common.exception.ErrorCode;
import com.ssafy.monster.domain.topic.member.MemberDTO;
import com.ssafy.monster.domain.topic.member.MemberEvent;
import com.ssafy.monster.service.MonsterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class KafkaConsumer {

    private final MonsterServiceImpl monsterService;
    @KafkaListener(topics = "member-command-mysql.member.member")
    public void consume(ConsumerRecord<String,String> consumerRecord) {
        String message = consumerRecord.value();
        System.out.println("message = " + message);

        ObjectMapper objMapper = new ObjectMapper();

        try {
            MemberEvent memberUpdateEvent = objMapper.readValue(message, MemberEvent.class);

            MemberDTO before = memberUpdateEvent.getBefore();
            MemberDTO after = memberUpdateEvent.getAfter();

            if(memberUpdateEvent.getOp().equals("c")){
                if(after == null) {
                    throw new CustomException(ErrorCode.EVENT_CONTENT_ERROR);
                }
                monsterService.setInitialMonster(after.getMemberId());
            } else if(memberUpdateEvent.getOp().equals("u")) {
                if(before == null || after == null) {
                    throw new CustomException(ErrorCode.EVENT_CONTENT_ERROR);
                }
                if(before.getProfileMonsterId() != after.getProfileMonsterId()){
                    monsterService.updateMemberMonsterProfile(after.getMemberId(), after.getProfileMonsterId());
                }
            } else if (memberUpdateEvent.getOp().equals("d")) {
                if(before == null) {
                    throw new CustomException(ErrorCode.EVENT_CONTENT_ERROR);
                }
                monsterService.deleteMemberMonsterProfile(before.getMemberId());
            }
        } catch (IOException e) {
            throw new CustomException(ErrorCode.JSON_PARSE_ERROR);
        }
    }
}
