package com.example.event_management.service.impl;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.DTO.RegisterDTO;
import com.example.event_management.converter.RegisterConverter;
import com.example.event_management.entity.EventEntity;
import com.example.event_management.entity.RegisterEntity;
import com.example.event_management.repository.EventRepository;
import com.example.event_management.repository.RegisterRepository;
import com.example.event_management.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService implements IRegisterService {

    @Autowired
    RegisterConverter registerConverter;

    @Autowired
    RegisterRepository registerRepository;

    @Override
    public RegisterDTO createRegister(RegisterDTO newRegister) {

        RegisterEntity newregister = registerConverter.convertToEntity(newRegister);
        newregister = registerRepository.save(newregister) ;
        return registerConverter.convertToDTO(newregister);
    }
}
