package com.example.teamproject.Repository.JpaRepository;

import com.example.teamproject.JpaClass.UserTable.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

    public UserInfo findByUserId(String userId);
}
