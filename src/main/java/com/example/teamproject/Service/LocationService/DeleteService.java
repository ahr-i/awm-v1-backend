package com.example.teamproject.Service.LocationService;

import com.example.teamproject.Dto.LocationDto.DeleteDto;
import com.example.teamproject.JpaClass.LocationTable.Location;
import com.example.teamproject.Repository.MySQL.LoactionRepository.LocationRepository;
import com.example.teamproject.Setting.LocationSetting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteService {
    private final LocationRepository locationRepository;
    private final LocationSetting setting;

    public boolean delete(DeleteDto dto) {
        try {
            int existingLocationId = getLocationId(dto.getLatitude(), dto.getLongitude(), dto.getCategory());

            if(existingLocationId != -1) {
                locationRepository.updateScore(existingLocationId, setting.getDeleteBaseScore());
                log.info("locationId: {}", existingLocationId);

                return true;
            }

            return false;
        } catch (Exception e) {
            log.info(e.getMessage());

            return false;
        }
    }

    public int getLocationId(double latitude, double longitude, String category) {
        List<Location> result = locationRepository.findLocationByCategory(latitude, longitude, category);

        if(!result.isEmpty()) {
            return result.get(0).getLocationId();
        } else {
            return -1;
        }
    }
}
