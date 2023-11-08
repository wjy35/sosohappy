package com.ssafy.monster.controller.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.monster.common.exception.CustomException;
import com.ssafy.monster.common.exception.ErrorCode;
import com.ssafy.monster.controller.kafka.event.help.HelpDTO;
import com.ssafy.monster.controller.kafka.event.help.HelpEvent;
import com.ssafy.monster.controller.kafka.event.member.MemberDTO;
import com.ssafy.monster.controller.kafka.event.member.MemberEvent;
import com.ssafy.monster.service.MonsterService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class KafkaConsumer {

    private final MonsterService monsterService;

    @KafkaListener(topics = "member-command-mysql.member.member")
    public void memberConsume(ConsumerRecord<String,String> consumerRecord) {
        if(isEmptyEvent(consumerRecord)) return;
        String message = consumerRecord.value();

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

    @KafkaListener(topics = "help-history-command-mysql.help_history.help_history")
    public void helpConsume(ConsumerRecord<String,String> consumerRecord) {
        if(isEmptyEvent(consumerRecord)) return;
        String message = consumerRecord.value();

        ObjectMapper objMapper = new ObjectMapper();

        try{
            HelpEvent helpEvent = objMapper.readValue(message, HelpEvent.class);
            HelpDTO after = helpEvent.getAfter();
            monsterService.updateClover(after.getFromMemberId(), after.getToMemberId(), 1);
        } catch (IOException e){
            throw new CustomException(ErrorCode.JSON_PARSE_ERROR);
        }
    }

    private boolean isEmptyEvent(ConsumerRecord<?,?> consumerRecord){
        return Optional.ofNullable(consumerRecord.value()).isEmpty();
    }
}
