package com.example.teamproject.Service;


import com.example.teamproject.JpaClass.UserDto;
//import com.example.teamproject.JpaClass.UserInfo.UserInfo;
import com.example.teamproject.Repository.MemberRepository;
import com.example.teamproject.Security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
/*
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;


    public UserInfo findByUser(UserDto dto){
      return   repository.findByUserNamePassWord(dto);
    }

}
*/