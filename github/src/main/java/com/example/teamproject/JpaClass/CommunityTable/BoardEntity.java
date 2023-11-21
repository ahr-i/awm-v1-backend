package com.example.teamproject.JpaClass.CommunityTable;

import com.example.teamproject.Dto.CommuityDto.BoardDto;
import com.example.teamproject.JpaClass.LocationTable.Location;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Table(name = "board_table")
@Entity
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 20)
    private String BoardWriter;
    @Column
    private String boardTitle;
    @Column
    private String boardContent;
    @Column
    private int boardHits;
    @Column
    private String userId;
    @ManyToOne
    @JoinColumn(name = "location_id")
    public Location location;
    @Column
    private int reportCount;
    @Column
    int likeCount;
    @JsonManagedReference
    @OneToMany(mappedBy ="entity",cascade = CascadeType.REMOVE,orphanRemoval = true)
    List<CommentEntity> entityList = new ArrayList<>();



    public static BoardEntity Dto_To_Entity(BoardDto dto, Optional<Location> byId,String userId){
        BoardEntity entity = new BoardEntity();
        entity.setId(dto.getId());
        entity.setBoardHits(0);
        entity.setLikeCount(0);
        entity.setBoardWriter(dto.getBoardWriter());
        entity.setBoardTitle(dto.getBoardTitle());
        entity.setBoardContent(dto.getBoardContent());
        entity.setLocation(byId.get());
        entity.setReportCount(0);
        entity.setUserId(userId);
        return entity;
    }


}
