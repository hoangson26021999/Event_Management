package com.example.event_management.service.impl;

import com.example.event_management.repository.NewRepository;
import com.example.event_management.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewService implements INewService {

    @Autowired
    private NewRepository newRepository ;
}
