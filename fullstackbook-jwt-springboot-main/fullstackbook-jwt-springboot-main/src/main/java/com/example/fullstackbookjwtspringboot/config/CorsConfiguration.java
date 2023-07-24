package com.example.fullstackbookjwtspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {
    @Bean//khi bị từ chối fetchdata trên frontend cần sửa lại allowedOrigins
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "PUT", "POST", "DELETE").allowedOrigins("http://localhost:3000");//https://6609-210-245-52-67.ngrok-free.app
            }
        };
    }
}
