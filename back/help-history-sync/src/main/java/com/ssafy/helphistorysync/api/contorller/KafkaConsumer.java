package com.ssafy.helphistorysync.api.contorller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.helphistorysync.api.service.HelpHistoryService;
import com.ssafy.helphistorysync.exception.CustomException;
import com.ssafy.helphistorysync.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class KafkaConsumer {

    private final HelpHistoryService helpHistoryService;

    @KafkaListener(topics = "help-history-command-mysql.help_history.help_history")
    public void consume(ConsumerRecord<String,String> message) {
        if(isEmptyEvent(message)) return;

        try {
            helpHistoryService.addHelpHistory(message);
        } catch (JsonProcessingException e) {
            throw new CustomException(ErrorCode.CLASS_CAST_ERROR);
        }
        System.out.println("message = " + message.value());
    }
    private boolean isEmptyEvent(ConsumerRecord<?,?> consumerRecord){
        return Optional.ofNullable(consumerRecord.value()).isEmpty();
    }

}

