package com.example.teamproject.JpaClass.UserTable;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nick_name;

    rank_score	INT
    image	LONGBLOB
    image_hash	VARCHAR(256)
    is_auth
}
