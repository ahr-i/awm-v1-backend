package com.example.teamproject.Service;


import com.example.teamproject.Dto.UserDto;
import com.example.teamproject.Dto.UserProfileDto;
import com.example.teamproject.JpaClass.LocationTable.Location;
import com.example.teamproject.JpaClass.UserTable.UserEntity;
import com.example.teamproject.Repository.JpaRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public boolean editNickName(UserProfileDto dto, String userId) {
        try {
            Optional<UserEntity> existingUser = repository.findByUserId(userId);

            if (!existingUser.isPresent()) return false;

            UserEntity updatedUser = existingUser.get();

            if(dto.getNickName() != null)
                updatedUser.setNickName(dto.getNickName());
            if(dto.getImage() != null)
                updatedUser.setImage(Base64.getDecoder().decode(dto.getImage()));
            repository.save(updatedUser);

            return true;
        } catch (Exception e) {
            log.info(e.getMessage());

            return false;
        }
    }
}
