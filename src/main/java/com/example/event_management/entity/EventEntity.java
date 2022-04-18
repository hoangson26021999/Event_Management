package com.example.event_management.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name ="event")
public class EventEntity {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int event_id ;

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

    /*@ManyToOne
    private SpeakerEntity event_speaker;

    @ManyToMany(mappedBy = "Events")
    private List<RegisterEntity> registers = new ArrayList<>();

    @ManyToOne
    private AdminEntity event_admin;*/


}
