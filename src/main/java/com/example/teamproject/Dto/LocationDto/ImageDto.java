package com.example.teamproject.Dto.LocationDto;

import com.example.teamproject.JpaClass.LocationTable.Location;
import com.example.teamproject.JpaClass.LocationTable.LocationImage;
import lombok.Data;
import org.springframework.web.servlet.tags.form.OptionsTag;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class ImageDto {
    private byte[] image;

    public static List<ImageDto> locationImageToDto(List<byte[]> locationImages) {
        return locationImages.stream().map(image -> {
            ImageDto dto = new ImageDto();

            dto.setImage(image);

            return dto;
        }).collect(Collectors.toList());
    }
}
