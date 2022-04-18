package com.example.event_management.controller;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.service.springmail.QrMail;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.ArrayList;

@Controller
public class BaseController {

    @RequestMapping("/")
    public String index() {
        return "home" ;
    }

    @GetMapping("/login")
    public String login() { return "login" ;}

    @PostMapping("/createEvent")
    @ResponseBody
    public EventDTO createEvent(@RequestBody EventDTO newEvent) { return newEvent;}

    @GetMapping("/getAllEvent")
    public ArrayList<EventDTO> getAllEvent() {
        ArrayList<EventDTO> a = new ArrayList<>() ;
        return a ;
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
