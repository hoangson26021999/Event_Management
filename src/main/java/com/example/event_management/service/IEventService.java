package com.example.event_management.service;

import com.example.event_management.dto.EventDTO;
import com.example.event_management.dto.EventFormDTO;

import java.util.List;

public interface IEventService {
    List<EventDTO> getAllEvents() ;
    EventDTO getEventbyId(long id) ;
    EventDTO createEvent(EventFormDTO newEvent) ;
    EventDTO editEvent(EventFormDTO editEvent , long id) ;
    void deleteEvent(long[] ids) ;
    void deleteOneEvent(long id) ;
}
