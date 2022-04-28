package com.example.event_management.converter;

import com.example.event_management.DTO.AdminDTO;
import com.example.event_management.DTO.EventDTO;
import com.example.event_management.entity.AdminEntity;
import com.example.event_management.entity.EventEntity;
import com.example.event_management.entity.SpeakerEntity;
import com.example.event_management.repository.AdminRepository;
import com.example.event_management.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class EventConverter {

    @Autowired
    AdminRepository adminRepository ;

    @Autowired
    SpeakerRepository speakerRepository ;

    public EventEntity convertToEntity(EventDTO b) {

        EventEntity a = new EventEntity() ;
        AdminEntity c = adminRepository.getById(b.getEvent_admin_id()) ;
        SpeakerEntity d = speakerRepository.getById(b.getEvent_speaker_id()) ;

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
        a.setEvent_speaker_id(b.getEvent_speaker().getSpeakerId());
        a.setEvent_admin_id(b.getEvent_admin().getAdminId());
        a.setEvent_id(b.getEventId());
        a.setEvent_name(b.getEventName());
        a.setEvent_date(b.getEventDate());
        a.setEvent_location(b.getEventLocation());
        a.setEvent_ending_time(b.getEventEndingTime());
        a.setEvent_starting_time(b.getEventStartingTime());
        return a ;
    }
}
