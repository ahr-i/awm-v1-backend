package com.example.teamproject.Repository.JpaRepository;

import com.example.teamproject.JpaClass.UserTable.CategoryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface CategoryListRepository extends JpaRepository<CategoryList,Integer> {
    public Optional<CategoryList> findByUserId(String userId);

    @Modifying
    @Query("select cl from CategoryList cl where cl.userId != :userId")
    List<CategoryList> findOtherUser(@Param("userId") String userId);
}