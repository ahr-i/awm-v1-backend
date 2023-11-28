package com.example.teamproject.Service.LocationService;

import com.example.teamproject.Dto.LocationDto.InformationDto;
import com.example.teamproject.Dto.LocationDto.LocationDto;
import com.example.teamproject.Dto.LocationDto.SearchDto;
import com.example.teamproject.Dto.LocationDto.SearchInformationDto;
import com.example.teamproject.JpaClass.LocationTable.Location;
import com.example.teamproject.Repository.LoactionRepository.LocationRepository;
import com.example.teamproject.Setting.LocationSetting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {
    private final LocationSetting setting;
    private final LocationRepository repository;

    public List<LocationDto> findInRange(SearchDto dto) {
        try {
            dto.calculateRange();

            List<Location> result = repository.findLocationsInRange(dto.getLatitude(), dto.getLongitude(), dto.getMaxLatitudeRange(), dto.getMaxLongitudeRange(), setting.getThreshold());
            List<LocationDto> response = LocationDto.locationToLocationDto(result);

            return response;
        } catch (Exception e) {
            log.info(e.getMessage());

            return null;
        }
    }

    public List<LocationDto> findWithinRange(SearchDto dto) {
        try {
            dto.calculateRange();

            List<Location> result = repository.findLocationsWithinRange(dto.getLatitude(), dto.getLongitude(), dto.getMaxLatitudeRange(), dto.getMaxLongitudeRange(), dto.getMinLatitudeRange(), dto.getMinLongitudeRange(), setting.getThreshold());
            List<LocationDto> response = LocationDto.locationToLocationDto(result);

            return response;
        } catch (Exception e) {
            log.info(e.getMessage());

            return null;
        }
    }

    public InformationDto findLocationInformation(SearchInformationDto dto) {
        try {
            int locationId = getLocationId(dto.getLatitude(), dto.getLongitude(), dto.getCategory());
            Optional<Location> result = repository.findById(locationId);

            return InformationDto.locationToInformationDto(result.get());
        } catch (Exception e){
            log.info(e.getMessage());

            return null;
        }
    }

    public int getLocationId(double latitude, double longitude, String category) {
        List<Location> result = repository.findLocationByCategory(latitude, longitude, category);

        if(!result.isEmpty()) {
            return result.get(0).getLocationId();
        } else {
            return -1;
        }
    }
}
