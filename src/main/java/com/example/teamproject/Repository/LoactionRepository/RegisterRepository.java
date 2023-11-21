package com.example.teamproject.Repository.LoactionRepository;

import com.example.teamproject.JpaClass.LocationTable.Contributor;
import com.example.teamproject.JpaClass.LocationTable.LocationRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface RegisterRepository extends JpaRepository<LocationRegister,Integer> {

}
