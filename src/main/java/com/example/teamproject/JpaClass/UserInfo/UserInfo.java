package com.example.teamproject.JpaClass.UserInfo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String phoneNumber;
    @CreationTimestamp
    private LocalDateTime creatAt;
    private String Role;

}
