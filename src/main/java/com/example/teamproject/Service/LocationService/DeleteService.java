package com.example.teamproject.Service.LocationService;

import com.example.teamproject.Dto.LocationDto.DeleteDto;
import com.example.teamproject.Dto.LocationDto.RegisterDto;
import com.example.teamproject.JpaClass.LocationTable.Contributor;
import com.example.teamproject.JpaClass.LocationTable.Location;
import com.example.teamproject.JpaClass.LocationTable.LocationImage;
import com.example.teamproject.Repository.LoactionRepository.LocationRepository;
import com.example.teamproject.Setting.LocationSetting;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteService {
    private final LocationRepository locationRepository;
    private final LocationSetting setting;

    /* 기존 장소 삭제 */
    public boolean delete(DeleteDto dto) {
        try {
            // latitude, longitude, category를 통해 기존 장소가 있는지 검색
            int existingLocationId = getLocationId(dto.getLatitude(), dto.getLongitude(), dto.getCategory());

            // 기존 장소가 있는 경우
            // 점수를 차감시킴
            if(existingLocationId != -1) {
                locationRepository.updateScore(existingLocationId, setting.getDeleteBaseScore());
                //log.info("locationId: {}", existingLocationId);

                return true;
            }

            // 기존 장소가 없는 경우
            return false;
        } catch (Exception e) {
            log.info(e.getMessage());

            return false;
        }
    }

    /* latitude, longitude, category를 기반으로 기존의 장소 ID 검색 */
    public int getLocationId(double latitude, double longitude, String category) {
        // latitude, longitude, category로 장소를 찾음
        List<Location> result = locationRepository.findLocationByCategory(latitude, longitude, category);

        if(!result.isEmpty()) {
            // 기존 장소가 있는 경우
            return result.get(0).getLocationId();
        } else {
            // 검색된 장소가 없는 경우
            return -1;
        }
    }
}
