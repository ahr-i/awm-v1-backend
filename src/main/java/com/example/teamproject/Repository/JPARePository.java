package com.example.teamproject.Repository;

import com.example.teamproject.JpaClass.UserDto;
import com.example.teamproject.JpaClass.UserInfo.UserInfo;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.plaf.PanelUI;

public interface JPARePository extends JpaRepository<UserInfo,Integer> {

    public UserInfo findByUsername(String username);
    public UserInfo findByUsernameAndPassword(String username,String password);
    public UserInfo findByUsernameAndPhoneNumber(String username, String phoneNumber);



}
