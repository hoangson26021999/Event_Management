package com.example.event_management.converter;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.DTO.RegisterDTO;
import com.example.event_management.entity.EventEntity;
import com.example.event_management.entity.RegisterEntity;
import org.springframework.stereotype.Component;

@Component
public class RegisterConverter {

    public RegisterEntity convertToEntity(RegisterDTO b) {
        RegisterEntity a = new RegisterEntity() ;
        a.setRegister_id(b.getRegister_id());
        a.setRegister_name(b.getRegister_name());
        a.setRegister_age(b.getRegister_age());
        a.setRegister_email(b.getRegister_email());
        a.setRegister_account_name(b.getRegister_account_name());
        a.setRegister_account_password(b.getRegister_account_password());
        return a  ;
    }

    public RegisterDTO convertToDTO (RegisterEntity b) {
        RegisterDTO a = new RegisterDTO() ;
        a.setRegister_id(b.getRegister_id());
        a.setRegister_name(b.getRegister_name());
        a.setRegister_age(b.getRegister_age());
        a.setRegister_email(b.getRegister_email());
        a.setRegister_account_name(b.getRegister_account_name());
        a.setRegister_account_password(b.getRegister_account_password());
        return a;
    }
}
