package com.example.teamproject.Repository.MySQL.LoactionRepository;

import com.example.teamproject.JpaClass.LocationTable.Contributor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ContributorRepository extends JpaRepository<Contributor,Integer> {

}
