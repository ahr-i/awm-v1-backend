package com.example.teamproject.Filter;


import com.example.teamproject.JWT.JWTUtil;
import com.example.teamproject.JpaClass.UserTable.User;
import com.example.teamproject.Repository.JpaRepository.UserRepository;
import com.example.teamproject.Security.PrincipalDetails;
import com.example.teamproject.JWT.jwtKey.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 시큐리티가 filter를 가지고 있는데 BasicAuthenticationFilter 라는 것이 있음.
 권한이나 인증이 필요한 특정 주소를 요청 했을 때 위 필터를 무조건 탐.
 만약 권한이나 인증이 필요한 주소가 아니라면 이 필터를 안탐.
 */
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository repository;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,UserRepository repository) {
        super(authenticationManager);
        this.repository = repository;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {


        String authorization = request.getHeader("Authorization");

        log.info("토큰 정보 : {}",authorization);

        if(authorization == null || !authorization.startsWith("Bearer ")){
            System.out.println("토큰이 없음.");
            chain.doFilter(request,response);
            return;
        }

        String token = authorization.split(" ")[1];

        if(JWTUtil.isExpired(token,SecretKey.secretKey)){
            chain.doFilter(request,response);
            return;
        }
        String userName = JWTUtil.getUserName(token, SecretKey.secretKey);
        System.out.println(userName);

        if(userName != null){
            User byUserId = repository.findByUserId(userName);

            PrincipalDetails details = new PrincipalDetails(byUserId);
            Authentication authentication = new UsernamePasswordAuthenticationToken(details,null,details.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request,response);
        }



    }

}
