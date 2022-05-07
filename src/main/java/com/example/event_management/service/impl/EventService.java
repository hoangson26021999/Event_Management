package com.example.event_management.service.impl;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.converter.EventConverter;
import com.example.event_management.entity.AdminEntity;
import com.example.event_management.entity.EventEntity;
import com.example.event_management.repository.AdminRepository;
import com.example.event_management.repository.EventRepository;
import com.example.event_management.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public EventDTO createEvent(EventDTO newEvent) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername() ;
        AdminEntity admin =  adminRepository.findAdminEntityByAdminAccountName(username);
        newEvent.setEvent_admin_id(admin.getAdminId());

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
