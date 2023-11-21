package com.example.teamproject.Repository.ChatRepository;

import com.example.teamproject.JpaClass.ChatTable.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<Chat, Integer> {
}
