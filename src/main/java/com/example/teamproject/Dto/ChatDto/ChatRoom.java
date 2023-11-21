package com.example.teamproject.Dto.ChatDto;

import com.example.teamproject.Service.ChatService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public class ChatRoom {
    private String roomId; // 채팅방의 고유 식별자
    private String name; // 채팅방의 이름
    private Set<WebSocketSession> sessions = new HashSet<>(); // 채팅방에 참여한 WebSocket 세션들의 집합

    @Builder
    public ChatRoom create(String name){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();

        return chatRoom;
    }

    /*
     * WebSocket 세션의 액션을 처리하는 메소드
     *
     * @param session      현재 액션을 수행하는 WebSocket 세션
     * @param chatMessage  수신된 채팅 메시지 객체
     * @param chatService  채팅 서비스 객체
     */
    public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService){
        sendMessage(chatMessage, chatService);
    }

    // 모든 세션에 메시지를 전송하는 메소드
    public <T> void sendMessage(T message, ChatService chatService){
        // 병렬 스트림을 사용하여 모든 세션에 메시지를 전송
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
}
