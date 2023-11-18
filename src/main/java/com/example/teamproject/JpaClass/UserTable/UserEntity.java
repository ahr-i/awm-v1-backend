package com.example.teamproject.JpaClass.UserTable;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "user_table")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column
    private String userId;
    @Column
    private String nickName;
    @Column
    private int rankScore;
    @Lob
    private String image;
    @Column
    private String imageHash;
    @Column
    private String phoneNumber;
    @Column
    private int  state;
    @Column
    private String password;
    @Column
    @CreationTimestamp
    private LocalDateTime creatAt;
    private String provider;
}
