package com.example.teamproject;

import com.example.teamproject.JpaClass.UserInfo.UserInfo;
import com.example.teamproject.Repository.JPARePository;
import com.example.teamproject.Service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class TeamProJectApplicationTests {

    @Autowired
    JPARePository rePository;

    @Autowired
    MemberService service;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Test
    void contextLoads() {


    }


}
