package com.example.event_management.service.impl;

import com.example.event_management.converter.AdminConverter;
import com.example.event_management.dto.EventDTO;
import com.example.event_management.converter.EventConverter;
import com.example.event_management.dto.EventFormDTO;
import com.example.event_management.entity.EventEntity;
import com.example.event_management.repository.AdminRepository;
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

    @Autowired
    private AdminRepository adminRepository ;

    @Autowired
    private AdminConverter adminConverter ;

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
    public EventDTO getEventbyId(long id) {
        return eventConverter.convertToDTO(eventRepository.getById((id)));
    }

    @Override
    public EventDTO createEvent(EventFormDTO newEvent) {

        EventDTO a = eventConverter.FormToDTO(newEvent) ;
        EventEntity newEventEntity = eventConverter.convertToEntity(a) ;

        newEventEntity = eventRepository.save(newEventEntity) ;
        return eventConverter.convertToDTO(newEventEntity) ;
    }

    @Override
    public EventDTO editEvent(EventFormDTO editEvent , long id ) {

        EventDTO a = eventConverter.FormToDTO(editEvent) ;
        a.setEvent_id(id);

        EventEntity editedEvent = eventConverter.convertToEntity(a);
        editedEvent.setEvent_speaker(eventRepository.getById(id).getEvent_speaker());
        editedEvent.setEvent_admin(eventRepository.getById(id).getEvent_admin());

        editedEvent = eventRepository.save(editedEvent) ;
        return eventConverter.convertToDTO(editedEvent) ;
    }

    @Override
    public void deleteEvent(long[] ids) {
       for( long id : ids) {
           eventRepository.deleteById(id);
       }
    }

    @Override
    public void deleteOneEvent(long id) {
        eventRepository.deleteById(id);
    }

}
