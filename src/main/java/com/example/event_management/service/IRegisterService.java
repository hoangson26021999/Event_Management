package com.example.event_management.service;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.DTO.RegisterDTO;

import java.util.List;

public interface IRegisterService {

    List<EventDTO> getEventsByRegisterID() ;

    RegisterDTO createRegister(RegisterDTO newRegister);

    RegisterDTO editRegister(RegisterDTO newRegister);

    void deleteRegister(int[] ids);

    void registerEvent( int event_id);
}
