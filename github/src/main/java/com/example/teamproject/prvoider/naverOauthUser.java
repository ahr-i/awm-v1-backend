<<<<<<< HEAD
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
        log.info("프로바이더 아이디 : {}",attribute.get("id"));
        return (String)attribute.get("id");
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getEmail() {
        return (String)attribute.get("email");
    }

}
=======
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
        log.info("프로바이더 아이디 : {}",attribute.get("id"));
        return (String)attribute.get("id");
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getEmail() {
        return (String)attribute.get("email");
    }

}
>>>>>>> ec39bc92820df73215dd9b39b629f9db2cbb79f2
