package com.example.event_management.converter;

import com.example.event_management.DTO.SpeakerDTO;
import com.example.event_management.entity.SpeakerEntity;
import org.springframework.stereotype.Component;

@Component
public class SpeakerConverter {
    public SpeakerEntity toEntity (SpeakerDTO speakerDTO) {
                SpeakerEntity a = new SpeakerEntity() ;
                a.setSpeaker_id(speakerDTO.getId());
                a.setSpeaker_name(speakerDTO.getSpeaker_name()) ;
                a.setSpeaker_age(speakerDTO.getSpeaker_age())  ;
                a.setSpeaker_email(speakerDTO.getSpeaker_email());
                a.setSpeaker_career(speakerDTO.getSpeaker_career());
                a.setSpeakerAccountName(speakerDTO.getSpeaker_account_name());
                a.setSpeakerAccountPassword(speakerDTO.getSpeaker_account_password());
                return a ;
    }

    public SpeakerDTO toDTO (SpeakerEntity speakerEntity) {
        SpeakerDTO a = new SpeakerDTO() ;
        a.setId(speakerEntity.getSpeaker_id());
        a.setSpeaker_name(speakerEntity.getSpeaker_name()) ;
        a.setSpeaker_age(speakerEntity.getSpeaker_age()) ;
        a.setSpeaker_email(speakerEntity.getSpeaker_email());
        a.setSpeaker_career(speakerEntity.getSpeaker_career());
        a.setSpeaker_account_name(speakerEntity.getSpeakerAccountName());
        a.setSpeaker_account_password(speakerEntity.getSpeakerAccountPassword());
        return a ;

    }
}
