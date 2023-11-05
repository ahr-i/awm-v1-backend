package com.example.teamproject.JpaClass.UserTable;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class User_Info {
    @Id
    private String userId;
    @Column
    private String phoneNumber;
    @Column
    private String email;
    @Column
    private String role;
    @Column
    private int state;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
