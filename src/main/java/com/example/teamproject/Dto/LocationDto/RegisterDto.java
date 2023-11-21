package com.example.teamproject.Dto.LocationDto;

import com.example.teamproject.JpaClass.LocationTable.Location;
import lombok.Data;

@Data
public class RegisterDto {
    private double latitude;
    private double longitude;
    private String userId;
    private String category;

    static public Location toLocation(RegisterDto dto) {
        Location location = new Location();

        location.setLatitude(dto.getLatitude());
        location.setLongitude(dto.getLongitude());
        location.setCategory(dto.getCategory());

        return location;
    }
}
