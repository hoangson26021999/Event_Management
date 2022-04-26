package com.example.event_management.repository;

import com.example.event_management.entity.AdminEntity;
import com.example.event_management.entity.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository <RegisterEntity, Integer> {

    RegisterEntity findRegisterEntityByRegisterAccountName(String name) ;
}
