package com.example.event_management.service.impl;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.converter.EventConverter;
import com.example.event_management.entity.EventEntity;
import com.example.event_management.repository.EventRepository;
import com.example.event_management.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService implements IEventService {

    @Autowired
    private EventConverter eventConverter ;

    @Autowired
    private EventRepository eventRepository ;

    @Override
    public List<EventDTO> getAllEvents() {
        List<EventEntity> allEventsEntity = eventRepository.findAll() ;
        List<EventDTO> allEventsDTO = new ArrayList<>() ;
        for (EventEntity i : allEventsEntity) {
               allEventsDTO.add(eventConverter.convertToDTO(i)) ;
        }
        return allEventsDTO ;
    }

    @Override
    public EventDTO createEvent(EventDTO newEvent) {
        EventEntity newEventEntity = eventConverter.convertToEntity(newEvent) ;
        newEventEntity = eventRepository.save(newEventEntity) ;
        return eventConverter.convertToDTO(newEventEntity) ;
    }

    @Override
    public EventDTO editEvent(EventDTO editEvent ) {
        EventEntity editedEvent = eventConverter.convertToEntity(editEvent);
        editedEvent = eventRepository.save(editedEvent) ;
        return eventConverter.convertToDTO(editedEvent) ;
    }

    @Override
    public void deleteEvent(int[] ids) {
       for( int id : ids) {
           eventRepository.deleteById(id);
       }
    }


}
