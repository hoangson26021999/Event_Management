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
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

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
                    .antMatchers("/" ,"/create_register","/create_speaker","/home/**","//cdn.ckeditor.com/4.18.0/standard/ckeditor.js","https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js","https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js","https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" , "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" , "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js").permitAll() // Cho ph??p t???t c??? m???i ng?????i truy c???p v??o c??c ?????a ch??? n??y
                    .anyRequest().authenticated() // T???t c??? c??c request kh??c ?????u c???n ph???i x??c th???c m???i ???????c truy c???p
                    .and()
                .formLogin()
                    .loginPage("/home/login")
                    .loginProcessingUrl("/j_spring_security_check")// Cho ph??p ng?????i d??ng x??c th???c b???ng form login
                    .defaultSuccessUrl("/home/user_home")
                    .permitAll() // T???t c??? ?????u ???????c truy c???p v??o ?????a ch??? n??y
                    .and()
                .logout()
                    .logoutSuccessUrl("/home")// Cho ph??p logout
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(evMaUserDetailService).passwordEncoder(passwordEncoder());
    }

}
