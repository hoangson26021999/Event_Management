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
                    .antMatchers("/admin/**" ).hasAuthority("ROLE_ADMIN")
                    .antMatchers("/register/**" ).hasAuthority("ROLE_REGISTER")
                    .antMatchers("/speaker/**" ).hasAuthority("ROLE_SPEAKER")
                    .antMatchers("/" ,"/create_register","/create_speaker","/home/**" , "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" , "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js").permitAll() // Cho phép tất cả mọi người truy cập vào các địa chỉ này
                    .anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
                    .and()
                .formLogin()
                    .loginPage("/home/login")
                    .loginProcessingUrl("/j_spring_security_check")// Cho phép người dùng xác thực bằng form login
                    .defaultSuccessUrl("/home/user_home")
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
