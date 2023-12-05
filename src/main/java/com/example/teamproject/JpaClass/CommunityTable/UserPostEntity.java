package com.example.teamproject.JpaClass.CommunityTable;


import com.example.teamproject.JpaClass.LocationTable.Location;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "LogBoardTable")
public class UserPostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nickName;
    private String userId;
    private int likeCount;
    private int badCount;
    @Lob
    private byte[] image;
    @CreationTimestamp
    private LocalDateTime createAt;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    private String content;
    @OneToMany(mappedBy = "logBoardEntity" ,cascade = CascadeType.REMOVE,orphanRemoval = true)
    @JsonManagedReference
    private List<LogBoardCountEntity> entityList = new ArrayList<>();
}
