package com.example.teamproject.Controller.LocationController;

import com.example.teamproject.JpaClass.LocationTable.Location;
import com.example.teamproject.Service.LocationService.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/location/search")
@RequiredArgsConstructor
@Slf4j
@RestController
public class SearchController {
    private final SearchService service;

    @GetMapping("/nearby")
    public ResponseEntity registerLocation(@RequestParam double latitude,
                                           @RequestParam double longitude,
                                           @RequestParam double range){
        List<Location> result = service.findNearBy(latitude, longitude, range);

        if(result != null) {
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.badRequest().body("검색된 장소가 없습니다.");
        }
    }
}
