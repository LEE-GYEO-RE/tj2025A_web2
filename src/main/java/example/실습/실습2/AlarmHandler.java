package example.실습.실습2;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AlarmHandler extends TextWebSocketHandler {

    private static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        broadcast("익명의 유저가 접속했습니다.");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);

        // 세션이 완전히 0이 된 경우에만 퇴장 메시지 전송
        if (sessions.isEmpty()) {
            broadcast("익명의 유저가 퇴장했습니다.");
        }
    }

    private void broadcast(String message) throws Exception {
        for (WebSocketSession s : sessions) {
            if (s.isOpen()) {
                s.sendMessage(new TextMessage(message));
            }
        }
    }
}