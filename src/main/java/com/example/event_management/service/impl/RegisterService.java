package com.example.event_management.service.impl;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.DTO.RegisterDTO;
import com.example.event_management.converter.EventConverter;
import com.example.event_management.converter.RegisterConverter;
import com.example.event_management.entity.AdminEntity;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
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
    public void deleteRegister(long[] ids) {
        for(long id : ids) {
            registerRepository.deleteById(id);
        }
    }

    @Override
    public void registerEvent(long event_id)  {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername() ;
        RegisterEntity register =  registerRepository.findRegisterEntityByRegisterAccountName(username);

        EventEntity event = eventRepository.getById(event_id);

        for (int i = 0 ; i < event.getRegisters().size() ; i++) {
            if(register.getRegisterId() != event.getRegisters().get(i).getRegisterId() && i ==  event.getRegisters().size()-1 ) {

                event.getRegisters().add(register);
                eventRepository.save(event);

                try {
                    qrmail.sendQrMail(event, register);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            } else {

            }
        }
    }
}
