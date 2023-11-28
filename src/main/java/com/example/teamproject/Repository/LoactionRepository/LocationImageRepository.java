package com.example.teamproject.Repository.LoactionRepository;

import com.example.teamproject.JpaClass.LocationTable.Contributor;
import com.example.teamproject.JpaClass.LocationTable.LocationImage;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface LocationImageRepository extends JpaRepository<LocationImage,Integer> {

}
