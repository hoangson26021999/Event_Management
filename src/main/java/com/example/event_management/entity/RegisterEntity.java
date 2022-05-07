package com.example.event_management.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name ="register")
public class RegisterEntity {

    /*@Setter(AccessLevel.NONE)*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long registerId ;

    @Column
    private String registerName ;

    @Column
    private int registerAge ;

    @Column
    private String registerEmail ;

    @Column(nullable = false, unique = true)
    private String registerAccountName ;
    @Column(nullable = false)
    private String registerAccountPassword ;

    @ManyToMany( mappedBy = "registers" )
    private List<EventEntity> Events = new ArrayList<>();

}
