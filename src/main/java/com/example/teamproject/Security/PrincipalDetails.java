package com.example.teamproject.Security;

import com.example.teamproject.JpaClass.OauthUser_Info;
import com.example.teamproject.JpaClass.UserInfo.UserInfo;
import com.example.teamproject.JpaClass.UserTable.AuthUserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * PrincipaDetailService or PrincipalOuath2UserService 가지고온 정보들을
 * 사용자의 인증정보와 권한 정보를 SpringSecurity에 제공하는 클래스이다.
 */
public class PrincipalDetails implements UserDetails , OAuth2User {

    private UserInfo info;
    private AuthUserDetail details;

   //일반 유저 (Oauth 사용자 X)
    private Map<String,Object> attribute;
    public PrincipalDetails(UserInfo info) {
        this.info = info;
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
                if(info != null) return info.getRole();
                else if(details != null) return details.get
                else return null;
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        if(info != null) return info.getPassword();
        else return null;
    }

    @Override
    public String getUsername() {
        if(info != null){
            return info.getUsername();
        }else if(oauthUser_info != null) {
            return oauthUser_info.getSub();
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
