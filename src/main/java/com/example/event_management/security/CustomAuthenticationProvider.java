package com.example.event_management.security;

import com.example.event_management.entity.AdminEntity;
import com.example.event_management.entity.RegisterEntity;
import com.example.event_management.entity.SpeakerEntity;
import com.example.event_management.repository.AdminRepository;
import com.example.event_management.repository.RegisterRepository;
import com.example.event_management.repository.SpeakerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AdminRepository adminRepository ;

    @Autowired
    private RegisterRepository registerRepository ;

    @Autowired
    private SpeakerRepository speakerRepository ;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        AdminEntity a = adminRepository.findAdminEntityByAdminAccountName(username) ;
        RegisterEntity b = registerRepository.findRegisterEntityByRegisterAccountName(username) ;
        SpeakerEntity c = speakerRepository.findSpeakerEntityBySpeakerAccountName(username) ;

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (a != null) {
            String comparePassword = a.getAdminAccountPassword();
            if (passwordEncoder.matches(password, comparePassword)){
                return new UsernamePasswordAuthenticationToken(username, password,
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
            }
        } else if (b != null) {
            String comparePassword = b.getRegisterAccountPassword();
            if (passwordEncoder.matches(password, comparePassword)){
                return new UsernamePasswordAuthenticationToken(username, password,
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_REGISTER")));
            }
        } else if (c != null) {

            String comparePassword = c.getSpeakerAccountPassword();
            if (passwordEncoder.matches(password, comparePassword)){
                return new UsernamePasswordAuthenticationToken(username, password,
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_SPEAKER")));
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
