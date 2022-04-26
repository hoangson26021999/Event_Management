package com.example.event_management.controller;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.service.IAdminService;
import com.example.event_management.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private IEventService eventService ;

    @Autowired
    private IAdminService adminService ;

    @GetMapping(value = { "/admin_home"} )
    public String index(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "/admin/admin_home" ;
    }

    @GetMapping(value = { "/admin_your_events"} )
    public String yourEvents(Model model) {
        model.addAttribute("events", adminService.getEventsbyAdminId());
        return "/admin/admin_your_event" ;
    }

    @GetMapping("/event")
    @ResponseBody
    public List<EventDTO> getAllEvent () {
        return eventService.getAllEvents() ;
    }

    @PostMapping("/event")
    public String createEvent( @ModelAttribute("newevent") EventDTO newevent) {
        eventService.createEvent(newevent);
        return "/admin/admin_home" ;
    }

    @PutMapping("/event/{id}")
    @ResponseBody
    public EventDTO editEvent(@RequestBody EventDTO newEvent , @PathVariable("id") int id) {
        newEvent.setEvent_id(id);
        return eventService.editEvent(newEvent);
    }

    @DeleteMapping("/event")
    public void editEvent(@RequestBody int[] ids ) {
        eventService.deleteEvent(ids);
    }

    @GetMapping("/create_event")
    public String createEventForm (Model model) {
        model.addAttribute("newevent", new EventDTO());
        return "/admin/create_event" ;
    }
}
