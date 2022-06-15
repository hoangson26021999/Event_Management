package com.example.event_management.converter;

import com.example.event_management.dto.EventDTO;
import com.example.event_management.dto.EventFormDTO;
import com.example.event_management.entity.AdminEntity;
import com.example.event_management.entity.EventEntity;
import com.example.event_management.entity.SpeakerEntity;
import com.example.event_management.repository.AdminRepository;
import com.example.event_management.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class EventConverter {

    @Autowired
    AdminRepository adminRepository ;

    @Autowired
    SpeakerRepository speakerRepository ;

    @Autowired
    SpeakerConverter speakerConverter ;

    @Autowired
    AdminConverter adminConverter ;

    public EventDTO FormToDTO(EventFormDTO b) {

        EventDTO a = new EventDTO();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername() ;
        AdminEntity admin =  adminRepository.findAdminEntityByAdminAccountName(username);

        a.setEvent_admin(adminConverter.toDTO(admin));
        a.setEvent_speaker(speakerConverter.toDTO(speakerRepository.getById(b.getSpeaker_id())));
        a.setEvent_name(b.getEvent_name());
        a.setEvent_date(b.getEvent_date());
        a.setEvent_location(b.getEvent_location());
        a.setEvent_ending_time(b.getEvent_ending_time());
        a.setEvent_starting_time(b.getEvent_starting_time());
        return a  ;
    }


    public EventEntity convertToEntity(EventDTO b) {

        EventEntity a = new EventEntity() ;
        AdminEntity c = adminRepository.getById(b.getEvent_admin().getAdmin_id()) ;
        SpeakerEntity d = speakerRepository.getById( b.getEvent_speaker().getId()) ;

        a.setEvent_admin(c);
        a.setEvent_speaker(d);
        a.setEventId(b.getEvent_id());
        a.setEventName(b.getEvent_name());
        a.setEventDate(b.getEvent_date());
        a.setEventLocation(b.getEvent_location());
        a.setEventEndingTime(b.getEvent_ending_time());
        a.setEventStartingTime(b.getEvent_starting_time());
        return a  ;
    }

    public EventDTO convertToDTO (EventEntity b) {
        EventDTO a = new EventDTO() ;
        a.setEvent_speaker(speakerConverter.toDTO(b.getEvent_speaker()));
        a.setEvent_admin(adminConverter.toDTO(b.getEvent_admin()));
        a.setEvent_id(b.getEventId());
        a.setEvent_name(b.getEventName());
        a.setEvent_date(b.getEventDate());
        a.setEvent_location(b.getEventLocation());
        a.setEvent_ending_time(b.getEventEndingTime());
        a.setEvent_starting_time(b.getEventStartingTime());
        return a ;
    }
}
