package com.ssafy.help.match.event.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.ssafy.help.match.db.repository.MemberSessionEntityRepository;
import com.ssafy.help.match.event.dto.MemberKafkaEventDTO;
import com.ssafy.help.match.util.KafkaEventMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final KafkaEventMapper kafkaEventMapper;
    private final MemberSessionEntityRepository memberSessionEntityRepository;

    @KafkaListener(topics = "member-command-mysql.member.member")
    public void consume(ConsumerRecord<?,?> consumerRecord){
        if(isEmptyEvent(consumerRecord)) return;

        MemberKafkaEventDTO memberEvent = kafkaEventMapper.toEvent(consumerRecord, MemberKafkaEventDTO.class);

        if(memberEvent.getOp().equals("c")){
            memberSessionEntityRepository.create(memberEvent.getAfter().getMemberId());
        }
    }

    private boolean isEmptyEvent(ConsumerRecord<?,?> consumerRecord){
        return Optional.ofNullable(consumerRecord.value()).isEmpty();
    }

}
