package com.example.teamproject.Filter;

import com.example.teamproject.Dto.UserDto;
import com.example.teamproject.JWT.JWTUtil;
import com.example.teamproject.JpaClass.UserTable.UserEntity;
import com.example.teamproject.Repository.JpaRepository.UserRepository;
import com.example.teamproject.Service.SpringSecurityLogin.PrincipalDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class JwtAuthentication extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager manager;
    private final UserRepository repository;



    public JwtAuthentication(AuthenticationManager manager,UserRepository repository){
        super(manager);
        this.manager = manager;
        this.repository = repository;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserDto user = objectMapper.readValue(request.getInputStream(), UserDto.class);
            //인증 과정아 사용되는 클래스 -> 아이디랑 비밀번호랑 담아서 시큐리티한테 인증 요청 보냄
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserId()
                    ,user.getPassword(), List.of(new SimpleGrantedAuthority("USER")));
            /**토큰을 시도해 로그인 시도를 해보고 로그인이 정상적으로 만들어지면 authenticate가 만들어짐
             * 즉 DB에 있는 값과 같다.
             * 여기서 loadUserByusername에 들어가서 검증한다.
             */
            Authentication authenticate = manager.authenticate(token);



            return authenticate; // < -- 반드시 authenticate가 되어야 한다 리턴값은
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    //위에 메서드가 정상적으로 끝나면  이 메서드가 실행됨.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principal = (PrincipalDetails) authResult.getPrincipal();
        String jwtToken = JWTUtil.createJwt(principal.getUserInfo());
        Optional<UserEntity> byUserId = repository.findByUserId(principal.getUserInfo().getUserId());

        if(byUserId.isPresent()) {
            UserDto dto = UserDto.UserEntityToUserDto(byUserId.get());
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            String jsonUser = mapper.writeValueAsString(dto);
            response.addHeader("Authorization","Bearer "+jwtToken);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonUser);
            log.info("토큰 정보 : ",jwtToken);
        }


    }
}