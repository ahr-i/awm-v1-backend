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
public class RegisterService {
    private final LocationRepository locationRepository;
    private final RegisterRepository registerRepository;
    private final ContributorRepository contributorRepository;

    public boolean register(RegisterDto dto) {
        try {
            int existingLocationId = getLocationId(dto.getLatitude(), dto.getLongitude(), dto.getCategory());
            if(existingLocationId != -1) {
                locationRepository.updateScore(existingLocationId);

                return true;
            }

            LocalDateTime time = LocalDateTime.now();

            /*
             *  Dto To Entry??
             */
            Location location = new Location();
            LocationRegister locationRegister = new LocationRegister();
            Contributor contributor = new Contributor();

            /* -- */
            //Location location = RegisterDto.toLocation(dto);
            /* -- */

            location.setLatitude(dto.getLatitude());
            location.setLongitude(dto.getLongitude());
            location.setCreatedAt(time);
            location.setCategory(dto.getCategory());

            locationRegister.setUserId(dto.getUserId());
            locationRegister.setCreatedAt(time);

            contributor.setUserId(dto.getUserId());
            contributor.setRate(5); // 변경 필요

            int locationId = locationRepository.save(location).getLocationId();

            locationRepository.updateScore(locationId);
            locationRegister.setLocationId(locationId);
            int registerId = registerRepository.save(locationRegister).getRegisterId();

            contributor.setRegisterId(registerId);
            contributorRepository.save(contributor);

            return true;
        } catch (Exception e) {
            log.info(e.getMessage());

            return false;
        }
    }

    private int getLocationId(double latitude, double longitude, String category) {
        double range = 50; // 변경 필요

        List<Location> result = locationRepository.findLocationByCategoryInRange(latitude, longitude, range, category);

        if(!result.isEmpty()) {
            return result.get(0).getLocationId();
        } else {
            return -1;
        }
    }
}
