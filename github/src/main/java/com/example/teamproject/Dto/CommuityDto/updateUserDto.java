package com.example.teamproject.Dto.CommuityDto;


import com.example.teamproject.JpaClass.CommunityTable.BoardEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class updateUserDto {



    private int id;
    private String boardWriter;
    private String boardTitle;
    private String boardContent;
    private int boardHits;
    private String userId;
    private LocalDateTime boardCreatedTime;

    public updateUserDto(int id, String boardWriter, String boardTitle, String boardContent, int boardHits, LocalDateTime boardCreatedTime) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
    }
    public  static updateUserDto TransferBoardEntityToFindBoardDto(BoardEntity entity){
        updateUserDto dto = new updateUserDto();
        dto.setId(entity.getId());
        dto.setBoardContent(entity.getBoardContent());
        dto.setBoardWriter(entity.getBoardWriter());
        dto.setBoardHits(entity.getBoardHits());
        dto.setUserId(entity.getUserId());
        dto.setBoardTitle(entity.getBoardTitle());
        dto.setBoardCreatedTime(entity.getCreateTime());
        return dto;
    }
}
