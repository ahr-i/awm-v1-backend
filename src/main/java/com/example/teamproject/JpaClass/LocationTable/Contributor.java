package com.example.teamproject.JpaClass.LocationTable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Contributor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contributorId;
    @Column
    private String userId;
    @Column
    private int locationId;
    @Column
    private int registerId;
    @Column
    private int rate;
}
