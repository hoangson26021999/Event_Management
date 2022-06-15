package com.example.event_management.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class EventFormDTO {
    private long speaker_id ;
    private String event_name ;
    private Date event_date ;
    private Time event_starting_time ;
    private Time event_ending_time ;
    private String event_location ;
}
