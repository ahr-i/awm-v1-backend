package com.example.teamproject.JpaClass.CommunityTable;
import com.example.teamproject.JpaClass.LocationTable.Location;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "board_table")
@Entity
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    @Column(length = 20)
    private String boardWriter;
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
    @Column(columnDefinition = "LONGBLOB")
    @Lob
    private byte[] imageFile;

}
