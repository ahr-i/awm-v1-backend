package com.example.teamproject.JpaClass.UserTable;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ImageTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob
    public byte[] image;
}
