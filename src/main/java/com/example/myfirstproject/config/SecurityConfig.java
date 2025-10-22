package com.example.myfirstproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("api/public/**").permitAll()
                                .requestMatchers("api/user/**").hasAnyRole("ADMIN","USER")
                                .requestMatchers("api/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
        ).httpBasic(httpBasicAuth -> {httpBasicAuth = httpBasicAuth;});
        return http.build();
    }
    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user= User.withUsername("user").password(passwordEncoder().encode("1111")).roles("USER").build();
        UserDetails admin= User.withUsername("admin").password(passwordEncoder().encode("1111")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
