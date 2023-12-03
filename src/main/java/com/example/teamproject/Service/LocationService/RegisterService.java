package com.example.teamproject.Service.LocationService;

import com.example.teamproject.Dto.LocationDto.RegisterDto;
import com.example.teamproject.JpaClass.LocationTable.Contributor;
import com.example.teamproject.JpaClass.LocationTable.Location;
import com.example.teamproject.JpaClass.LocationTable.LocationImage;
import com.example.teamproject.Repository.LoactionRepository.ContributorRepository;
import com.example.teamproject.Repository.LoactionRepository.LocationImageRepository;
import com.example.teamproject.Repository.LoactionRepository.LocationRepository;
import com.example.teamproject.Setting.LocationSetting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterService {
    private final LocationRepository locationRepository;
    private final ContributorRepository contributorRepository;
    private final LocationImageRepository locationImageRepository;
    private final LocationSetting setting;

    public boolean register(RegisterDto dto, String userId) {
        try {
            dto.setUserId(userId);

            Location location = RegisterDto.toLocation(dto);
            Contributor contributor = RegisterDto.toContributor(dto);

            int existingLocationId = getLocationId(dto.getLatitude(), dto.getLongitude(), dto.getCategory());
            int locationId = 0;

            if(existingLocationId != -1) {
                locationId = existingLocationId;
                location.setLocationId(locationId);
                Optional<Location> existingLocation = locationRepository.findById(locationId);

                if (existingLocation.isPresent()) {
                    Location updatedLocation = existingLocation.get();

                    updatedLocation.setTitle(dto.getTitle());
                    updatedLocation.setDescription(dto.getDescription());
                }
            } else {
                locationId = locationRepository.save(location).getLocationId();
            }

            if(dto.getImage() != null) {
                LocationImage locationImage = RegisterDto.toLocationImage(dto);

                locationImage.setLocationId(locationId);

                locationImageRepository.save(locationImage);
            }
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
