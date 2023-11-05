package com.example.teamproject.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequestMapping("/user/")
public class MapController {

    //  /user/loginForm

    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
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
