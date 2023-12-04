package com.example.teamproject.Service;

import com.example.teamproject.Dto.ChatDto.ChatRoom;
import com.example.teamproject.JpaClass.ChatTable.Chat;
import com.example.teamproject.Repository.MySQL.ChatRepository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
    // JSON 매핑을 위한 ObjectMapper 주입
    //private final ObjectMapper objectMapper;
    // 채팅방 정보를 담은 맵
    private Map<String, ChatRoom> chatRooms;

    private ChatMessageRepository chatMessageRepository;

    // 초기화 메소드
    @PostConstruct
    private void init(){chatRooms = new LinkedHashMap<>();}

    // 모든 채팅방 조회
    public List<ChatRoom> findAllRoom(){
        return new ArrayList<>(chatRooms.values());
    }

    // 특정 채팅방 ID로 채팅방 조회
    public ChatRoom findRoomById(String roomId){
        return chatRooms.get(roomId);
    }

    // 채팅방 생성, 리팩토링 완료
    public ChatRoom createRoom(String locationId){
        ChatRoom chatRoom = new ChatRoom().create(locationId);
        chatRooms.put(locationId, chatRoom);
        return chatRoom;
    }

    // WebSocket 세션에 메시지를 전송하는 메소드
    // STOMP 방식으로 동작하므로 기존의 세션관련 코드는 비활성화
    /*
    public <T> void sendMessage(WebSocketSession session, T message){
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        }catch (IOException e){
            log.error(e.getMessage(), e);
        }
    }

     */

    public List<Chat> getAllMessages(){
        return chatMessageRepository.findAll();
    }

    public void saveMessage(Chat message){
        chatMessageRepository.save(message);
    }


}
