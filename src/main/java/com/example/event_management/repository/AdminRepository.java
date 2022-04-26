package com.example.event_management.repository;

import com.example.event_management.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository <AdminEntity , Integer> {

    AdminEntity findAdminEntityByAdminAccountName(String name);

}
