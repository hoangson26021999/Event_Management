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
        a.setRegisterId(b.getRegister_id());
        a.setRegisterName(b.getRegister_name());
        a.setRegisterAge(b.getRegister_age());
        a.setRegisterEmail(b.getRegister_email());
        a.setRegisterAccountName(b.getRegister_account_name());
        a.setRegisterAccountPassword(b.getRegister_account_password());
        return a  ;
    }

    public RegisterDTO convertToDTO (RegisterEntity b) {
        RegisterDTO a = new RegisterDTO() ;
        a.setRegister_id(b.getRegisterId());
        a.setRegister_name(b.getRegisterName());
        a.setRegister_age(b.getRegisterAge());
        a.setRegister_email(b.getRegisterEmail());
        a.setRegister_account_name(b.getRegisterAccountName());
        a.setRegister_account_password(b.getRegisterAccountPassword());
        return a;
    }
}
