package com.example.event_management.repository;

import com.example.event_management.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewRepository extends JpaRepository <EventEntity, Long> {
}
