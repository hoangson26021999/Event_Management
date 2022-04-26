package com.example.event_management.DTO;

import lombok.Data;

import java.util.List;

@Data
public class AdminDTO {
    private String admin_name ;
    private String admin_account_name ;
    private String admin_account_password ;
    private List<EventDTO> admin_events ;
}
