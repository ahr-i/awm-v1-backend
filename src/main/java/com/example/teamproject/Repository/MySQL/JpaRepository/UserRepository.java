package com.example.teamproject.Repository.MySQL.JpaRepository;

import com.example.teamproject.JpaClass.UserTable.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {


    public Optional<UserEntity> findByUserId(String userId);

}
