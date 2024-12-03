package com.ensta.myfilmlist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .antMatchers(HttpMethod.GET, "/film/**", "/realisateur/**", "/film", "/realisateur").permitAll()
                        .antMatchers(HttpMethod.POST, "/film", "/realisateur").permitAll()
                        .antMatchers(HttpMethod.PUT, "/film/**", "/realisateur/**").permitAll()
                        .antMatchers(HttpMethod.DELETE, "/film/**", "/realisateur/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }
}