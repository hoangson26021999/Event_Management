package com.example.event_management.service;

import com.example.event_management.DTO.EventDTO;

import java.util.List;

public interface IEventService {
    List<EventDTO> getAllEvents() ;
    EventDTO getEventbyId(int id) ;
    EventDTO createEvent(EventDTO newEvent) ;
    EventDTO editEvent(EventDTO editEvent ) ;
    void deleteEvent(int[] ids) ;
    void deleteOneEvent(int id) ;
}
