package com.example.teamproject.Dto.CommuityDto.BoardDto;

import com.example.teamproject.JpaClass.CommunityTable.LogBoardCountEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogBoardLikeDto {

    private int Id;
    private String userId;
    private LocalDateTime creatTime;
    private int postId;

}
