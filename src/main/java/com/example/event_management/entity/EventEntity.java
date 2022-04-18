package com.example.event_management.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;


@Getter
@Setter
@Entity
@Table(name ="event")
public class EventEntity {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long event_id ;

    @Column
    private String event_name ;

    @Column
    private Date event_date ;

    @Column
    private Time event_starting_time ;

    @Column
    private Time event_ending_time ;

    @Column
    private String event_location ;


}
