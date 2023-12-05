package com.example.teamproject.Dto.CommuityDto.BoardDto;

import com.example.teamproject.JpaClass.CommunityTable.UserPostEntity;
import com.example.teamproject.JpaClass.LocationTable.Location;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
public class UserLogDto {

    private int id;
    private String nickName;
    private String content;
    private String userId;
    private LocalDateTime createAt;
    private int locationId;
    private int likeCount;
    private int badCount;

    private byte[] image;

    public static UserPostEntity TransferUserEntity(UserLogDto dto, Optional<Location>
            byId, String userId, String nickName)  {
        UserPostEntity entity = new UserPostEntity();
        Location location = byId.get();
        entity.setUserId(userId);
        entity.setLocation(location);
        entity.setNickName(dto.getNickName());
        entity.setContent(dto.getContent());
        entity.setNickName(nickName);
        entity.setBadCount(dto.getBadCount());
        entity.setImage(null);
        return entity;
    }
    public static UserLogDto TransferUserLogDto(UserPostEntity entity){

        UserLogDto dto = new UserLogDto();

        dto.setNickName(entity.getNickName());
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setCreateAt(entity.getCreateAt());
        dto.setUserId(entity.getUserId());
        dto.setImage(entity.getImage());
        return dto;
    }
    public static UserLogDto TransferPageUserLogDto(UserPostEntity entities){
        UserLogDto dto = new UserLogDto();

        dto.setUserId(entities.getUserId());
        dto.setContent(entities.getContent());
        dto.setId(entities.getId());
        dto.setCreateAt(entities.getCreateAt());
        dto.setLocationId(entities.getLocation().getLocationId());
        dto.setNickName(entities.getNickName());
        dto.setLikeCount(entities.getLikeCount());
        dto.setBadCount(entities.getBadCount());
        if(entities.getImage() != null) {
            dto.setImage(entities.getImage());
        }else dto.setImage(null);
        return dto;
    }
}