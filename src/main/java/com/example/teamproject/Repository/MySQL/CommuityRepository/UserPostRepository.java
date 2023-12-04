package com.example.teamproject.Repository.MySQL.CommuityRepository;

import com.example.teamproject.JpaClass.CommunityTable.UserPostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserPostRepository extends JpaRepository<UserPostEntity,Integer> {

    Page<UserPostEntity> findAllByLocation_LocationId(int locationId, Pageable page);

    @Modifying
    @Query(value = "update UserPostEntity  b set b.likeCount = b.likeCount +1 where b.id =:id")
    void updateHit(@Param("id") int id);

    @Modifying
    @Query(value = "update UserPostEntity b set b.badCount = b.badCount +1 where b.id =:id")
    void updateBadCountHit(@Param("id") int id);


}
