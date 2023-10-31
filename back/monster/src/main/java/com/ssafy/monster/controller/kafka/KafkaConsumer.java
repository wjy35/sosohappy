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
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class KafkaConsumer {

    private final MonsterServiceImpl monsterService;
    @KafkaListener(topics = "member-command-mysql.member.member")
    public void consume(ConsumerRecord<String,String> consumerRecord) {
        if(isEmptyEvent(consumerRecord)) return;
        String message = consumerRecord.value();

        ObjectMapper objMapper = new ObjectMapper();

        try {
            MemberEvent memberUpdateEvent = objMapper.readValue(message, MemberEvent.class);

            MemberDTO before = memberUpdateEvent.getBefore();
            MemberDTO after = memberUpdateEvent.getAfter();

            if(memberUpdateEvent.getOp().equals("c")){
                monsterService.setInitialMonster(after.getMemberId());
            } else if(memberUpdateEvent.getOp().equals("u")) {
                if(before.getProfileMonsterId() != after.getProfileMonsterId()){
                    monsterService.updateMemberMonsterProfile(after.getMemberId(), after.getProfileMonsterId());
                }
            } else if (memberUpdateEvent.getOp().equals("d")) {
                monsterService.deleteMemberMonsterProfile(before.getMemberId());
            }
        } catch (IOException e) {
            throw new CustomException(ErrorCode.JSON_PARSE_ERROR);
        }
    }

    private boolean isEmptyEvent(ConsumerRecord<?,?> consumerRecord){
        return Optional.ofNullable(consumerRecord.value()).isEmpty();
    }
}
