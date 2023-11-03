package com.ssafy.help.match.socket;

import com.ssafy.help.match.socket.service.SocketConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final SocketConnectionService socketConnectionService;

    @EventListener
    void handleSessionConnected(SessionConnectedEvent event){
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(event.getMessage().getHeaders().get("simpConnectMessage",Message.class));

        String sessionId = stompHeaderAccessor.getSessionId();
        Long memberId = Long.parseLong(stompHeaderAccessor.getFirstNativeHeader("memberId"));

        socketConnectionService.connect(memberId,sessionId);
    }

    @EventListener
    void handleSessionDisconnect(SessionDisconnectEvent event){
        String sessionId = event.getSessionId();

        socketConnectionService.disconnect(sessionId);
    }
}
