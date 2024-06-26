package com.example.teamproject.Controller;

import com.example.teamproject.Dto.ChatDto.ChatMessage;
import com.example.teamproject.Dto.ChatDto.ChatRoom;
import com.example.teamproject.JpaClass.ChatTable.Chat;
import com.example.teamproject.Service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequiredArgsConstructor
@Log4j2
public class ChatController {
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;


    // MessageMapping 을 통해 webSocket 로 들어오는 메시지를 발신 처리한다.
    // 이때 클라이언트에서는 /chat/message 로 요청하게 되고 이것을 controller 가 받아서 처리한다.
    // 처리가 완료되면 /topic/chat/room/roomId 로 메시지가 전송된다.
    @MessageMapping("/chat/message")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        try {
            // 클라이언트에서 전송한 방의 ID를 확인
            String roomId = chatMessage.getRoomId();

            Thread.sleep(500);

            // 해당 방에 참여한 모든 사용자에게 ChatMessage 객체를 전송
            messagingTemplate.convertAndSend("/topic/chat/room/" + roomId, chatMessage);
            log.info("Message [{}] send by member: {} to chatting room: {}", chatMessage.getMessage(),
                    chatMessage.getNickName(),
                    chatMessage.getRoomId());
        } catch (Exception e) {
            // 예외 처리 로직 추가
            // 예를 들어, 로그 남기기 또는 클라이언트에게 에러 메시지 전송하기 등의 작업 수행
            e.printStackTrace(); // 또는 로깅 프레임워크를 사용하여 로그에 기록
        }
    }



    // 아래 코드는 DB를 위한 작업임, 채팅 기능에는 해당 메소드 사용 불필요
    @GetMapping("/messages")
    public List<Chat> getAllMessage(){return chatService.getAllMessages();}

    @PostMapping("/send")
    public void send(@RequestBody ChatMessage message/*, Authorization authorization*/){ // 리펙토링
        //region DB에 채팅내역을 저장하기 위한 코드, 현재 작업은 아직 미완
        /*
        Chat chat = new Chat();
        chat.setSender(message.getSender());
        chat.setComment(message.getMessage());
        chat.setCreatedAt(message.getTimestamp());
        chat.setLocationId(message.getRoomId());
        chatService.saveMessage(chat);
        */
        //endregion
        messagingTemplate.convertAndSend("/send/room", message);
    }
}
