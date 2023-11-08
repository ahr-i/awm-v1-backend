package com.example.teamproject.Controller;

import com.example.teamproject.Dto.UserDto;
import com.example.teamproject.JpaClass.UserTable.User;
import com.example.teamproject.JpaClass.UserSatuts.UserResponse;
import com.example.teamproject.Security.PrincipalDetails;
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

    @GetMapping("/joinForm")
    String joinForm(){
        return "joinForm";
    }
    @GetMapping("/user")
    @ResponseBody
    public String user(Authentication authentication){
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("gkgkgkgk"+principal.getUsername());
        return "유저만 들어올 수 있는 사이트임";
    }
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/manager")
    @ResponseBody
    public String manager(){
        return "매니저 사이트임. 토큰이 있어야 들어올 수 있음";
    }
    @GetMapping("/managerPage")
    public String managerPage(){
        return "manager";
    }


    @PostMapping("/join")
    @ResponseBody
    public ResponseEntity<UserResponse> join(@RequestBody User info){
        String rawPassWord = info.getPassword();
        String encodePassWord = encoder.encode(rawPassWord);
        info.setPassword(encodePassWord);
        User byUserInfo = service.findByUser(info);

        if(byUserInfo != null){
          return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse("400","이미 있는 사용자"));
        }
        else {
            User user = new UserDto().TransferUser(info);
            service.join(user);
            return ResponseEntity.ok(new UserResponse("200","회원가입 성공"));
        }
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }
}
