package com.example.event_management.converter;


import com.example.event_management.dto.PresentationDTO;
import com.example.event_management.entity.PresentationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PresentationConverter {

    @Autowired
    SpeakerConverter speakerConverter ;

    public PresentationDTO toDTO(PresentationEntity p) {
        PresentationDTO pdto = new PresentationDTO() ;
        pdto.setPresentation_id(p.getPresentation_id());
        pdto.setPresentation_name(p.getPresentation_name());
        pdto.setPresentation_content(p.getPresentation_content());
        pdto.setPresentation_speaker(speakerConverter.toDTO(p.getPresentation_speaker()));
        return pdto ;
    }

    public PresentationEntity toEntity(PresentationDTO p) {
        PresentationEntity pEntity = new PresentationEntity() ;
        pEntity.setPresentation_id(p.getPresentation_id());
        pEntity.setPresentation_name(p.getPresentation_name());
        pEntity.setPresentation_content(p.getPresentation_content());
        return pEntity ;
    }
}
