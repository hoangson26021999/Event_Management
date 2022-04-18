package com.example.event_management.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "speaker")
public class SpeakerEntity {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int event_id ;

    @Column
    private String speaker_name ;

    @Column
    private int speaker_age ;

    @Column
    private String speaker_email ;

    @Column
    private String speaker_career ;

    @Column
    private String speaker_account_name ;

    @Column
    private String speaker_password_name ;

  /*  @OneToMany( mappedBy = "presentation_speaker")
    private List<PresentationEntity> presentations = new ArrayList<>();

    @OneToMany( mappedBy = "event_speaker")
    private List<EventEntity> events = new ArrayList<>();*/


}
