package com.example.event_management.service;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.DTO.RegisterDTO;
import com.example.event_management.DTO.SpeakerDTO;

public interface ISpeakerService {

    SpeakerDTO createSpeaker (SpeakerDTO newSpeaker) ;
}
