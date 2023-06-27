package com.example.fullstackbookjwtspringboot.filter;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TestFilter extends OncePerRequestFilter {
    @Value("${fullstackbook.app.jwtSecret}")
    private String jwtSecret;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        System.out.println("tokenHeader = " + tokenHeader);
        if(tokenHeader != null && tokenHeader.startsWith("Bearer")){
            String token = tokenHeader.substring(7);
            try{
                Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
                System.out.println("Token is valid");
            } catch (JwtException e){
                System.out.println("Token is invalid");
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }
}
