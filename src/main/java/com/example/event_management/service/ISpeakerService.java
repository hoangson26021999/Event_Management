package com.example.event_management.service;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.DTO.RegisterDTO;
import com.example.event_management.DTO.SpeakerDTO;

import java.util.List;

public interface ISpeakerService {
    List<SpeakerDTO> getAllSpeakers() ;
    SpeakerDTO getSpeakerbyId(long id) ;
    List<EventDTO> getEventsbySpeakerId() ;
    SpeakerDTO createSpeaker (SpeakerDTO newSpeaker) ;
    SpeakerDTO editSpeaker (SpeakerDTO editSpeaker) ;
    void deleteSpeaker (long[] ids) ;
}
