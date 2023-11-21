package com.example.teamproject.Dto.CommuityDto.BoardDto;


import com.example.teamproject.JpaClass.CommunityTable.BoardEntity;
import com.example.teamproject.JpaClass.CommunityTable.CommentEntity;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommentDto {

    private int id;
    private String commentWriter;
    private String commentContents;
    private LocalDateTime creatTime;
    private int postId;

    public static CommentEntity TransferCommentEntity(CommentDto dto, BoardEntity entitys) {
        CommentEntity entity = new CommentEntity();
        entity.setReport(0);
        entity.setCommentContents(dto.commentContents);
        entity.setLikeCount(0);
        entity.setCommentWriter(dto.commentWriter);
        entity.setEntity(entitys);

        return entity;
    }

}
