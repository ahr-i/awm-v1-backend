package com.example.teamproject.JpaClass.CommunityTable;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    @Column
    private int locationId;
    @Column
    private String userId;
    @Column
    private String title;
    @Column
    private String content;
    @Lob
    private byte[] image;
    @Column
    private String imageHash;
    @Column
    private int report;
    @Column
    private int likeCount;
    @CreationTimestamp
    private Date createdAt;
}
