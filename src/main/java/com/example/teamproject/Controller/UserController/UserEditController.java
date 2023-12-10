package com.example.teamproject.Controller.UserController;

import com.example.teamproject.Dto.LocationDto.RegisterDto;
import com.example.teamproject.Dto.UserDto;
import com.example.teamproject.Dto.UserProfileDto;
import com.example.teamproject.Service.LocationService.RegisterService;
import com.example.teamproject.Service.SpringSecurityLogin.PrincipalDetails;
import com.example.teamproject.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user/edit")
@RequiredArgsConstructor
@Slf4j
@RestController
public class UserEditController {
    private final UserService service;

    @PostMapping("/profile")
    public ResponseEntity editUserNickName(@RequestBody UserProfileDto dto, Authentication authentication){
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        Boolean result = service.editNickName(dto, principalDetails.getUserInfo().getUserId());

        if(result) {
            return ResponseEntity.ok().body("유저 프로필 변경에 성공했습니다.");
        } else {
            return ResponseEntity.badRequest().body("유저 프로필 변경에 실패했습니다.");
        }
    }
}
