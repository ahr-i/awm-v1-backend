package com.example.teamproject.Repository;

import com.example.teamproject.JpaClass.OauthUser_Info;
import com.example.teamproject.JpaClass.UserDto;
import com.example.teamproject.JpaClass.UserInfo.QUserInfo;
import com.example.teamproject.JpaClass.UserInfo.UserInfo;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Repository
@RequiredArgsConstructor
@Transactional
public class MemberRepository{
    private final JPAQueryFactory queryFactory;
    private final JPARePository jpaRePository;
    private final OauthRepository oauthRepository;


    public void save(UserInfo info){
        jpaRePository.save(info);
    }

    public UserInfo findByName(String username){
       return jpaRePository.findByUsername(username);
    }

   public UserInfo findByUserNamePassWord(UserDto dto){
      return   jpaRePository.findByUsernameAndPassword(dto.getUsername(),dto.getPassword());
   }

    public UserInfo findByUsernameAndPhoneNumber(String username, String phoneNumber){
        return jpaRePository.findByUsernameAndPhoneNumber(username, phoneNumber);
    }
    public OauthUser_Info findByEmail(String email){
        return oauthRepository.findByEmail(email);
    }
    public OauthUser_Info oauthUser_save(OauthUser_Info info){
        return oauthRepository.save(info);
    }


}
