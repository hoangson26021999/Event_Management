package com.example.event_management.repository;

import com.example.event_management.entity.AdminEntity;
import com.example.event_management.entity.PresentationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresentationRepository extends JpaRepository<PresentationEntity, Long> {
}
