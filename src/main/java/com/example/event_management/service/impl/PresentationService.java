package com.example.event_management.service.impl;

import com.example.event_management.converter.PresentationConverter;
import com.example.event_management.converter.SpeakerConverter;
import com.example.event_management.dto.PresentationDTO;
import com.example.event_management.entity.AdminEntity;
import com.example.event_management.entity.PresentationEntity;
import com.example.event_management.entity.SpeakerEntity;
import com.example.event_management.repository.PresentationRepository;
import com.example.event_management.repository.SpeakerRepository;
import com.example.event_management.service.IPresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class PresentationService implements IPresentationService {

    @Autowired
    PresentationRepository presentationRepository ;

    @Autowired
    PresentationConverter presentationConverter ;

    @Autowired
    SpeakerRepository speakerRepository ;

    @Autowired
    SpeakerConverter speakerConverter ;

    @Override
    public PresentationDTO getPresentationbyId(long id) {
       return  presentationConverter.toDTO(presentationRepository.getById(id));
    }

    @Override
    public PresentationDTO createPresentation(PresentationDTO newPresentation) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername() ;
        SpeakerEntity speakerEntity =  speakerRepository.findSpeakerEntityBySpeakerAccountName(username);

        PresentationEntity presentationEntity = presentationConverter.toEntity(newPresentation);

        presentationEntity.setPresentation_speaker(speakerEntity);

        presentationEntity = presentationRepository.save(presentationEntity) ;
        return presentationConverter.toDTO(presentationEntity);
    }

    @Override
    public PresentationDTO editPresentation(PresentationDTO editPresentation , long id ) {
        PresentationEntity presentationEntity = presentationConverter.toEntity(editPresentation);
        presentationEntity.setPresentation_id(id);
        presentationEntity.setPresentation_speaker(presentationRepository.getById(id).getPresentation_speaker());
        presentationEntity = presentationRepository.save(presentationEntity) ;
        return presentationConverter.toDTO(presentationEntity);
    }

    @Override
    public void deletePresentationbyId(long id) {
        presentationRepository.deleteById(id);
    }
}
