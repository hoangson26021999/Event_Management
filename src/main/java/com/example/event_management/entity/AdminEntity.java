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

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int admin_id ;

    @Column
    private String admin_name ;

    @Column
    private String admin_account_name ;

    @Column
    private String admin_account_password ;

    /*@OneToMany( mappedBy = "event_admin")
    private List<EventEntity> events = new ArrayList<>();*/
}
