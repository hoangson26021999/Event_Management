package com.example.event_management.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
public class AuthenticationResponse implements Serializable {

    @Getter
    private final String jwt;

}