package com.ssafy.helphistorycommand.api.service.impl;

import com.ssafy.helphistorycommand.api.dto.HelpHistoryDto;
import com.ssafy.helphistorycommand.db.entity.HelpHistoryEntity;
import com.ssafy.helphistorycommand.db.repository.HelpHistoryRepository;
import com.ssafy.helphistorycommand.api.service.HelpHistoryService;
import com.ssafy.helphistorycommand.util.KafkaEventMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelpHistoryServiceImpl implements HelpHistoryService {

    private final HelpHistoryRepository helpHistoryRepository;

    private final KafkaEventMapper kafkaEventMapper;

    @Override
    public void addHelpHistory(ConsumerRecord<String, String> message) {
        HelpHistoryDto helpHistoryDto = kafkaEventMapper.toEvent(message, HelpHistoryDto.class);

        HelpHistoryEntity helpHistoryEntity = HelpHistoryEntity.builder()
                .toMemberId(helpHistoryDto.getToMemberId())
                .fromMemberId(helpHistoryDto.getFromMemberId())
                .content(helpHistoryDto.getContent())
                .x(helpHistoryDto.getLatitude())
                .y(helpHistoryDto.getLongitude())
                .categoryId(helpHistoryDto.getCategoryId())
                .build();

        helpHistoryRepository.save(helpHistoryEntity);
    }
}
