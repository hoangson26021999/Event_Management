package com.example.event_management.DTO;

import lombok.Data;
import java.util.List;

@Data
public class SpeakerDTO {

    private long id ;
    private String speaker_name ;
    private int speaker_age ;
    private String speaker_email ;
    private String speaker_career ;
    private String speaker_account_name ;
    private String speaker_account_password ;
    private String confirm_password ;
    private List<EventDTO> speaker_events ;
}
