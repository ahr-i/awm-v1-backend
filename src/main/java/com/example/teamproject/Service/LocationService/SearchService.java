package com.example.teamproject.Service.LocationService;

import com.example.teamproject.Dto.LocationDto.RegisterDto;
import com.example.teamproject.JpaClass.LocationTable.Contributor;
import com.example.teamproject.JpaClass.LocationTable.Location;
import com.example.teamproject.JpaClass.LocationTable.LocationRegister;
import com.example.teamproject.Repository.LoactionRepository.ContributorRepository;
import com.example.teamproject.Repository.LoactionRepository.LocationRepository;
import com.example.teamproject.Repository.LoactionRepository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {
    private final LocationRepository repository;

    public List<Location> findNearBy(double latitude, double longitude, double range) {
        try {
            return repository.findLocationsInRange(latitude, longitude, range);
        } catch (Exception e) {
            log.info(e.getMessage());

            return null;
        }
    }
}
