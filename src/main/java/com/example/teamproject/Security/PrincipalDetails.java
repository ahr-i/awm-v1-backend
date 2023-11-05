package com.example.teamproject.Security;

import com.example.teamproject.JpaClass.OauthUser_Info;
//import com.example.teamproject.JpaClass.UserInfo.UserInfo;
import com.example.teamproject.JpaClass.UserTable.AuthUserDetail;
import com.example.teamproject.JpaClass.UserTable.UserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * PrincipaDetailService or PrincipalOuath2UserService 가지고온 정보들을
 * 사용자의 인증정보와 권한 정보를pringSecurity에 제공하는 클래스이다.
 */
public class PrincipalDetails implements UserDetails , OAuth2User {

    private UserDetail userDetail;
    private AuthUserDetail details;

   //일반 유저 (Oauth 사용자 X)
    private Map<String,Object> attribute;
    public PrincipalDetails(UserDetail info) {
        this.userDetail = info;
    }
        //Oauth 유저 사용자
    public PrincipalDetails(Map<String, Object> attribute, AuthUserDetail details) {
        this.attribute = attribute;
        this.details = details;
    }



    @Override
    public Map<String, Object> getAttributes() {
        return attribute;
    }
    //
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                if(userDetail != null) return "ROLE_USER";
                else if(details != null) return "ROLE_USER";
                else return null;
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        if(userDetail != null) return userDetail.getPassword();
        else return null;
    }

    @Override
    public String getUsername() {
        if(userDetail != null){
            return userDetail.getUserId();
        }else if(details != null) {
            return details.getUserId();
        }else return null;

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return null;
    }
}
