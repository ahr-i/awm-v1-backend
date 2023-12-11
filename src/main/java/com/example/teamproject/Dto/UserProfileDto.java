package com.example.teamproject.Dto;

import com.example.teamproject.Dto.LocationDto.ImageDto;
import com.example.teamproject.Dto.LocationDto.InformationDto;
import com.example.teamproject.JpaClass.LocationTable.Location;
import com.example.teamproject.JpaClass.UserTable.UserEntity;
import lombok.Data;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Data
public class UserProfileDto {
    private String userId;
    private String nickName;
    private String image;
    private String categoryList;

    static public UserProfileDto userEntryToDto(UserEntity userEntity) {
        UserProfileDto userProfileDto = new UserProfileDto();

        userProfileDto.setUserId(userEntity.getUserId());
        userProfileDto.setNickName(userEntity.getNickName());
        userProfileDto.setImage(Base64.getEncoder().encodeToString(userEntity.getImage()));

        return userProfileDto;
    }
}