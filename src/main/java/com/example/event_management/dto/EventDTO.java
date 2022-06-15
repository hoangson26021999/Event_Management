package com.example.event_management.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class EventDTO {

    private long event_id ;
    private AdminDTO event_admin;
    private SpeakerDTO event_speaker ;
    private String event_name ;
    private Date event_date ;
    private Time event_starting_time ;
    private Time event_ending_time ;
    private String event_location ;
}
