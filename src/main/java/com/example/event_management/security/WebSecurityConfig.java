package com.example.event_management.security;

import com.example.event_management.filter.JwtRequestFilter;
import com.example.event_management.service.EvMaUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JwtRequestFilter jwtRequestFilter  ;

    @Autowired
    private EvMaUserDetailService evMaUserDetailService ;

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder() ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
  /*      http    .csrf().disable()
                .cors(cors -> cors.disable())
                .authorizeRequests()
                    .antMatchers("/admin/**" ).hasAuthority("ROLE_ADMIN")
                    .antMatchers("/register/**" ).hasAuthority("ROLE_REGISTER")
                    .antMatchers("/speaker/**" ).hasAuthority("ROLE_SPEAKER")
                    .antMatchers("/" ,"/j_spring_security_check", "/api/**" ,"/create_register","/create_speaker","/home/**","//cdn.ckeditor.com/4.18.0/standard/ckeditor.js","https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js","https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js","https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" , "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" , "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js").permitAll() // Cho phép tất cả mọi người truy cập vào các địa chỉ này
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
                    .permitAll();*/

        http    .csrf().disable()
                .authorizeRequests()
                .antMatchers("/op_api/**","/authenticate").permitAll() // Cho phép tất cả mọi người truy cập vào các địa chỉ này
                .antMatchers("/admin_api/**" ).hasAuthority("ROLE_ADMIN")
                .antMatchers("/register_api/**" ).hasAuthority("ROLE_REGISTER")
                .antMatchers("/speaker_api/**" ).hasAuthority("ROLE_SPEAKER")
                .anyRequest().authenticated().and()
                .exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http    .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(evMaUserDetailService).passwordEncoder(passwordEncoder());
        //auth.authenticationProvider(authProvider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
