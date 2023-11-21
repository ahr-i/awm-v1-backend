package com.example.teamproject.Config;
import com.example.teamproject.Dto.OauthDto;
import com.example.teamproject.Dto.UserDto;
import com.example.teamproject.Filter.JWTAuthorizationFilter;
import com.example.teamproject.Filter.JwtAuthentication;
import com.example.teamproject.JWT.JWTUtil;
import com.example.teamproject.Repository.JpaRepository.UserRepository;
import com.example.teamproject.Repository.Oauth2Repository.Oauth2Repository;
import com.example.teamproject.Service.SpringSecurityLogin.PrincipalDetails;
import com.example.teamproject.Service.SpringSecurityLogin.PrincipalOauth2UserService;
import com.example.teamproject.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final UserRepository repository;
    private final PrincipalOauth2UserService service;
    private final Oauth2Repository oauth2Repository;




    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 구글 API 설명
     * 1. 코드받기 (인증)
     * 2. 액세스 토큰(권한)
     * 3. 사용자 프로필 가져옴
     * 4. 그 정보를 토대로 회원가입 시킴
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http,AuthenticationConfiguration configuration) throws Exception {
       http.csrf(AbstractHttpConfigurer :: disable);
       http.httpBasic(AbstractHttpConfigurer :: disable);
       http.formLogin(AbstractHttpConfigurer :: disable);
       http.addFilterBefore(new JwtAuthentication(configuration.getAuthenticationManager()), UsernamePasswordAuthenticationFilter.class);
       http.addFilter(new JWTAuthorizationFilter(configuration.getAuthenticationManager(),repository,oauth2Repository));



       http.authorizeRequests(auth ->
               auth.antMatchers("/user/**").authenticated()
                       .antMatchers("/join").permitAll()
                       .anyRequest().permitAll());

       http.sessionManagement(session ->
               session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.oauth2Login(oauth2 -> oauth2.successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
                String jwtToken = JWTUtil.createOauthJwt(principal);
                ObjectMapper mapper = new ObjectMapper();
                UserDto userDto = principal.getUserInfo();
                log.info("토큰 정보 : {}",jwtToken);
                String oauthUserInfo = mapper.writeValueAsString(userDto);
                response.setContentType("application/json");
                response.addHeader("Authorization","Bearer "+ jwtToken);
                response.setStatus(HttpServletResponse.SC_OK);
                PrintWriter writer = response.getWriter();
                writer.write(oauthUserInfo);
                writer.flush();
            }
        }));

        return http.build();

    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public CorsConfigurationSource source(){
        CorsConfiguration configuration = new CorsConfiguration();
        // 모든 HTTP 메서드 허용
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        // 모든 헤더 허용
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // 신뢰할 수 있는 특정 오리진 혹은 패턴 지정
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        // 신임 정보 허용
        configuration.setAllowCredentials(true);
        // 모든 경로에 대해 적용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

        }

