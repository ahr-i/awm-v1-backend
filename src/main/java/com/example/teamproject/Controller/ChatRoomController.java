package com.example.teamproject.Controller;

import com.example.teamproject.Dto.ChatDto.ChatRoom;
import com.example.teamproject.Service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Controller
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;

    // 채팅 리스트
    @GetMapping("/room")
    @ResponseBody
    public ResponseEntity rooms(Model model){
        log.info("채팅 리스트");
        return ResponseEntity.ok().body("채팅 리스트 화면");
    }

    // 모든 채팅방 목록
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String roomId){
        return chatService.createRoom(roomId);
    }

    // 채팅방 입장
    @GetMapping("/room/enter/{roomId}")
    @ResponseBody
    public ResponseEntity<?> roomDetail(Model model, @PathVariable String roomId) {
        ChatRoom findChatRoom = chatService.findRoomById(roomId);

        if (findChatRoom == null) {
            return ResponseEntity.badRequest().body("채팅방이 존재하지 않음");
        }


        model.addAttribute("roomId", roomId);
        return ResponseEntity.ok().body(roomId + " 채팅방 입장");
    }


    //특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ResponseEntity roomInfo(@PathVariable String roomId){
        ChatRoom findChatRoom = chatService.findRoomById(roomId);
        if(findChatRoom == null)
            return ResponseEntity.badRequest().body("해당 채팅방을 찾을수 없음");
        return ResponseEntity.ok(findChatRoom);
    }
}
