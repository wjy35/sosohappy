package com.ssafy.monster.controller.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.monster.common.exception.CustomException;
import com.ssafy.monster.common.exception.ErrorCode;
import com.ssafy.monster.controller.kafka.event.member.MemberDTO;
import com.ssafy.monster.controller.kafka.event.member.MemberEvent;
import com.ssafy.monster.service.MonsterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class KafkaConsumer {

    private final MonsterServiceImpl monsterService;
    @KafkaListener(topics = "member-command-mysql.member.member")
    public void MemberConsume(ConsumerRecord<?,?> consumerRecord) {
        if(isEmptyEvent(consumerRecord)) return;
        String message = consumerRecord.value().toString();

        ObjectMapper objMapper = new ObjectMapper();

        try {
            MemberEvent memberUpdateEvent = objMapper.readValue(message, MemberEvent.class);

            MemberDTO before = memberUpdateEvent.getBefore();
            MemberDTO after = memberUpdateEvent.getAfter();

            if("c".equals(memberUpdateEvent.getOp())){
                monsterService.setInitialMonster(after.getMemberId());
            } else if ("d".equals(memberUpdateEvent.getOp())) {
                monsterService.deleteMemberMonsterProfile(before.getMemberId());
            }
        } catch (IOException e) {
            throw new CustomException(ErrorCode.JSON_PARSE_ERROR);
        }
    }

    @KafkaListener(topics = "fortune-cookie.use")
    public void UseConsume(ConsumerRecord<?,?> consumerRecord) {
        if(isEmptyEvent(consumerRecord)) return;
        String message = consumerRecord.value().toString();

        monsterService.updateClover(Long.parseLong(message), 1);

    }

    private boolean isEmptyEvent(ConsumerRecord<?,?> consumerRecord){
        return Optional.ofNullable(consumerRecord.value()).isEmpty();
    }
}
