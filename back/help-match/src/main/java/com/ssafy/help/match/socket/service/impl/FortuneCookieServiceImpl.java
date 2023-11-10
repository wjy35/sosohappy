package com.ssafy.help.match.socket.service.impl;

import com.ssafy.help.match.db.entity.FortuneCookieEntity;
import com.ssafy.help.match.db.repository.FortuneCookieEntityRepository;
import com.ssafy.help.match.socket.service.FortuneCookieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FortuneCookieServiceImpl implements FortuneCookieService {
    private final FortuneCookieEntityRepository fortuneCookieEntityRepository;

    @Override
    public List<FortuneCookieEntity> viewList(Long memberId) {
        return fortuneCookieEntityRepository.getListByMemberId(memberId);
    }

    @Override
    public void use(Long memberId, String fortuneCookieId) {
        fortuneCookieEntityRepository.delete(memberId,fortuneCookieId);
    }
}
