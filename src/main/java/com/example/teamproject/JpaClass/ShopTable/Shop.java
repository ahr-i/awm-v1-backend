package com.example.teamproject.JpaClass.ShopTable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    @Column
    private String name;
    @Column
    private String price;
    @Column
    private String detail;
}
