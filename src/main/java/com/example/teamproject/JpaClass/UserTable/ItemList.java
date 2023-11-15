package com.example.teamproject.JpaClass.UserTable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class ItemList {
    @Id
    private String userId;
    @Column(columnDefinition = "JSON")
    private String list;
}
