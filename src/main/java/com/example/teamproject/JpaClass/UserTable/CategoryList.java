package com.example.teamproject.JpaClass.UserTable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CategoryList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int listId;
    @Column
    private String userId;
    @Column
    private String categoryList;
}