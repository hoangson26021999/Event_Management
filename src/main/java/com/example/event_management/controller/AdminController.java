package com.example.event_management.controller;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private IEventService eventService ;

    @GetMapping("/Event")
    @ResponseBody
    public List<EventDTO> getAllEvent () {
        return eventService.getAllEvents() ;
    }

    @PostMapping("/Event")
    @ResponseBody
    public EventDTO createEvent(@RequestBody EventDTO newEvent) {
        return eventService.createEvent(newEvent);
    }

    @PutMapping("/Event/{id}")
    @ResponseBody
    public EventDTO editEvent(@RequestBody EventDTO newEvent , @PathVariable("id") int id) {
        newEvent.setEvent_id(id);
        return eventService.editEvent(newEvent);
    }

    @DeleteMapping("/Event")
    @ResponseBody
    public void editEvent(@RequestBody int[] ids ) {
        eventService.deleteEvent(ids);
    }
}
