package com.example.teamproject.Repository.LoactionRepository;

import com.example.teamproject.JpaClass.LocationTable.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface LocationRepository extends JpaRepository<Location,Integer> {
    @Modifying
    @Query(value = "update Location l set l.score = l.score + 100 where l.locationId =:locationId")  // 변경 필요
    void updateScore(@Param("locationId") int id);

    @Modifying
    @Query("SELECT l FROM Location l WHERE l.latitude BETWEEN (:latitude - :range) AND (:latitude + :range) AND l.longitude BETWEEN (:longitude - :range) AND (:longitude + :range) AND l.score >= 100")
    List<Location> findLocationsInRange(double latitude, double longitude, double range);

    @Query("SELECT l FROM Location l WHERE l.latitude BETWEEN :latitude - :range AND :latitude + :range AND l.longitude BETWEEN :longitude - :range AND :longitude + :range AND l.category = :category")
    List<Location> findLocationByCategoryInRange(double latitude, double longitude, double range, String category);
}
