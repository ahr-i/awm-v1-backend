package com.example.teamproject.JpaClass.UserTable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class UserDetail {
    @Id
    private String userId;
    @Column
    private String password;
    @Column
    private String Role;
}
