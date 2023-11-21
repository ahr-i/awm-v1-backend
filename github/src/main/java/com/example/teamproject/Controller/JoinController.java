package com.example.teamproject.Controller;

import com.example.teamproject.Dto.UserDto;
import com.example.teamproject.JpaClass.UserTable.User;
import com.example.teamproject.JpaClass.UserSatuts.UserResponse;
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


    @PostMapping("/join")
    @ResponseBody
    public ResponseEntity<UserResponse> join(@RequestBody User info){
        String rawPassWord = info.getPassword();
        String encodePassWord = encoder.encode(rawPassWord);
        info.setPassword(encodePassWord);
        User byUserInfo = service.findByUser(info);

        if(byUserInfo != null){
          return   ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else {
            User user = new UserDto().TransferUser(info);
            service.join(user);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }
}
