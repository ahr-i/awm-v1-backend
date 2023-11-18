package com.example.teamproject.Service.SpringSecurityLogin;
import com.example.teamproject.Dto.UserDto;
import com.example.teamproject.JpaClass.UserTable.Oauth2UserEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

/**
 * PrincipaDetailService or PrincipalOuath2UserService 가지고온 정보들을
 * 사용자의 인증정보와 권한 정보를pringSecurity에 제공하는 클래스이다.
 */
@Data
public class PrincipalDetails implements UserDetails , OAuth2User {

    private UserDto userInfo;



   //일반 유저 (Oauth 사용자 X)
    private Map<String,Object> attribute;
    public PrincipalDetails(UserDto userInfo) {
        this.userInfo = userInfo;
    }
        //Oauth 유저 사용자
    public PrincipalDetails(Map<String, Object> attribute,UserDto dto) {
        this.attribute = attribute;
        this.userInfo = dto;
    }



    @Override
    public Map<String, Object> getAttributes() {
        return attribute;
    }
    //
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        if(userInfo != null) return userInfo.getPassword();
        else return null;
    }

    @Override
    public String getUsername() {
        return userInfo.getUserId();
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
