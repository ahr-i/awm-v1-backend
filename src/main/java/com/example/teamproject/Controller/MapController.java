package com.example.teamproject.Controller;

import com.example.teamproject.JpaClass.UserTable.User;
import com.example.teamproject.Security.PrincipalDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


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
