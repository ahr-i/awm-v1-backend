<<<<<<< HEAD
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
public class User {
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
    private String email;
    @Column
    private int  state;
    @Column
    private String password;
    @Column
    @CreationTimestamp
    private LocalDateTime creatAt;
    @Builder
    public User(String userId, String nickName, int rankScore, String image,
                String phoneNumber, String email, int state, String password) {
        this.userId = userId;
        this.nickName = nickName;
        this.rankScore = rankScore;
        this.image = image;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.state = state;
        this.password = password;
    }




}
=======
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
public class User {
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
    private String email;
    @Column
    private int  state;
    @Column
    private String password;
    @Column
    @CreationTimestamp
    private LocalDateTime creatAt;
    @Builder
    public User(String userId, String nickName, int rankScore, String image,
                String phoneNumber, String email, int state, String password) {
        this.userId = userId;
        this.nickName = nickName;
        this.rankScore = rankScore;
        this.image = image;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.state = state;
        this.password = password;
    }




}
>>>>>>> ec39bc92820df73215dd9b39b629f9db2cbb79f2
