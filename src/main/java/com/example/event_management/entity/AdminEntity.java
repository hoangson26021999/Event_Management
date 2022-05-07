package com.example.event_management.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name ="admin")
public class AdminEntity {

    /*@Setter(AccessLevel.NONE)*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adminId ;

    @Column
    private String adminName ;

    @Column(nullable = false, unique = true)
    private String adminAccountName ;

    @Column(nullable = false)
    private String adminAccountPassword ;

    @OneToMany( mappedBy = "event_admin")
    private List<EventEntity> events = new ArrayList<>();
}
