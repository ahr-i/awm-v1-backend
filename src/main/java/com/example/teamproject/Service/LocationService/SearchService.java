package com.example.teamproject.Service.LocationService;

import com.example.teamproject.Dto.LocationDto.*;
import com.example.teamproject.JpaClass.LocationTable.Location;
import com.example.teamproject.JpaClass.LocationTable.LocationImage;
import com.example.teamproject.Repository.LoactionRepository.LocationImageRepository;
import com.example.teamproject.Repository.LoactionRepository.LocationRepository;
import com.example.teamproject.Setting.LocationSetting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {
    private final LocationSetting setting;
    private final LocationRepository repository;
    private final LocationImageRepository locationImageRepository;

    /* 장소 범위 검색 */
    public List<LocationDto> findInRange(SearchDto dto) {
        try {
            // range를 longitude, latitude 값으로 계산함.
            dto.calculateRange();
            // range를 기반으로 범위 검색
            List<Location> result = repository.findLocationsInRange(dto.getLatitude(), dto.getLongitude(), dto.getMaxLatitudeRange(), dto.getMaxLongitudeRange(), setting.getThreshold());
            // Response를 위한 Dto로 변환
            List<LocationDto> response = LocationDto.locationToLocationDto(result);

            return response;
        } catch (Exception e) {
            log.info(e.getMessage());

            return null;
        }
    }

    /* Max Range 안에서 Min Range를 제외한 범위 검색 */
    public List<LocationDto> findWithinRange(SearchDto dto) {
        try {
            // range를 longitude, latitude 값으로 계산함.
            dto.calculateRange();
            // Max Range와 Min Range 범위를 계산해 장소 검색
            List<Location> result = repository.findLocationsWithinRange(dto.getLatitude(), dto.getLongitude(), dto.getMaxLatitudeRange(), dto.getMaxLongitudeRange(), dto.getMinLatitudeRange(), dto.getMinLongitudeRange(), setting.getThreshold());
            // Response를 위한 Dto로 변환
            List<LocationDto> response = LocationDto.locationToLocationDto(result);

            return response;
        } catch (Exception e) {
            log.info(e.getMessage());

            return null;
        }
    }

    /* 장소의 Information 조회 (title, description, images) */
    public InformationDto findLocationInformation(SearchInformationDto dto) {
        try {
            // latitude, longitude, category 기반 location 검색
            int locationId = getLocationId(dto.getLatitude(), dto.getLongitude(), dto.getCategory());
            // locationId로 장소 검색
            // title, description이 필요함.
            Optional<Location> result = repository.findById(locationId);
            // locationId로 해당 장소의 모든 이미지 검색
            List<byte[]> images = locationImageRepository.findByLocationId(locationId);
            // 검색된 이미지를 Dto로 변환
            List<ImageDto> imageDtos = ImageDto.locationImageToDto(images);

            // 해당 장소의 visitCount를 증가
            repository.upVisitCount(locationId);

            // title, description, images의 배열로 변환
            return InformationDto.locationToInformationDto(result.get(), imageDtos);
        } catch (Exception e){
            log.info(e.getMessage());

            return null;
        }
    }

    /* latitude, longitude, category를 기반으로 locationId 검색 */
    public LocationDto findLocationId(SearchInformationDto dto) {
        try {
            // latitude, longitude, category를 기반으로 locationId 검색
            int locationId = getLocationId(dto.getLatitude(), dto.getLongitude(), dto.getCategory());
            LocationDto response = new LocationDto();

            response.setLocationId(locationId);

            return response;
        } catch (Exception e){
            log.info(e.getMessage());

            return null;
        }
    }

    /* latitude, longitude, category 기반 location 검색 */
    public int getLocationId(double latitude, double longitude, String category) {
        List<Location> result = repository.findLocationByCategory(latitude, longitude, category);

        if(!result.isEmpty()) {
            return result.get(0).getLocationId();
        } else {
            return -1;
        }
    }

    /* 선택한 category와 현재 위치, range를 기반으로 장소 추천 */
    public LocationDto recommendLocation(SearchDto dto) {
        try {
            // range를 longitude, latitude로 환산
            dto.calculateRange();

            // 검색 범위
            Pageable pageable = PageRequest.of(0, 1);
            // 검색 범위에 따른 검색 결과
            List<Location> locations = repository.findTopLocationInRange(dto.getLatitude(), dto.getLongitude(), dto.getMaxLatitudeRange(), dto.getMaxLongitudeRange(), setting.getThreshold(), dto.getCategory(), pageable);

            return LocationDto.locationToLocationDto(locations).get(0);
        } catch (Exception e) {
            log.info(e.getMessage());

            return null;
        }
    }

    /*
    public List<ImageDto> findImages(SearchInformationDto dto) {
        try {
            int locationId = getLocationId(dto.getLatitude(), dto.getLongitude(), dto.getCategory());

            List<byte[]> result = locationImageRepository.findByLocationId(locationId);
            List<ImageDto> response = ImageDto.locationImageToDto(result);

            return response;
        } catch (Exception e) {
            log.info(e.getMessage());

            return null;
        }
    }
    */
}
