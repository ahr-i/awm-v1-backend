package com.example.teamproject.Dto.CommuityDto;

import com.example.teamproject.JpaClass.CommunityTable.BoardEntity;
import com.example.teamproject.Repository.CommuityRepository.CommentRepository;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Data
public class PagePostDto {
    private int postId;
    private String boardTitle;
    private String boardWriter;
    private long commentCount;
    private int postHits;
    private LocalDateTime createTime;


    public static Page<PagePostDto> PageBoardEntityToUpdateUserDto(Page<BoardEntity> entities, CommentRepository repository){
        return entities.map(entity -> {
            PagePostDto dto = new PagePostDto();
            dto.setPostId(entity.getId());
            dto.setBoardTitle(entity.getBoardTitle());
            dto.setCreateTime(entity.getCreateTime());
            dto.setBoardWriter(entity.getBoardWriter());
            dto.setPostHits(entity.getBoardHits());
            Long aLong = repository.countAllByEntity(entity);
            dto.setCommentCount(aLong);
            return dto;
        });
    }

}

