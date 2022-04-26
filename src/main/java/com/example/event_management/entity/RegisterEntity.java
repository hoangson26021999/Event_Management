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
@Table(name ="register")
public class RegisterEntity {

    /*@Setter(AccessLevel.NONE)*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int register_id ;

    @Column
    private String register_name ;

    @Column
    private int register_age ;

    @Column
    private String register_email ;

    @Column(nullable = false, unique = true)
    private String registerAccountName ;
    @Column(nullable = false)
    private String registerAccountPassword ;

    @ManyToMany
    @JoinTable(name = "register_event",
            joinColumns = @JoinColumn(name = "register_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<EventEntity> Events = new ArrayList<>();

}
