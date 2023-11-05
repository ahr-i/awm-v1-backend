package com.example.teamproject.JpaClass;

import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OauthUser_Info {
    @Builder
    public OauthUser_Info(String sub, String name,String email, String provider, String providerId, String role) {
        this.sub = sub;
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
        this.role = role;
        this.name = name;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_key;
    @Column
    private String sub;
    @Column
   private String name;
    @Column
    private String email;
    @Column
    private String  provider;
    @Column
    private String providerId;
    @Column
    private String role;
    @CreationTimestamp
    private LocalDateTime createAt;







}
