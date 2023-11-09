package com.example.teamproject.Service;


import com.example.teamproject.Dto.UserDto;
import com.example.teamproject.JWT.JWTUtil;
import com.example.teamproject.JpaClass.UserTable.User;
import com.example.teamproject.Repository.JpaRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {



    private final UserRepository repository;


    public void join(User info) {
        repository.save(info);
    }

    public User findByUser(User info) {
        return repository.findByUserId(info.getUserId());
    }
}
