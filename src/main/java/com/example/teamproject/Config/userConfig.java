package com.example.teamproject.Config;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class userConfig implements WebMvcConfigurer {
    private final EntityManager manager;
    @Bean
    public JPAQueryFactory factory(){
        return new JPAQueryFactory(manager);
    }
}
