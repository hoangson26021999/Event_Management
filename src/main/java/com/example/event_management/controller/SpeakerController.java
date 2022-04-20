package com.example.event_management.controller;

import com.example.event_management.DTO.SpeakerDTO;
import com.example.event_management.service.ISpeakerService;
import com.example.event_management.service.springmail.QrMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;

@Controller
public class SpeakerController {

    @Autowired
    private ISpeakerService speakerService ;

    @PostMapping("/createSpeaker")
    @ResponseBody
    public SpeakerDTO createSpeaker(@RequestBody SpeakerDTO newSpeaker) {
        return speakerService.createSpeaker(newSpeaker);
    }

}
