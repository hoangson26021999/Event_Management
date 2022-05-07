package com.example.event_management.service.impl;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.DTO.RegisterDTO;
import com.example.event_management.DTO.SpeakerDTO;
import com.example.event_management.converter.EventConverter;
import com.example.event_management.converter.SpeakerConverter;
import com.example.event_management.entity.AdminEntity;
import com.example.event_management.entity.EventEntity;
import com.example.event_management.entity.RegisterEntity;
import com.example.event_management.entity.SpeakerEntity;
import com.example.event_management.repository.SpeakerRepository;
import com.example.event_management.service.IRegisterService;
import com.example.event_management.service.ISpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpeakerService implements ISpeakerService {

    @Autowired
    SpeakerRepository speakerRepository ;

    @Autowired
    SpeakerConverter speakerConverter ;

    @Autowired
    EventConverter eventConverter;

    @Autowired
    EntityManager entityManager ;

    @Override
    public List<SpeakerDTO> getAllSpeakers() {

        List<SpeakerDTO> list = new ArrayList<>() ;
        for (SpeakerEntity s : speakerRepository.findAll())
        {
            list.add(speakerConverter.toDTO(s));
        }
        return list ;
    }

    @Override
    public SpeakerDTO getSpeakerbyId(long id) {
        return speakerConverter.toDTO(speakerRepository.getById(id)) ;
    }

    @Override
    public List<EventDTO> getEventsbySpeakerId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername() ;
        SpeakerEntity speaker =  speakerRepository.findSpeakerEntityBySpeakerAccountName(username);

        return speakerConverter.toDTO(speaker).getSpeaker_events() ;
    }

    @Override
    public SpeakerDTO createSpeaker(SpeakerDTO newSpeaker) {

        SpeakerEntity speakerEntity = speakerConverter.toEntity(newSpeaker) ;
        speakerEntity = speakerRepository.save(speakerEntity) ;

        /*entityManager.getTransaction().begin();
        long index = service.saveUser(user);
        entityManager.getTransaction().commit();*/


        return speakerConverter.toDTO(speakerEntity) ;
    }

    @Override
    public SpeakerDTO editSpeaker(SpeakerDTO editSpeaker) {
        SpeakerEntity speakerEntity = speakerConverter.toEntity(editSpeaker) ;
        speakerEntity = speakerRepository.save(speakerEntity) ;
        return speakerConverter.toDTO(speakerEntity) ;
    }

    @Override
    public void deleteSpeaker(long[] ids) {
        for(long id : ids) {
            speakerRepository.deleteById(id);
        }
    }
}
