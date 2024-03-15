package com.dedagroup.springboottraining.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final CustomAuthProvider customAuthProvider;

    @Autowired
    public SecurityConfig(CustomAuthProvider customAuthProvider) {
        this.customAuthProvider = customAuthProvider;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()

//                .authorizeHttpRequests().anyRequest().permitAll();
//                .and() //momentaneamente, per angular

                .authorizeHttpRequests()
                .requestMatchers("/home", "/login", "/registration", /*"password_registration",*/ "/registration/password/", "/user/register/**", "/user/register/password/")
                .permitAll()
//              .requestMatchers("/user/**", "/group/**", "/role/**", "/user/create/", "/newUser/")
                .anyRequest()
                .authenticated()
                .and()
//                formLogin( form -> form
//                            .loginPage("/login")
//                            .defaultSuccessUrl("/user/all/view", true)
//                            .failureUrl("/login?error")
//                            .permitAll());
                .httpBasic(Customizer.withDefaults()); //o http.formLogin()
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




}
