package com.example.event_management.service;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.DTO.RegisterDTO;

import java.util.List;

public interface IRegisterService {

    List<EventDTO> getEventsByRegisterID() ;

    RegisterDTO createRegister(RegisterDTO newRegister);

    RegisterDTO editRegister(RegisterDTO newRegister);

    void cancel_event(long id);

    void deleteRegister(long[] ids);

    void registerEvent( long event_id);
}
