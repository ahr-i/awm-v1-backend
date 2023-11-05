package com.example.teamproject.Repository.JpaRepository;

import com.example.teamproject.JpaClass.OauthUser_Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthRepository extends JpaRepository<OauthUser_Info,Integer> {


    public OauthUser_Info findByEmail(String email);
    public OauthUser_Info save(OauthUser_Info info);
}
