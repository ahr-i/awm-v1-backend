package com.example.teamproject.Repository;

import com.example.teamproject.JpaClass.UserTable.AuthUserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserDetailRepository extends JpaRepository<AuthUserDetail,String> {

    public AuthUserDetail findAllByUserId(String userId);

}
