package com.example.teamproject.Repository.CommuityRepository;

import com.example.teamproject.JpaClass.CommunityTable.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {
    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits +1 where b.id =:postId")
    void updateHit(@Param("postId") int id);
    void removeById(int id);
    Page<BoardEntity> findAllByLocation_LocationId(int id, Pageable pageable);

    List<BoardEntity> findAllByLocation_LocationId(int locationId);
}
