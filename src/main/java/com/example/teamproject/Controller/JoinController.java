package com.example.teamproject.Controller;
import com.example.teamproject.JpaClass.UserDto;
import com.example.teamproject.Repository.MemberRepository;
import com.example.teamproject.Service.MemberService;
import com.example.teamproject.JpaClass.UserInfo.UserInfo;
import com.example.teamproject.JpaClass.UserSatuts.UserResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class JoinController {

    private final MemberService service;
    private final MemberRepository repository;
    private final BCryptPasswordEncoder encoder;

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }
    @GetMapping("/user")
    @ResponseBody
    public String user(){
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
    public ResponseEntity<UserResponse> join(@ModelAttribute UserInfo info){
        info.setRole("ROLE_USER");
        String rawPassWord = info.getPassword();
        String encodePassWord = encoder.encode(rawPassWord);
        info.setPassword(encodePassWord);

        UserInfo nickName = repository.findByName(info.getUsername());

        if(nickName != null){
          return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse("400","이미 있는 사용자"));
        }
        else {
            repository.save(info);
            return ResponseEntity.ok(new UserResponse("200","회원가입 성공"));
        }
    }
    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }
}
