package com.example.event_management.service;

import com.example.event_management.dto.EventDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAdminService {

    List<EventDTO> getEventsbyAdminId() ;

    boolean checkin(MultipartFile file, long id) ;
    boolean checkinbyCode(String code, long id) ;

}
