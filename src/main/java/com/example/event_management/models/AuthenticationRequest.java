package com.example.event_management.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements Serializable {

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

}


