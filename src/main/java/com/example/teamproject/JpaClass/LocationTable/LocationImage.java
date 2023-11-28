package com.example.teamproject.JpaClass.LocationTable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class LocationImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;
    @Column
    private int locationId;
    @Lob
    private byte[] image;
}
