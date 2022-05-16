package com.example.event_management.dto;

import lombok.Data;
import java.util.List;

@Data
public class RegisterDTO {

    private long register_id ;
    private String register_name ;
    private int register_age ;
    private String register_email ;
    private String register_account_name ;
    private String register_account_password ;
    private String confirm_password ;
    private List<EventDTO> register_events ;

}
