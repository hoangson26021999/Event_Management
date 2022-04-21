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

    @Column
    private String register_account_name ;

    @Column
    private String register_account_password ;

   /* @ManyToMany
    @JoinTable(name = "register_event",
            joinColumns = @JoinColumn(name = "register_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<EventEntity> Events = new ArrayList<>();*/

}
