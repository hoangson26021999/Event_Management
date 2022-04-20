package com.example.event_management.service.impl;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.converter.EventConverter;
import com.example.event_management.entity.EventEntity;
import com.example.event_management.repository.EventRepository;
import com.example.event_management.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService implements IEventService {

    @Autowired
    private EventConverter eventConverter ;

    @Autowired
    private EventRepository eventRepository ;

    @Override
    public EventDTO createEvent(EventDTO newEvent) {
        EventEntity newEventEntity = eventConverter.convertToEntity(newEvent) ;
        newEventEntity = eventRepository.save(newEventEntity) ;
        return eventConverter.convertToDTO(newEventEntity) ;
    }

    @Override
    public EventDTO editEvent(EventDTO newEvent) {
        EventEntity newEventEntity = eventConverter.convertToEntity(newEvent) ;
        newEventEntity = eventRepository.save(newEventEntity) ;
        return eventConverter.convertToDTO(newEventEntity) ;
    }

    @Override
    public EventDTO deleteEvent(EventDTO newEvent) {
        EventEntity newEventEntity = eventConverter.convertToEntity(newEvent) ;
        newEventEntity = eventRepository.save(newEventEntity) ;
        return eventConverter.convertToDTO(newEventEntity) ;
    }


}
