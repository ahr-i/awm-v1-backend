package com.example.teamproject.Service;


import com.example.teamproject.JpaClass.UserTable.User;
import com.example.teamproject.Repository.JpaRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;


    public void join(User info){
        repository.save(info);
    }
    public User findByUser(User info){
        return repository.findByUserId(info.getUserId());
    }
}
