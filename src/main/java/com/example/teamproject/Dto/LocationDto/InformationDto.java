package com.example.teamproject.Dto.LocationDto;

import com.example.teamproject.JpaClass.LocationTable.Location;
import lombok.Data;

@Data
public class InformationDto {
    private String title;
    private String description;

    static public InformationDto locationToInformationDto(Location location) {
        InformationDto informationDto = new InformationDto();

        informationDto.setTitle(location.getTitle());
        informationDto.setDescription(location.getDescription());

        return informationDto;
    }
}
