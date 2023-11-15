package com.example.teamproject.Dto.CommuityDto;


import com.example.teamproject.JpaClass.CommunityTable.BoardEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class BoardDto {

    private int id;
    private String boardWriter;
    private String boardTitle;
    private String boardContent;
    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdateTime;



    public BoardDto(int id, String boardWrite, String boardTitle, String boardContent, int boardHits, LocalDateTime createTime) {
        this.id = id;
        this.boardWriter = boardWrite;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardHits = boardHits;
        this.boardCreatedTime = createTime;
    }
}
