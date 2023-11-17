package com.example.teamproject.Filter;


import com.example.teamproject.JWT.JWTUtil;
import com.example.teamproject.JpaClass.UserTable.Oauth2UserEntity;
import com.example.teamproject.JpaClass.UserTable.User;
import com.example.teamproject.Repository.JpaRepository.UserRepository;
import com.example.teamproject.Repository.Oauth2Repository.Oauth2Repository;
import com.example.teamproject.Service.SpringSecurityLogin.PrincipalDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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
    private Oauth2Repository oauth2Repository;


    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository repository, Oauth2Repository oauth2Repository) {
        super(authenticationManager);
        this.repository = repository;
        this.oauth2Repository = oauth2Repository;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String authorization;



        authorization = request.getHeader("Authorization");


        if (!request.getRequestURI().startsWith("/user/")) {
            chain.doFilter(request, response);
            return;
        }

        //더 이상 로직을 실행하지말고 401에러 내리고 이 메서드 종료
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {


            String token = authorization.split(" ")[1];
            Claims userName = JWTUtil.getUserName(token);
            String provider = userName.get("provider", String.class);
            if (userName != null) {

                if(provider.equals("google") || provider.equals("naver")){
                    String oauth2UserProviderId = userName.get("username",String.class);
                    Oauth2UserEntity entity = oauth2Repository.findByProviderUserId(oauth2UserProviderId).get();
                    PrincipalDetails details = new PrincipalDetails(entity);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(details,null,details.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    chain.doFilter(request,response);
                }
                else {

                    String userId = userName.get("username", String.class);
                    User byUserId = repository.findByUserId(userId);

                    PrincipalDetails details = new PrincipalDetails(byUserId);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    chain.doFilter(request, response);
                }
            }
        } catch (SignatureException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }


}

