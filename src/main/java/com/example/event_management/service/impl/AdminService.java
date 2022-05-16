package com.example.event_management.service.impl;

import com.example.event_management.dto.EventDTO;
import com.example.event_management.Encryption.Encryption;
import com.example.event_management.converter.EventConverter;
import com.example.event_management.entity.AdminEntity;
import com.example.event_management.entity.EventEntity;
import com.example.event_management.repository.AdminRepository;
import com.example.event_management.repository.EventRepository;
import com.example.event_management.repository.RegisterRepository;
import com.example.event_management.service.IAdminService;
import com.example.event_management.service.qrcode.QrCodeGenerate;
import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService implements IAdminService {

    @Autowired
    Encryption encryption;

    @Autowired
    QrCodeGenerate qrCodeGenerate ;

    @Autowired
    AdminRepository adminRepository ;

    @Autowired
    EventConverter eventConverter;

    @Autowired
    EventRepository eventRepository ;

    @Autowired
    RegisterRepository registerRepository;

    @Override
    public List<EventDTO> getEventsbyAdminId() {

        List<EventDTO> admin_events = new ArrayList<>() ;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername() ;
        AdminEntity admin =  adminRepository.findAdminEntityByAdminAccountName(username);

        for (EventEntity event : admin.getEvents() ) {
            admin_events.add(eventConverter.convertToDTO(event)) ;
        }

        return admin_events ;
    }

    @Override
    public boolean checkin(MultipartFile file, long id) {

        try {
            File newfile = new File("src/main/resources/QrCode/check/" + file.getOriginalFilename()) ;
            FileOutputStream fileOutputStream = new FileOutputStream(newfile);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Encoding charset
        String charset = "UTF-8";

        Map<EncodeHintType, ErrorCorrectionLevel> hashMap  = new HashMap<>();

        hashMap.put(EncodeHintType.ERROR_CORRECTION,  ErrorCorrectionLevel.L);

        try {

           String a =  qrCodeGenerate.readQR("src/main/resources/QrCode/check/" + file.getOriginalFilename() ,charset ,hashMap) ;
           String event_id = encryption.decrypt(a.substring(0,24)) ;
           String register_id = encryption.decrypt(a.substring(26,50)) ;

           if(( Long.parseLong(event_id) == id ) && (eventRepository.getById(id).getRegisters().contains(registerRepository.getById(Long.parseLong(register_id)))) ) {
               return true ;
           } else {
               return false ;
           }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }
}
