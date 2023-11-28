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
    @Query(value = "update Location l set l.score = l.score + :score where l.locationId =:locationId")
    void updateScore(int locationId, int score);

    @Modifying
    @Query("SELECT l FROM Location l WHERE l.latitude BETWEEN (:latitude - :latitudeRange) AND (:latitude + :latitudeRange) AND l.longitude BETWEEN (:longitude - :longitudeRange) AND (:longitude + :longitudeRange) AND l.score >= :score")
    List<Location> findLocationsInRange(double latitude, double longitude, double latitudeRange, double longitudeRange, int score);

    @Modifying
    @Query("SELECT l FROM Location l WHERE (l.latitude BETWEEN (:latitude - :maxLatitudeRange) AND (:latitude - :minLatitudeRange) OR l.latitude BETWEEN (:latitude + :minLatitudeRange) AND (:latitude + :maxLatitudeRange)) AND (l.longitude BETWEEN (:longitude - :maxLongitudeRange) AND (:longitude - :minLongitudeRange) OR l.longitude BETWEEN (:longitude + :minLongitudeRange) AND (:longitude + :maxLongitudeRange)) AND l.score >= :score")
    List<Location> findLocationsWithinRange(double latitude, double longitude, double maxLatitudeRange, double maxLongitudeRange, double minLatitudeRange, double minLongitudeRange, int score);

    @Query("SELECT l FROM Location l WHERE l.latitude BETWEEN (:latitude - :latitudeRange) AND (:latitude + :latitudeRange) AND l.longitude BETWEEN (:longitude - :longitudeRange) AND (:longitude + :longitudeRange) AND l.category = :category")
    List<Location> findLocationByCategoryInRange(double latitude, double longitude, double latitudeRange, double longitudeRange, String category);

    @Query("SELECT l FROM Location l WHERE l.latitude = :latitude AND l.longitude = :longitude AND l.category = :category")
    List<Location> findLocationByCategory(double latitude, double longitude, String category);
}
