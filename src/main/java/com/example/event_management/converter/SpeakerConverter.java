package com.example.event_management.converter;

import com.example.event_management.dto.EventDTO;
import com.example.event_management.dto.SpeakerDTO;
import com.example.event_management.entity.EventEntity;
import com.example.event_management.entity.SpeakerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpeakerConverter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    EventConverter eventConverter;

    public SpeakerEntity toEntity (SpeakerDTO speakerDTO) {

                SpeakerEntity a = new SpeakerEntity() ;
                if(speakerDTO.getId() !=0 ) {
                    a.setSpeakerId(speakerDTO.getId());
                }
                a.setSpeakerName(speakerDTO.getSpeaker_name()) ;
                a.setSpeakerAge(speakerDTO.getSpeaker_age())  ;
                a.setSpeakerEmail(speakerDTO.getSpeaker_email());
                a.setSpeakerCareer(speakerDTO.getSpeaker_career());
                a.setSpeakerAccountName(speakerDTO.getSpeaker_account_name());
                a.setSpeakerAccountPassword(passwordEncoder.encode(speakerDTO.getSpeaker_account_password()));
                return a ;
    }

    public SpeakerDTO toDTO (SpeakerEntity speakerEntity) {

        SpeakerDTO a = new SpeakerDTO() ;
        a.setId(speakerEntity.getSpeakerId());
        a.setSpeaker_name(speakerEntity.getSpeakerName()) ;
        a.setSpeaker_age(speakerEntity.getSpeakerAge()) ;
        a.setSpeaker_email(speakerEntity.getSpeakerEmail());
        a.setSpeaker_career(speakerEntity.getSpeakerCareer());

        List<EventDTO> list = new ArrayList<>() ;
        for (EventEntity i : speakerEntity.getEvents())
        {
            list.add(eventConverter.convertToDTO(i));
        }
        a.setSpeaker_events(list);
        a.setSpeaker_account_name(speakerEntity.getSpeakerAccountName());
        return a ;

    }
}
