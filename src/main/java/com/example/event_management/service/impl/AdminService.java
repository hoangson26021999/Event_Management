package com.example.event_management.service.impl;

import com.example.event_management.DTO.AdminDTO;
import com.example.event_management.DTO.EventDTO;
import com.example.event_management.converter.EventConverter;
import com.example.event_management.entity.AdminEntity;
import com.example.event_management.entity.EventEntity;
import com.example.event_management.repository.AdminRepository;
import com.example.event_management.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService implements IAdminService {

    @Autowired
    AdminRepository adminRepository ;

    @Autowired
    EventConverter eventConverter;

    @Override
    public List<EventDTO> getEventsbyAdminId() {

        List<EventDTO> admin_events = new ArrayList<>() ;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername() ;
        AdminEntity admin =  adminRepository.findAdminEntityByAdminAccountName(username);

        for (EventEntity event : admin.getEvents() ) {
            admin_events.add(eventConverter.convertToDTO(event)) ;
        }

        return admin_events ;
    }
}
