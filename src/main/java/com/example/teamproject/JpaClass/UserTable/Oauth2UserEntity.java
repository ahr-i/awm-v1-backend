package com.example.teamproject.JpaClass.UserTable;

import com.example.teamproject.Dto.CharacterName;
import com.example.teamproject.prvoider.Oauth2UserInfo;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Oauth2UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String providerUserId;
    private String provider;
    private String nickname;
    private int score;
    private int state;
    private String image;
    private String email;

public static Oauth2UserEntity saveTransferOauth2User(Oauth2UserInfo info){
    Oauth2UserEntity entity = new Oauth2UserEntity();
    entity.setNickname(CharacterName.getRandomName());
    entity.setProviderUserId(info.getProviderId());
    entity.setScore(0);
    entity.setProvider(info.getProvider());
    entity.setState(0);
    entity.setEmail(info.getEmail());
    return entity;
}
}
