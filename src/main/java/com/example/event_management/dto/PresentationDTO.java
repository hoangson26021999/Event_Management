package com.example.event_management.dto;

import lombok.Data;


@Data
public class PresentationDTO {

    private long presentation_id ;
    private String presentation_name ;
    private String presentation_content ;
    private SpeakerDTO presentation_speaker;

}
