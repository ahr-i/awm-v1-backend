package com.example.teamproject.Dto.CommuityDto;


import com.example.teamproject.JpaClass.CommunityTable.BoardEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Optional;

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


    public static Optional<updateUserDto> OptionalBoardEntityToUpdateDto(Optional<BoardEntity> entity){
       return entity.map(result ->{
            updateUserDto dto = new updateUserDto();
            dto.setId(result.getId());
            dto.setBoardContent(result.getBoardContent());
            dto.setBoardWriter(result.getBoardWriter());
            dto.setBoardHits(result.getBoardHits());
            dto.setUserId(result.getUserId());
            dto.setBoardTitle(result.getBoardTitle());
            dto.setBoardCreatedTime(result.getCreateTime());
            return dto;
        });
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
