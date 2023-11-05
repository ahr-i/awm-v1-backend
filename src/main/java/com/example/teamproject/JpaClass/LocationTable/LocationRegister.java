package com.example.teamproject.JpaClass.LocationTable;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class LocationRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int registerId;
    @Column
    private String userId;
    @Column
    private int locationId;
    @Column
    private String categoty;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
