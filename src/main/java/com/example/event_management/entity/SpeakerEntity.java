package com.example.event_management.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "speaker")
public class SpeakerEntity {

    /*@Setter(AccessLevel.NONE)*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long speakerId ;

    @Column
    private String speakerName ;

    @Column
    private int speakerAge ;

    @Column
    private String speakerEmail ;

    @Column
    private String speakerCareer ;

    @Column(nullable = false, unique = true)
    private String speakerAccountName ;

    @Column(nullable = false)
    private String speakerAccountPassword ;

    @OneToMany( mappedBy = "presentation_speaker")
    private List<PresentationEntity> presentations = new ArrayList<>();

    @OneToMany( mappedBy = "event_speaker")
    private List<EventEntity> events = new ArrayList<>();


}
