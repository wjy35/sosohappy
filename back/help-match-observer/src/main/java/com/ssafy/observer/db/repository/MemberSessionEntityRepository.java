package com.ssafy.observer.db.repository;


import com.ssafy.observer.db.entity.HelpMatchStatus;
import com.ssafy.observer.db.entity.HelpMatchType;

public interface MemberSessionEntityRepository {

    public void setMatchType(Long memberId, HelpMatchType matchType);

    public void setMatchStatus(Long memberId, HelpMatchStatus matchStatus);
    public String getServerUUID(Long memberId);
    public boolean isConnected(Long memberId) ;

}
