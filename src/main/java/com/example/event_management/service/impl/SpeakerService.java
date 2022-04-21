package com.example.event_management.service.impl;

import com.example.event_management.DTO.RegisterDTO;
import com.example.event_management.DTO.SpeakerDTO;
import com.example.event_management.converter.SpeakerConverter;
import com.example.event_management.entity.RegisterEntity;
import com.example.event_management.entity.SpeakerEntity;
import com.example.event_management.repository.SpeakerRepository;
import com.example.event_management.service.IRegisterService;
import com.example.event_management.service.ISpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeakerService implements ISpeakerService {

    @Autowired
    SpeakerRepository speakerRepository ;

    @Autowired
    SpeakerConverter speakerConverter ;

    @Override
    public SpeakerDTO createSpeaker(SpeakerDTO newSpeaker) {
        SpeakerEntity speakerEntity = speakerConverter.toEntity(newSpeaker) ;
        speakerEntity = speakerRepository.save(speakerEntity) ;
        return speakerConverter.toDTO(speakerEntity) ;
    }

    @Override
    public SpeakerDTO editSpeaker(SpeakerDTO editSpeaker) {
        SpeakerEntity speakerEntity = speakerConverter.toEntity(editSpeaker) ;
        speakerEntity = speakerRepository.save(speakerEntity) ;
        return speakerConverter.toDTO(speakerEntity) ;
    }

    @Override
    public void deleteSpeaker(int[] ids) {
        for(int id : ids) {
            speakerRepository.deleteById(id);
        }
    }
}
