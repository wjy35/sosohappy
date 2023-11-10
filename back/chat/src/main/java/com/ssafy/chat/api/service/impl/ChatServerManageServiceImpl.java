package com.ssafy.chat.api.service.impl;

import com.ssafy.chat.api.service.ChatServerManageService;
import com.ssafy.chat.db.repository.ChatServerIdRepository;
import com.ssafy.chat.db.repository.MemberIdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServerManageServiceImpl implements ChatServerManageService {

    private final ChatServerIdRepository chatServerIdRepository;

    private final MemberIdRepository memberIdRepository;

    @Override
    public void enter(Long memberId, String simpSessionId) {
        memberIdRepository.saveBySimpSessionId(simpSessionId, memberId);
        chatServerIdRepository.saveByMemberId(memberId);
    }

    @Override
    public void leave(String simpSessionId) {
        Long memberId = memberIdRepository.deleteAndGetBySimpSessionId(simpSessionId);
        chatServerIdRepository.deleteByMemberId(memberId);
    }

    @Override
    public String getChatServerIdByMemberId(Long memberId) {
        return chatServerIdRepository.getChatServerIdByMemberId(memberId);
    }
}
