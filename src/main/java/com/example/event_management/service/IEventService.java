package com.example.event_management.service;

import com.example.event_management.DTO.EventDTO;

import java.util.List;

public interface IEventService {
    List<EventDTO> getAllEvents() ;
    EventDTO getEventbyId(long id) ;
    EventDTO createEvent(EventDTO newEvent) ;
    EventDTO editEvent(EventDTO editEvent ) ;
    void deleteEvent(long[] ids) ;
    void deleteOneEvent(long id) ;
}
