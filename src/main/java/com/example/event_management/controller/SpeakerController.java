package com.example.event_management.controller;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.DTO.SpeakerDTO;
import com.example.event_management.service.ISpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;


@Controller
public class SpeakerController {

    @Autowired
    private ISpeakerService speakerService ;

    @PostMapping("/speaker")
    @ResponseBody
    public SpeakerDTO createSpeaker(@ModelAttribute SpeakerDTO newSpeaker) {
        return speakerService.createSpeaker(newSpeaker);
    }

    @PutMapping("/speaker/{id}")
    @ResponseBody
    public SpeakerDTO editSpeaker(@RequestBody SpeakerDTO newSpeaker , @PathVariable("id") int id ) {
        newSpeaker.setId(id);
        return speakerService.editSpeaker(newSpeaker);
    }

    @DeleteMapping("/speaker")
    @ResponseBody
    public void deleteSpeaker(@RequestBody int[] ids) {
        speakerService.deleteSpeaker(ids);
    }

    @GetMapping("/create_speaker")
    public String createEventForm (Model model) {
        model.addAttribute("newspeaker", new SpeakerDTO());
        return "create_speaker" ;
    }

    @GetMapping("/speaker_home")
    public String speakerHome () {
        return "/speaker/speaker_home" ;
    }

}
