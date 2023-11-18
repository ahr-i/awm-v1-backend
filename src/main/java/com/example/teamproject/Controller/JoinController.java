package com.example.teamproject.Controller;

import com.example.teamproject.Dto.UserDto;
import com.example.teamproject.Service.SpringSecurityLogin.PrincipalDetails;
import com.example.teamproject.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class JoinController {

    private final BCryptPasswordEncoder encoder;
    private final UserService service;


    @GetMapping("/user")
    @ResponseBody
    public String user(Authentication authentication){
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        return "유저만 들어올 수 있는 사이트임";
    }
    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }


    @PostMapping("/join")
    @ResponseBody
    public ResponseEntity join(@RequestBody UserDto info){

        String rockPassword = encoder.encode(info.getPassword());
        info.setPassword(rockPassword);

        UserDto byUserInfo = service.findByUser(info);

        if(byUserInfo != null){
          return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 있는 사용자 이거나 회원가입을 할 수 없습니다.");
        } else service.join(info);
        return ResponseEntity.ok().body("회원가입이 완료 되었습니다.");
    }

}
