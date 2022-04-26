package com.example.event_management.converter;

import com.example.event_management.DTO.AdminDTO;
import com.example.event_management.DTO.EventDTO;
import com.example.event_management.entity.AdminEntity;
import com.example.event_management.entity.EventEntity;
import com.example.event_management.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class EventConverter {

    @Autowired
    AdminRepository adminRepository ;

    public EventEntity convertToEntity(EventDTO b) {
        EventEntity a = new EventEntity() ;
        AdminEntity c = adminRepository.getById(b.getEvent_admin_id()) ;
        a.setEvent_admin(c);
        a.setEvent_id(b.getEvent_id());
        a.setEvent_name(b.getEvent_name());
        a.setEvent_date(b.getEvent_date());
        a.setEvent_location(b.getEvent_location());
        a.setEvent_ending_time(b.getEvent_ending_time());
        a.setEvent_starting_time(b.getEvent_starting_time());
        return a  ;
    }

    public EventDTO convertToDTO (EventEntity b) {
        EventDTO a = new EventDTO() ;
        a.setEvent_admin_id(b.getEvent_admin().getAdminId());
        a.setEvent_id(b.getEvent_id());
        a.setEvent_name(b.getEvent_name());
        a.setEvent_date(b.getEvent_date());
        a.setEvent_location(b.getEvent_location());
        a.setEvent_ending_time(b.getEvent_ending_time());
        a.setEvent_starting_time(b.getEvent_starting_time());
        return a ;
    }
}
