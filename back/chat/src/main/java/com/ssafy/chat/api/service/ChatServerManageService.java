package com.ssafy.chat.api.service;

public interface ChatServerManageService {
    void enter(Long memberId, String simpSessionId);
    void leave(String simpSessionId);
    String getChatServerIdByMemberId(Long memberId);
}
