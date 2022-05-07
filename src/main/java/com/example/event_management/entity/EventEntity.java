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

    /*@Setter(AccessLevel.NONE)*/
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long eventId ;

    @Column
    private String eventName ;

    @Column
    private Date eventDate ;

    @Column
    private Time eventStartingTime ;

    @Column
    private Time eventEndingTime ;

    @Column
    private String eventLocation ;

    @ManyToMany()
    @JoinTable(name = "event_register",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "register_id")
    )
    private List<RegisterEntity> registers = new ArrayList<>();

    @ManyToOne
    private SpeakerEntity event_speaker;

    @ManyToOne
    private AdminEntity event_admin;


}
