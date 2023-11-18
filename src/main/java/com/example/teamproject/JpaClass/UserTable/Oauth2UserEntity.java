package com.example.teamproject.JpaClass.UserTable;

import com.example.teamproject.Dto.CharacterName;
import com.example.teamproject.Dto.UserDto;
import com.example.teamproject.prvoider.Oauth2UserInfo;
import lombok.Data;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Data
@Table(name = "OauthUser_Table")
public class Oauth2UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String providerUserId;
    private String provider;
    private String nickname;
    private int rankScore;
    private int state;
    private String image;


public static Oauth2UserEntity saveTransferOauth2User(Oauth2UserInfo info){
    Oauth2UserEntity entity = new Oauth2UserEntity();
    entity.setNickname(CharacterName.getRandomName());
    entity.setProviderUserId(info.getProviderId());
    entity.setRankScore(0);
    entity.setProvider(info.getProvider());
    entity.setState(0);
    return entity;
}
public static UserDto TransferOauthUserInfoToUserDto(Oauth2UserInfo info, Optional<Oauth2UserEntity> oauth2User){
    UserDto dto = new UserDto();
    Oauth2UserEntity oauth2UserEntity = oauth2User.get();
    dto.setRankScore(oauth2UserEntity.getRankScore());
    dto.setNickName(oauth2UserEntity.getNickname());
    dto.setState(oauth2UserEntity.getState());
    dto.setProvider(oauth2UserEntity.getProvider());
    dto.setImage(null);
        return dto;
}
public static UserDto FirstOauthUserTransferDto(Oauth2UserInfo userInfo){
    UserDto dto = new UserDto();
    dto.setUserId(userInfo.getProviderId());
    return dto;
}
}
