package com.example.teamproject.JpaClass.CommunityTable;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class LogBoardCountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String userId;
    @CreationTimestamp
    private LocalDateTime createTime;
    @ManyToOne
    @JoinColumn(name = "postId")
    private UserPostEntity logBoardEntity;
    private int countCheck;

    //좋아요 할때는 1을 추가
    public static LogBoardCountEntity likeCount(String userId,UserPostEntity countEntity){
        LogBoardCountEntity entity = new LogBoardCountEntity();
        entity.setUserId(userId);
        entity.setCountCheck(1);
        entity.setLogBoardEntity(countEntity);
        return entity;
    }
    public static LogBoardCountEntity BadCount(UserPostEntity userPostEntity,String userId){
        LogBoardCountEntity countEntity = new LogBoardCountEntity();

        countEntity.setCountCheck(0);
        countEntity.setLogBoardEntity(userPostEntity);
        countEntity.setUserId(userId);

        return countEntity;
    }
}


