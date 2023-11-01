package com.ssafy.category.controller.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.category.common.exception.CustomException;
import com.ssafy.category.common.exception.ErrorCode;
import com.ssafy.category.controller.kafka.event.HelpDTO;
import com.ssafy.category.controller.kafka.event.HelpEvent;
import com.ssafy.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class KafkaConsumer {

    private final CategoryService categoryService;

    @KafkaListener(topics = "help-history-command-mysql.help_history.help_history")
    public void consume(ConsumerRecord<String,String> record) {
        if(isEmptyEvent(record)) return;
        String message = record.value();

        ObjectMapper objMapper = new ObjectMapper();

        try{
            HelpEvent helpEvent = objMapper.readValue(message, HelpEvent.class);
            HelpDTO after = helpEvent.getAfter();

            categoryService.addCategoryPick(after.getToMemberId(), after.getCategoryId());

        } catch (IOException e){
            throw new CustomException(ErrorCode.JSON_PARSE_ERROR);
        }

    }

    private boolean isEmptyEvent(ConsumerRecord<?,?> consumerRecord){
        return Optional.ofNullable(consumerRecord.value()).isEmpty();
    }
}
