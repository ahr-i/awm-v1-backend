package com.example.teamproject.JpaClass.UserTable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AuthUserDetail {
    @Id
    private String userId;
    @Column
    private String provider;
    @Column
    private String code;
    @Builder
    public AuthUserDetail detail(String userId,String provider,String code){
        this.userId = userId;
        this.provider = provider;
        this.code = code;
        return this;
    }
}
