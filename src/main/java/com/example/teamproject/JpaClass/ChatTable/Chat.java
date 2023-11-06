package com.example.teamproject.JpaClass.ChatTable;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatId;
    @Column
    private String sender;
    @Column
    private String receiver;
    @Column
    private String comment;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
