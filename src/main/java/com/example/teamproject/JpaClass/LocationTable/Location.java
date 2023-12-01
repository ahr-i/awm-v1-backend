package com.example.teamproject.JpaClass.LocationTable;
import com.example.teamproject.JpaClass.CommunityTable.BoardEntity;
import com.example.teamproject.JpaClass.CommunityTable.UserPostEntity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationId;
    @Column
    private String category;
    @Column
    private Double latitude;
    @Column
    private Double longitude;
    @Column
    private int score;
    @Column
    private int visitCount;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "location" , cascade = CascadeType.REMOVE ,orphanRemoval = true)
    private List<BoardEntity> entityList = new ArrayList<>();
    @Column
    private String title;
    @Column
    private String description;
    @OneToMany(mappedBy = "location",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<UserPostEntity> userPostEntityList = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
