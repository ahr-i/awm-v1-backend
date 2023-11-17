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

    public static OauthDto Oauth2UserEntityToDto(Oauth2UserEntity entity){
        OauthDto dto = new OauthDto();
        dto.setId(entity.getId());
        dto.setScore(entity.getScore());
        dto.setEmail(entity.getEmail());
        dto.setNickName(entity.getNickname());
        dto.setImage(entity.getImage());
        return dto;
    }
}

