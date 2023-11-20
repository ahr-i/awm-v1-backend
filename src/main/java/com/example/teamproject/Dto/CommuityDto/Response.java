package com.example.teamproject.Dto.CommuityDto;

import com.example.teamproject.Dto.CommuityDto.BoardDto.BoardDto;
import com.example.teamproject.JpaClass.CommunityTable.CommentEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    BoardDto boardDto;
    List<CommentEntity> entityList;
    Long commentCount;
    public Response(BoardDto dto, List<CommentEntity> entityList, Long commentCount) {
        this.boardDto = dto;
        this.entityList = entityList;
        if(commentCount != null) this.commentCount = commentCount;
        else this.commentCount = Long.valueOf(0);

    }
}
