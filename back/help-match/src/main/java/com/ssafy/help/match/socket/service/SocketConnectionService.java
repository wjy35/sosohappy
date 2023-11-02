package com.ssafy.help.match.socket.service;

public interface SocketConnectionService {
    void connect(Long memberId,String sessionId);
    void disconnect(String sessionId);
}
