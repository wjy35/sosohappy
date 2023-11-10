package com.ssafy.help.match.socket.service;

import com.ssafy.help.match.db.entity.FortuneCookieEntity;
import java.util.List;

public interface FortuneCookieService {
    List<FortuneCookieEntity> viewList(Long memberId);
    void use(Long memberId,String fortuneCookieId);
}
