package com.example.teamproject.Service.LocationService;

import com.example.teamproject.Dto.LocationDto.RegisterDto;
import com.example.teamproject.JpaClass.LocationTable.Contributor;
import com.example.teamproject.JpaClass.LocationTable.Location;
import com.example.teamproject.JpaClass.LocationTable.LocationImage;
import com.example.teamproject.Repository.LoactionRepository.ContributorRepository;
import com.example.teamproject.Repository.LoactionRepository.LocationRepository;
import com.example.teamproject.Setting.LocationSetting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterService {
    private final LocationRepository locationRepository;
    private final ContributorRepository contributorRepository;
    private final LocationSetting setting;

    public boolean register(RegisterDto dto, String userId) {
        try {
            int existingLocationId = getLocationId(dto.getLatitude(), dto.getLongitude(), dto.getCategory());

            if(existingLocationId != -1) {
                locationRepository.updateScore(existingLocationId, setting.getRegisterBaseScore());

                return true;
            }
            dto.setUserId(userId);

            Location location = RegisterDto.toLocation(dto);
            Contributor contributor = RegisterDto.toContributor(dto);
            LocationImage locationImage = RegisterDto.toLocationImage(dto);

            int locationId = locationRepository.save(location).getLocationId();

            locationImage.setLocationId(locationId);
            contributor.setLocationId(locationId);
            contributor.setRate(setting.getUserRate());

            locationRepository.updateScore(locationId, setting.getRegisterBaseScore());
            contributorRepository.save(contributor);

            return true;
        } catch (Exception e) {
            log.info(e.getMessage());

            return false;
        }
    }

    public int getLocationId(double latitude, double longitude, String category) {
        List<Location> result = locationRepository.findLocationByCategoryInRange(latitude, longitude, setting.getLatitudeRange(), setting.calculateLongitudeRange(latitude), category);

        if(!result.isEmpty()) {
            return result.get(0).getLocationId();
        } else {
            return -1;
        }
    }
}