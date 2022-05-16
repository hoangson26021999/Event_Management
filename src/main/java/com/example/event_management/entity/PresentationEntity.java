package com.example.event_management.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Data
@Entity
@Table(name ="presentation")
public class PresentationEntity {

    /*@Setter(AccessLevel.NONE)*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long presentation_id ;

    @Column
    private String presentation_name ;

    @Column
    private String presentation_content ;

    @ManyToOne
    private SpeakerEntity presentation_speaker;

}
