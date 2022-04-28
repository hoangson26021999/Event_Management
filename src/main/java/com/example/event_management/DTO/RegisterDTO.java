package com.example.event_management.DTO;

import lombok.Data;

import java.util.List;

@Data
public class RegisterDTO {

    private int register_id ;
    private String register_name ;
    private int register_age ;
    private String register_email ;
    private List<EventDTO> register_events ;
    private String register_account_name ;
    private String register_account_password ;
    private String confirm_password ;
}
