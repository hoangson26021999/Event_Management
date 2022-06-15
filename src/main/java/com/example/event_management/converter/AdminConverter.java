package com.example.event_management.converter;

import com.example.event_management.dto.AdminDTO;
import com.example.event_management.dto.EventDTO;
import com.example.event_management.dto.SpeakerDTO;
import com.example.event_management.entity.AdminEntity;


import com.example.event_management.entity.EventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;



@Component
public class AdminConverter {

   /* @Autowired
    EventConverter eventConverter;*/

    public AdminEntity toEntity (AdminDTO b) {

        AdminEntity a = new AdminEntity() ;
        a.setAdminId(b.getAdmin_id());
        a.setAdminAccountName(b.getAdmin_account_name());
        a.setAdminAccountPassword(b.getAdmin_account_password());
        a.setAdminName(b.getAdmin_name());

        List<EventEntity> list = new ArrayList<>() ;
        /*for (EventDTO i : b.getAdmin_events())
        {
            list.add(eventConverter.convertToEntity(i));
        }*/

        a.setEvents(list);
        return a ;
    }

    public AdminDTO toDTO (AdminEntity b) {

        AdminDTO a = new AdminDTO() ;
        a.setAdmin_id(b.getAdminId());
        a.setAdmin_account_name(b.getAdminAccountName());
        a.setAdmin_account_password(b.getAdminAccountPassword());
        a.setAdmin_name(b.getAdminName());
        List<EventDTO> list = new ArrayList<>() ;
        a.setAdmin_events(list);
        return a ;

    }

}
