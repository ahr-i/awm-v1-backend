package com.example.teamproject.Dto;

import com.example.teamproject.JpaClass.UserTable.Oauth2UserEntity;
import lombok.Data;

@Data
public class OauthDto {

    private int id;
    private String nickName;
    private int score;
    private String image;
    private String email;

}

