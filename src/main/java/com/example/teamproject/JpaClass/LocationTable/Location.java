package com.example.teamproject.JpaClass.LocationTable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Column
    private LocalDateTime createdAt;
}
