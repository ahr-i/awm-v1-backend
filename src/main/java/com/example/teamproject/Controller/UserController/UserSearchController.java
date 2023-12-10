package com.example.teamproject.Controller.UserController;

import com.example.teamproject.Dto.UserProfileDto;
import com.example.teamproject.Service.SpringSecurityLogin.PrincipalDetails;
import com.example.teamproject.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user/search")
@RequiredArgsConstructor
@Slf4j
@RestController
public class UserSearchController {
    private final UserService service;

    @GetMapping("/similar-user")
    public ResponseEntity editUserNickName(Authentication authentication){
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        UserProfileDto response = service.searchSimilarUser(principalDetails.getUserInfo().getUserId());

        if(response != null) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body("관심 일치 유저를 찾을 수 없습니다.");
        }
    }
}
