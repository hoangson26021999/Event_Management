package com.example.event_management.service.impl;

import com.example.event_management.dto.EventDTO;
import com.example.event_management.dto.RegisterDTO;
import com.example.event_management.converter.EventConverter;
import com.example.event_management.converter.RegisterConverter;
import com.example.event_management.entity.EventEntity;
import com.example.event_management.entity.RegisterEntity;
import com.example.event_management.repository.EventRepository;
import com.example.event_management.repository.RegisterRepository;
import com.example.event_management.service.IRegisterService;
import com.example.event_management.service.springmail.QrMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterService implements IRegisterService {

    @Autowired
    RegisterConverter registerConverter;

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventConverter eventConverter ;

    @Autowired
    private QrMail qrmail ;

    @Override
    public List<EventDTO> getEventsByRegisterID() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername() ;

        RegisterEntity register =  registerRepository.findRegisterEntityByRegisterAccountName(username);

        List<EventDTO> list  = new ArrayList<>() ;
        for(EventEntity i : register.getEvents())
        {
            list.add(eventConverter.convertToDTO(i));
        }

        return list;
    }

    @Override
    public RegisterDTO createRegister(RegisterDTO newRegister) {
        RegisterEntity newregister = registerConverter.convertToEntity(newRegister);
        newregister = registerRepository.save(newregister) ;
        return registerConverter.convertToDTO(newregister);
    }

    @Override
    public RegisterDTO editRegister(RegisterDTO newRegister) {
        RegisterEntity newregister = registerConverter.convertToEntity(newRegister);
        newregister = registerRepository.save(newregister) ;
        return registerConverter.convertToDTO(newregister);
    }

    @Override
    public void cancel_event(long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername() ;

        RegisterEntity register = registerRepository.findRegisterEntityByRegisterAccountName(username);
        EventEntity event = eventRepository.getById(id);

        event.getRegisters().remove(register);
        eventRepository.save(event) ;

    }

    @Override
    public void deleteRegister(long[] ids) {
        for(long id : ids) {
            registerRepository.deleteById(id);
        }
    }

    @Override
    public void registerEvent(long event_id)  {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername() ;

        RegisterEntity register = registerRepository.findRegisterEntityByRegisterAccountName(username);
        EventEntity event = eventRepository.getById(event_id);

        if(event.getRegisters().contains(register)) {
            System.out.println(" B???n ???? ????ng k?? tham gia s??? ki???n n??y r???i.");
        } else  {
            event.getRegisters().add(register);
            eventRepository.save(event) ;

            try {
                qrmail.sendQrMail(event, register);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
