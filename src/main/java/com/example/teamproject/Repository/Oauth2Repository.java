package com.example.teamproject.Repository;

import com.example.teamproject.JpaClass.UserTable.AuthUserDetail;
import com.example.teamproject.Repository.JpaRepository.AuthUserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class Oauth2Repository {

    private final AuthUserDetailRepository repository;

    public AuthUserDetail findAllByUserId(String userId){
        return repository.findAllByUserId(userId);
    }
    public AuthUserDetail save(AuthUserDetail detail){
        return repository.save(detail);
    }
}
