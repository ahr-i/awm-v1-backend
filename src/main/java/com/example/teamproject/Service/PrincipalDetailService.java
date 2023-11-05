package com.example.teamproject.Service;

import com.example.teamproject.JpaClass.UserInfo.UserInfo;
import com.example.teamproject.Repository.JPARePository;
import com.example.teamproject.Security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//로그인 Service
@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {


    private final JPARePository jpaRePository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo findUser = jpaRePository.findByUsername(username);

        if(findUser != null) {
            return new PrincipalDetails(findUser);
        }else return null;
    }
}
