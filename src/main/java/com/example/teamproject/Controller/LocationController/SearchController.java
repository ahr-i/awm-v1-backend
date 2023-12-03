package com.example.teamproject.Controller.LocationController;

import com.example.teamproject.Dto.LocationDto.*;
import com.example.teamproject.JpaClass.LocationTable.Location;
import com.example.teamproject.Service.LocationService.SearchService;
import com.example.teamproject.Service.SpringSecurityLogin.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/location/search")
@RequiredArgsConstructor
@Slf4j
@RestController
public class SearchController {
    private final SearchService service;

    @GetMapping("/in-range")
    public ResponseEntity searchInRangeLocation(@ModelAttribute SearchDto dto) {
        List<LocationDto> response = service.findInRange(dto);

        if(response != null) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body("in-range에 문제가 발생했습니다.");
        }
    }

    @GetMapping("/within-range")
    public ResponseEntity searchWithinRangeLocation(@ModelAttribute SearchDto dto) {
        List<LocationDto> response = service.findWithinRange(dto);

        if(response != null) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body("within-range에 문제가 발생했습니다.");
        }
    }

    @GetMapping("/information")
    public ResponseEntity searchLocationInformation(@ModelAttribute SearchInformationDto dto) {
        InformationDto response = service.findLocationInformation(dto);

        if(response != null) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body("찾을 수 없는 장소입니다.");
        }
    }

    /*
    @GetMapping("/images")
    public ResponseEntity searchLocationImages(@ModelAttribute SearchInformationDto dto) {
        List<ImageDto> response = service.findImages(dto);

        if(response != null) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body("해당하는 장소가 없습니다.");
        }
    }
    */
}
