package com.example.event_management.DTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SpeakerDTO {

    private int id ;
    private String speaker_name ;
    private int speaker_age ;
    private List<EventDTO> speaker_events ;
    private String speaker_email ;
    private String speaker_career ;
    private String speaker_account_name ;
    private String speaker_account_password ;
}
