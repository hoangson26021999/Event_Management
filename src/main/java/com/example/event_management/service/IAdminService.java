package com.example.event_management.service;

import com.example.event_management.DTO.EventDTO;

import java.util.List;

public interface IAdminService {
    List<EventDTO> getEventsbyAdminId() ;

}
