package com.example.teamproject.JpaClass.ChatWoo;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Document(collation = "chat")
public class ChatWoo {
    @Id
    private String id;
    private String msg;
    private String sender;
    private LocalDateTime createAt;
    private Integer roomNumber;
}
