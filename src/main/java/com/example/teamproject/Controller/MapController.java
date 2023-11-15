package com.example.teamproject.Controller;

import com.example.teamproject.Service.SpringSecurityLogin.PrincipalDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Slf4j
@RequestMapping("/user/")
public class MapController {

    @ResponseBody
    @GetMapping("result")
    public String test(Authentication authentication){
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        System.out.println(principal.getUsername());
        return "ok";
    }



    @GetMapping("/map")
    public String map(){
        log.info("카카오 API TEST");
        return "Map";
    }

    @GetMapping("/myLocation")
    public String myLocation(){
        return "map";
    }
}
