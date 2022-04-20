package com.example.event_management.service;

import com.example.event_management.DTO.EventDTO;

public interface IEventService {
    EventDTO createEvent(EventDTO newEvent) ;
    EventDTO editEvent(EventDTO newEvent) ;
    EventDTO deleteEvent(EventDTO newEvent) ;
}
