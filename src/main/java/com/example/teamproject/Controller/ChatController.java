package com.example.teamproject.Controller;

import com.example.teamproject.Dto.ChatDto.ChatMessage;
import com.example.teamproject.Dto.ChatDto.ChatRoom;
import com.example.teamproject.JpaClass.ChatTable.Chat;
import com.example.teamproject.Service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    // 채팅방 생성
    @PostMapping("/create")
    public ChatRoom createRoom(@RequestParam String name){return chatService.createRoom(name);}

    // 채팅방 입장 화면
    // 파라미터로 넘어오는 roomId 를 확인후 해당 roomId 를 기준으로
    // 채팅방을 찾아서 클라이언트를 chatroom 으로 보낸다.
    @GetMapping("/room")
    public ResponseEntity roomDetail(Model model, @RequestParam String roomId){
        model.addAttribute("room", chatService.findRoomById(roomId));
        return ResponseEntity.ok().body("접속완료");
    }

    @GetMapping
    public List<ChatRoom> findAllRoom(){return chatService.findAllRoom();}
    // MessageMapping 을 통해 webSocket 로 들어오는 메시지를 발신 처리한다.
    // 이때 클라이언트에서는 /chat/message 로 요청하게 되고 이것을 controller 가 받아서 처리한다.
    // 처리가 완료되면 /chat/room/roomId 로 메시지가 전송된다.
    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        chatMessage.setMessage(chatMessage.getMessage());
        messagingTemplate.convertAndSend("/chat/room/" + chatMessage.getRoomId(), chatMessage);
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
