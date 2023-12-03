package com.example.teamproject.Repository.LoactionRepository;

import com.example.teamproject.JpaClass.LocationTable.Contributor;
import com.example.teamproject.JpaClass.LocationTable.Location;
import com.example.teamproject.JpaClass.LocationTable.LocationImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface LocationImageRepository extends JpaRepository<LocationImage,Integer> {
    @Modifying
    @Query("select l.image from LocationImage l where l.locationId = :locationId")
    List<byte[]> findByLocationId(@Param("locationId") int locationId);
}
