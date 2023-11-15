package com.example.teamproject.Dto.CommuityDto;

import com.example.teamproject.JpaClass.CommunityTable.CommentEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    updateUserDto boardDto;
    List<CommentEntity> entityList;
    public Response(updateUserDto boardDto, List<CommentEntity> entityList) {
        this.boardDto = boardDto;
        this.entityList = entityList;
    }


}
