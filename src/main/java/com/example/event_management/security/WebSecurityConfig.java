package com.example.event_management.security;

import com.example.event_management.service.EvMaUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private EvMaUserDetailService evMaUserDetailService ;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder() ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/home", "/home/**" ,"/login" , "/event_detail/**" ,"/create_event", "/create_user" , "/create_registerform" , "/create_speakerform" ).permitAll() // Cho phép tất cả mọi người truy cập vào các địa chỉ này
                    .anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/j_spring_security_check")// Cho phép người dùng xác thực bằng form login
                    .defaultSuccessUrl("/user_home")
                    .permitAll() // Tất cả đều được truy cập vào địa chỉ này
                    .and()
                .logout()
                    .logoutSuccessUrl("/home")// Cho phép logout
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(evMaUserDetailService).passwordEncoder(passwordEncoder());
    }

}
