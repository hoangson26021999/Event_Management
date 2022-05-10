package com.example.event_management.repository;

import com.example.event_management.entity.AdminEntity;
import com.example.event_management.entity.SpeakerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository< SpeakerEntity, Long> {

    SpeakerEntity findSpeakerEntityBySpeakerAccountName(String name) ;
    SpeakerEntity findSpeakerEntityBySpeakerEmail(String email) ;

}
