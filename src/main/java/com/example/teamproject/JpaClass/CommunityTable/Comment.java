package com.example.teamproject.JpaClass.CommunityTable;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    @Column
    private int postId;
    @Column
    private String userId;
    @Column
    private String comment;
    @Column
    private int report;
    @Column
    private int like;
    @CreationTimestamp
    private Date createdAt;
}
