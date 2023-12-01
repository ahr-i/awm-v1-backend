package com.example.teamproject.Handler;

import com.example.teamproject.Dto.ChatDto.ChatMessage;
import com.example.teamproject.Dto.ChatDto.ChatRoom;
import com.example.teamproject.Service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);
        // TextMessage intialGretting = new TextMessage("Welcome to Chat Server");
        // JSON -> Java Object
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        log.info("session : {}",chatMessage.toString());
        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
        log.info("room : {}",room.toString());

        //room.handleActions(session,chatMessage, chatService);
    }

    /** Client가 접속 시 호출되는 메서드*/
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info(session + " 클라이언트 접속");
    }
    /** client가 접속 해제 시 호출되는 메서드*/
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info(session + " 클라이언트 접속 해제");
    }
}
