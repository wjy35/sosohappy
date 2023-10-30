package com.ssafy.monster.controller.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.monster.common.exception.CustomException;
import com.ssafy.monster.common.exception.ErrorCode;
import com.ssafy.monster.domain.topic.member.After;
import com.ssafy.monster.domain.topic.member.Before;
import com.ssafy.monster.domain.topic.member.MemberUpdateTopic;
import com.ssafy.monster.service.MonsterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class KafkaConsumer {

    private final MonsterServiceImpl monsterService;
    @KafkaListener(topics = "member-command-mysql.member.member")
    public void consume(String message) {
        System.out.println("message = " + message);

        ObjectMapper objMapper = new ObjectMapper();

        try {
            MemberUpdateTopic memberUpdateTopic = objMapper.readValue(message, MemberUpdateTopic.class);
            Before before = memberUpdateTopic.before;
            After after = memberUpdateTopic.after;
            if(before.getProfileMonsterId() != after.getProfileMonsterId()){
                monsterService.updateMemberMonsterProfile(after.getMemberId(), after.getProfileMonsterId());
            }
        } catch (IOException e) {
            throw new CustomException(ErrorCode.JSON_PARSE_ERROR);
        }
    }
}
