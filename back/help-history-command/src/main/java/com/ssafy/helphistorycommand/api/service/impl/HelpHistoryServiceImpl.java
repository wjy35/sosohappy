package com.ssafy.helphistorycommand.api.service.impl;

import com.ssafy.helphistorycommand.api.dto.CategoryDto;
import com.ssafy.helphistorycommand.api.dto.HelpHistoryDto;
import com.ssafy.helphistorycommand.api.dto.MemberDto;
import com.ssafy.helphistorycommand.cloud.feign.CategoryFeign;
import com.ssafy.helphistorycommand.cloud.feign.MemberFeign;
import com.ssafy.helphistorycommand.db.entity.CertificateEntity;
import com.ssafy.helphistorycommand.db.entity.HelpHistoryEntity;
import com.ssafy.helphistorycommand.db.repository.HelpCertificateRepository;
import com.ssafy.helphistorycommand.db.repository.HelpHistoryRepository;
import com.ssafy.helphistorycommand.api.service.HelpHistoryService;
import com.ssafy.helphistorycommand.util.KafkaEventMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelpHistoryServiceImpl implements HelpHistoryService {

    private final HelpHistoryRepository helpHistoryRepository;

    private final HelpCertificateRepository helpCertificateRepository;

    private final KafkaEventMapper kafkaEventMapper;

    private final CategoryFeign categoryFeign;

    private final MemberFeign memberFeign;

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
        addHelpCertificate(helpHistoryEntity);
    }

    public void addHelpCertificate(HelpHistoryEntity helpHistory){

        CategoryDto categoryDto = getCategory(helpHistory);

        MemberDto memberDto = getMember(helpHistory.getToMemberId());

        CertificateEntity certificateEntity = CertificateEntity.builder()
                .createdAt(helpHistory.getCreatedAt())
                .memberId(helpHistory.getFromMemberId())
                .nickname(memberDto.getNickName())
                .categoryName(categoryDto.getCategoryName())
                .build();

        helpCertificateRepository.save(certificateEntity);
    }

    public CategoryDto getCategory(HelpHistoryEntity helpHistory) {

        String jsonString = categoryFeign.getCategoryDetail(helpHistory.getCategoryId());

        JSONObject jsonObject = new JSONObject(jsonString).getJSONObject("result").getJSONObject("category");

        long categoryId = jsonObject.getLong("categoryId");
        String categoryName = jsonObject.getString("categoryName");
        String categoryImage = jsonObject.getString("categoryImage");

        return CategoryDto.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .categoryImage(categoryImage).build();
    }

    public MemberDto getMember(long memberId) {
        String jsonString = memberFeign.getMemberDetail(memberId);
        JSONObject jsonObject = new JSONObject(jsonString).getJSONObject("result").getJSONObject("member");

        return MemberDto.builder()
                .memberId(memberId)
                .nickName(jsonObject.getString("nickname")).build();
    }

}
