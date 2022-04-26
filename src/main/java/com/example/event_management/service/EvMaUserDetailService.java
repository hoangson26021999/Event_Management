package com.example.event_management.service;

import com.example.event_management.entity.AdminEntity;
import com.example.event_management.entity.RegisterEntity;
import com.example.event_management.entity.SpeakerEntity;
import com.example.event_management.repository.AdminRepository;
import com.example.event_management.repository.RegisterRepository;
import com.example.event_management.repository.SpeakerRepository;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvMaUserDetailService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository ;

    @Autowired
    private RegisterRepository registerRepository ;

    @Autowired
    private SpeakerRepository speakerRepository ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            AdminEntity a = adminRepository.findAdminEntityByAdminAccountName(username) ;
            RegisterEntity b = registerRepository.findRegisterEntityByRegisterAccountName(username) ;
            SpeakerEntity c = speakerRepository.findSpeakerEntityBySpeakerAccountName(username) ;

            List<GrantedAuthority> grantList = new ArrayList<>();
            GrantedAuthority admin_authority = new SimpleGrantedAuthority("ADMIN");
            GrantedAuthority register_authority = new SimpleGrantedAuthority("REGISTER");
            GrantedAuthority speaker_authority = new SimpleGrantedAuthority("SPEAKER");

            if (a != null) {
                grantList.add(admin_authority) ;
                UserDetails userDetails = new User(a.getAdminAccountName(), a.getAdminAccountPassword(), grantList) ;
                return  userDetails ;
            } else if ( b != null) {
                grantList.add(register_authority) ;
                UserDetails userDetails = new User(b.getRegisterAccountName(), b.getRegisterAccountPassword(), grantList) ;
                return  userDetails ;
            } else  if (c != null) {
                grantList.add(speaker_authority) ;
                UserDetails userDetails = new User(c.getSpeakerAccountName(), c.getSpeakerAccountPassword(), grantList) ;
                return  userDetails ;
            } else {
                new UsernameNotFoundException("Login fail!") ;
            }

            return null;
    }
}
