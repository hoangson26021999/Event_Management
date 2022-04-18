package com.example.event_management.controller;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.DTO.RegisterDTO;
import com.example.event_management.DTO.SpeakerDTO;
import com.example.event_management.converter.EventConverter;
import com.example.event_management.service.IEventService;
import com.example.event_management.service.impl.EventService;
import com.example.event_management.service.springmail.QrMail;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.ArrayList;

@Controller
public class BaseController {

    @Autowired
    private IEventService eventService ;

    @RequestMapping("/")
    public String index() {
        return "home" ;
    }

    @GetMapping("/login")
    public String login() {
        return "login" ;
    }

    @PostMapping("/createEvent")
    @ResponseBody
    public EventDTO createEvent(@RequestBody EventDTO newEvent) {
        return eventService.createEvent(newEvent);
    }

    @PostMapping("/createSpeaker")
    @ResponseBody
    public SpeakerDTO createEvent(@RequestBody SpeakerDTO newSpeaker) {
        return newSpeaker;
    }

    @PostMapping("/createRegister")
    @ResponseBody
    public RegisterDTO createEvent(@RequestBody RegisterDTO newRegister) {
        return newRegister;
    }

    @GetMapping("/getAllEvent")
    @ResponseBody
    public ArrayList<EventDTO> getAllEvent() {
        ArrayList<EventDTO> allEvent = new ArrayList<>() ;
        return allEvent ;
    }

    @Autowired
    private QrMail qrmail ;

    @ResponseBody
    @RequestMapping("/sendQrCodeMail")
    public String sendHtmlEmail() throws MessagingException {
        qrmail.sendQrMail();
        return "home";
    }


}
