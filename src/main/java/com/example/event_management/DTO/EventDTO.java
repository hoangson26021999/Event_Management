package com.example.event_management.DTO;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class EventDTO {

    private String event_name ;
    private Date event_date ;
    private Time event_starting_time ;
    private Time event_ending_time ;
    private String event_location ;

}
