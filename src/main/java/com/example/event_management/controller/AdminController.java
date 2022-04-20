package com.example.event_management.controller;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

    @Autowired
    private IEventService eventService ;

    @PostMapping("/createEvent")
    @ResponseBody
    public EventDTO createEvent(@RequestBody EventDTO newEvent) {
        return eventService.createEvent(newEvent);
    }
}
