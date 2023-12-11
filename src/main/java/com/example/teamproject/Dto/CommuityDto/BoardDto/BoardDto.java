package com.example.teamproject.Dto.CommuityDto.BoardDto;

import com.example.teamproject.JpaClass.CommunityTable.BoardEntity;
import com.example.teamproject.JpaClass.LocationTable.Location;
import com.example.teamproject.Repository.CommuityRepository.CommentRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Getter
@Setter
@ToString
@Slf4j
public class BoardDto {
    private int postId;
    private String userId;
    private String boardWriter;
    private String boardTitle;
    private String boardContent;
    private int locationId;
    private int boardHit;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private int likeCount;
    private int reportCount;
    private byte[] image;
    private long commentCount;

    public static BoardEntity SaveToBoardEntity(BoardDto dto, Optional<Location> location, String userId,MultipartFile file) throws IOException {
        BoardEntity entity = new BoardEntity();
        entity.setUserId(userId);
        entity.setPostId(dto.getPostId());
        entity.setBoardWriter(dto.getBoardWriter());
        entity.setBoardTitle(dto.getBoardTitle());
        entity.setBoardContent(dto.getBoardContent());
        entity.setLocation(location.get());
        entity.setBoardHits(dto.getBoardHit());
        entity.setLikeCount(0);
        entity.setReportCount(0);
        if(file == null) entity.setImageFile(null);
        else {
            entity.setImageFile(file.getBytes());

        }
        return entity;
    }
    public static BoardDto DetailToBoardDto(BoardEntity entity){
        BoardDto dto = new BoardDto();

        dto.setBoardHit(entity.getBoardHits());
        dto.setBoardTitle(entity.getBoardTitle());
        dto.setPostId(entity.getPostId());
        dto.setCreateTime(entity.getCreateTime());
        dto.setBoardWriter(entity.getBoardWriter());
        dto.setBoardTitle(entity.getBoardTitle());
        dto.setBoardContent(entity.getBoardContent());
        dto.setUserId(entity.getUserId());
        if(entity.getImageFile() == null) dto.setImage(null);
        else dto.setImage(entity.getImageFile());
        return dto;
    }
    public static Page<BoardDto> PageToBoardPostDto(Page<BoardEntity> entities, CommentRepository repository) {
        return entities.map(entity -> {
           BoardDto dto = new BoardDto();
           dto.setBoardTitle(entity.getBoardTitle());
           dto.setBoardContent(entity.getBoardContent());
           dto.setBoardHit(entity.getBoardHits());
           dto.setPostId(entity.getPostId());
           dto.setBoardWriter(entity.getBoardWriter());
           dto.setCreateTime(entity.getCreateTime());
           dto.setCommentCount(repository.countAllByEntity(entity));
           if(entity.getImageFile() == null) dto.setImage(null);
           else dto.setImage(entity.getImageFile());
            return dto;
        });
    }
    public static BoardEntity updatePost(BoardDto dto,BoardEntity entity,MultipartFile file) throws IOException {
        entity.setBoardTitle(dto.getBoardTitle());
        entity.setBoardContent(dto.getBoardContent());
        entity.setBoardWriter(dto.getBoardWriter());
        if(file == null) entity.setImageFile(null);
        else entity.setImageFile(file.getBytes());
        return entity;
    }
}
