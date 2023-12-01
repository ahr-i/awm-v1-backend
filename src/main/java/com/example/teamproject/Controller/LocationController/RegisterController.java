package com.example.teamproject.Controller.LocationController;

import com.example.teamproject.Dto.LocationDto.RegisterDto;
import com.example.teamproject.Service.LocationService.RegisterService;
import com.example.teamproject.Service.SpringSecurityLogin.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user/location")
@RequiredArgsConstructor
@Slf4j
@RestController
public class RegisterController {
    private final RegisterService service;
    @PostMapping("/register")
    public ResponseEntity registerLocation(@RequestBody RegisterDto dto, Authentication authentication){
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        Boolean result = service.register(dto, principalDetails.getUserInfo().getUserId());
        if(result) {
            return ResponseEntity.ok().body("장소 등록에 성공했습니다.");
        } else {return ResponseEntity.badRequest().body("장소 등록에 실패했습니다.");}
    }

}
