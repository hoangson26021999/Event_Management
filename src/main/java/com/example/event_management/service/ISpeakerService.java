package com.example.event_management.service;

import com.example.event_management.dto.EventDTO;
import com.example.event_management.dto.PresentationDTO;
import com.example.event_management.dto.SpeakerDTO;

import java.util.List;

public interface ISpeakerService {
    List<SpeakerDTO> getAllSpeakers() ;
    List<PresentationDTO> getPresentaionsbyId() ;
    SpeakerDTO getSpeakerbyId(long id) ;
    List<EventDTO> getEventsbySpeakerId() ;
    SpeakerDTO createSpeaker (SpeakerDTO newSpeaker) ;
    SpeakerDTO editSpeaker (SpeakerDTO editSpeaker) ;
    void deleteSpeaker (long[] ids) ;
}
