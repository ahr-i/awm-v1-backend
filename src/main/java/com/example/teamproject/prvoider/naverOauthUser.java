package com.example.teamproject.prvoider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
@Getter
@Slf4j
public class naverOauthUser implements Oauth2UserInfo {



    private Map<String,Object> attribute;

    public naverOauthUser(Map<String, Object> attribute){
        this.attribute=attribute;
    }
    @Override
    public String getProviderId() {
        return (String)attribute.get("email");
    }

    @Override
    public String getProvider() {
        return "naver";
    }
}
