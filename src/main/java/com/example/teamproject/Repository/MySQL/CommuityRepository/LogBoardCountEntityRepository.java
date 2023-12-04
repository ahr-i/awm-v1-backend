package com.example.teamproject.Repository.MySQL.CommuityRepository;

import com.example.teamproject.JpaClass.CommunityTable.LogBoardCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface LogBoardCountEntityRepository extends JpaRepository<LogBoardCountEntity,Integer> {

//    List<LogBoardCountEntity> findAllByLogBoardEntity_Id(int Id);

    Optional<LogBoardCountEntity> findByLogBoardEntity_IdAndCountCheckAndUserId(int Id,int countCheck,String userId);
}
