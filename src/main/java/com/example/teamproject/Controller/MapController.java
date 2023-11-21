package com.example.teamproject.Controller;

import com.example.teamproject.Service.SpringSecurityLogin.PrincipalDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Slf4j

public class MapController {





    @GetMapping("/map")
    public String map(){
        log.info("카카오 API TEST");
        return "Map";
    }
    @ResponseBody
    @GetMapping("/user/t1")
    public String user(Authentication authentication){
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        log.info(principal.getUserInfo().getUserId());
        log.info("오어스 정보 {} " +principal.getUserInfo());
        return "userTeST";
    }

    @GetMapping("/myLocation")
    public String myLocation(Authentication authentication){

        return "map";
    }
}
