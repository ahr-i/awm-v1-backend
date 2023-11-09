package com.example.teamproject.JpaClass.LocationTable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class LocationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int infoId;
    @Column
    private String userId;
    @Column
    private int locationId;
    @Column
    private String title;
    @Column
    private String address;
    @Column
    private String information;

}
