package com.example.teamproject.JpaClass.UserTable;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class User {
    @Id
    private String userId;
    @Column
    private String nickName;
    @Column
    private int rankScore;
    @Lob
    private byte[] image;
    @Column
    private String imageHash;
    @Column
    private boolean isAuth;
}
