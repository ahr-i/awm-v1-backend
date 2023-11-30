package com.example.teamproject.Dto.ChatDto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private String roomId; // 채팅방 ID
    private String nickName; // 발신자 닉네임
    private String message; // 메시지
    private LocalDateTime timestamp; // 발신시간
}
