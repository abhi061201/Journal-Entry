package com.journalEntry.journal.Entry.config;

import com.journalEntry.journal.Entry.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpClient;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws  Exception{
        return http.authorizeHttpRequests(request->
                request
                        .requestMatchers("/user/**").authenticated()
                        .anyRequest().permitAll()
                )
                .build();
    }

    @Autowired
    public void configureGlobal (AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }





}
