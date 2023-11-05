package com.example.teamproject.JpaClass.UserTable;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {
    @Id
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
    private String email;
    @Column
    private int  state;

    @Column
    private String provider;
    @Column
    private String password;
    @Column
    @CreationTimestamp
    private LocalDateTime creatAt;
    @Builder
    public User(String userId, String nickName, int rankScore, String image,
                String phoneNumber, String email, int state, String provider, String password) {
        this.userId = userId;
        this.nickName = nickName;
        this.rankScore = rankScore;
        this.image = image;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.state = state;
        this.provider = provider;
        this.password = password;
    }




}
