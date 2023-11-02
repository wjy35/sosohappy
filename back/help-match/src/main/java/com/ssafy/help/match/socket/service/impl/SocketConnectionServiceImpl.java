package com.ssafy.help.match.socket.service.impl;

import com.ssafy.help.match.db.repository.MemberIdRepository;
import com.ssafy.help.match.db.repository.MemberSessionEntityRepository;
import com.ssafy.help.match.socket.service.SocketConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocketConnectionServiceImpl implements SocketConnectionService {
    private final MemberSessionEntityRepository memberSessionEntityRepository;
    private final MemberIdRepository memberIdRepository;

    @Override
    public void connect(Long memberId, String sessionId) {
        memberIdRepository.save(sessionId,memberId);
        memberSessionEntityRepository.connect(memberId,sessionId);
    }

    @Override
    public void disconnect(String sessionId) {
        Long memberId = memberIdRepository.deleteAndGetMemberId(sessionId);
        memberSessionEntityRepository.disconnect(memberId);
    }
}
