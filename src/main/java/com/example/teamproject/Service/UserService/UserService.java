package com.example.teamproject.Service.UserService;


import com.example.teamproject.Dto.UserDto.UserDto;
import com.example.teamproject.JpaClass.UserTable.UserEntity;
import com.example.teamproject.Repository.JpaRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {



    private final UserRepository repository;


    public void join(UserDto info) {
        UserEntity userEntity = UserDto.UserDtoTransferUser(info);
        repository.save(userEntity);
    }

    public UserDto findByUser(UserDto info) {
        Optional<UserEntity> byUserId = repository.findByUserId(info.getUserId());
        if(byUserId.isPresent()) {
          return UserDto.UserEntityToUserDto(byUserId.get());
        } return  null;

    }
}
