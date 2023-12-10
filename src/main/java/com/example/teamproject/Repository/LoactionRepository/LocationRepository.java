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
    @Transactional
    @Query("update Location l set l.score = l.score + :score where l.locationId = :locationId")
    void updateScore(@Param("locationId") int locationId, @Param("score") int score);

    @Modifying
    @Transactional
    @Query("update Location l set l.visitCount = l.visitCount + 1 where l.locationId = :locationId")
    void upVisitCount(@Param("locationId") int locationId);

    @Modifying
    @Query("SELECT l FROM Location l WHERE l.latitude BETWEEN (:latitude - :latitudeRange) AND (:latitude + :latitudeRange) " +
            "AND l.longitude BETWEEN (:longitude - :longitudeRange) AND (:longitude + :longitudeRange) " +
            "AND l.score >= :score")
    List<Location> findLocationsInRange(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("latitudeRange") double latitudeRange, @Param("longitudeRange") double longitudeRange, @Param("score") int score);

    @Modifying
    @Query("SELECT l FROM Location l WHERE (l.latitude BETWEEN (:latitude - :maxLatitudeRange) AND (:latitude - :minLatitudeRange) " +
            "OR l.latitude BETWEEN (:latitude + :minLatitudeRange) AND (:latitude + :maxLatitudeRange)) " +
            "AND (l.longitude BETWEEN (:longitude - :maxLongitudeRange) AND (:longitude - :minLongitudeRange) " +
            "OR l.longitude BETWEEN (:longitude + :minLongitudeRange) AND (:longitude + :maxLongitudeRange)) " +
            "AND l.score >= :score")
    List<Location> findLocationsWithinRange(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("maxLatitudeRange") double maxLatitudeRange, @Param("maxLongitudeRange") double maxLongitudeRange, @Param("minLatitudeRange") double minLatitudeRange, @Param("minLongitudeRange") double minLongitudeRange, @Param("score") int score);

    @Query("SELECT l FROM Location l WHERE l.latitude BETWEEN (:latitude - :latitudeRange) AND (:latitude + :latitudeRange) " +
            "AND l.longitude BETWEEN (:longitude - :longitudeRange) AND (:longitude + :longitudeRange) " +
            "AND l.category = :category")
    List<Location> findLocationByCategoryInRange(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("latitudeRange") double latitudeRange, @Param("longitudeRange") double longitudeRange, @Param("category") String category);

    @Query("SELECT l FROM Location l WHERE l.latitude = :latitude AND l.longitude = :longitude AND l.category = :category")
    List<Location> findLocationByCategory(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("category") String category);
}
