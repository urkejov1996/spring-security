package com.springsecurity.basicAuthentication;

import com.springsecurity.enums.RoleEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth -> {
                    auth.anyRequest().authenticated();
                }
        );
        //  http.formLogin();
        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {

        var user = User.withUsername("urke_jov")
                .password("{noop}dummy")
                .roles(RoleEnum.USER.name())
                .build();

        var admin = User.withUsername("admin")
                .password("{noop}dummy")
                .roles(RoleEnum.ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }


}
