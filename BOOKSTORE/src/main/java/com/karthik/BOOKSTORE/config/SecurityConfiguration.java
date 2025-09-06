package com.karthik.BOOKSTORE.config;

import com.karthik.BOOKSTORE.Service.Userdetailservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    Userdetailservice userDetailsService;
    @Autowired
    Jwtfilter jwtfilter;
    @Bean
    public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {
        http.csrf(csrf ->csrf.disable());
        http.authorizeHttpRequests(request -> request.requestMatchers("Bookstore/register","Bookstore/login").permitAll().anyRequest().authenticated());
        http.authenticationProvider(authenticationprovider());
        http.addFilterBefore( jwtfilter, UsernamePasswordAuthenticationFilter.class);
       return http.build();
    }
   @Bean
    AuthenticationProvider authenticationprovider() {
       DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
       auth.setUserDetailsService(userDetailsService);
       auth.setPasswordEncoder(new BCryptPasswordEncoder(12));
       return auth;
    }
    @Bean
    public AuthenticationManager authmanager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
