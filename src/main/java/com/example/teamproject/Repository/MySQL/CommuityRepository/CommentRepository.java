package com.example.teamproject.Repository.MySQL.CommuityRepository;

import com.example.teamproject.JpaClass.CommunityTable.BoardEntity;
import com.example.teamproject.JpaClass.CommunityTable.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * BoardId를 기준으로 할때에는 BoardEntity == Entitiy를 사용하자.
 */
@Transactional
public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {

    List<CommentEntity> findAllByEntityOrderByCreatTimeDesc(BoardEntity entity);
    Long countAllByEntity(BoardEntity entity);
}
