package com.example.event_management.service;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.DTO.RegisterDTO;

public interface IRegisterService {

    RegisterDTO createRegister(RegisterDTO newRegister);

    RegisterDTO editRegister(RegisterDTO newRegister);

    void deleteRegister(int[] ids);

    void registerEvent(int register_id , int event_id);
}
