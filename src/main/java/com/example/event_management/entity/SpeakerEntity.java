package com.example.event_management.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "speaker")
public class SpeakerEntity {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int speaker_id ;

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
    private String speaker_account_password ;

  /*  @OneToMany( mappedBy = "presentation_speaker")
    private List<PresentationEntity> presentations = new ArrayList<>();

    @OneToMany( mappedBy = "event_speaker")
    private List<EventEntity> events = new ArrayList<>();*/


}
