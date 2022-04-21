package com.example.event_management.controller;

import com.example.event_management.DTO.SpeakerDTO;
import com.example.event_management.service.ISpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class SpeakerController {

    @Autowired
    private ISpeakerService speakerService ;

    @PostMapping("/Speaker")
    @ResponseBody
    public SpeakerDTO createSpeaker(@RequestBody SpeakerDTO newSpeaker) {
        return speakerService.createSpeaker(newSpeaker);
    }

    @PutMapping("/Speaker/{id}")
    @ResponseBody
    public SpeakerDTO editSpeaker(@RequestBody SpeakerDTO newSpeaker , @PathVariable("id") int id ) {
        newSpeaker.setId(id);
        return speakerService.editSpeaker(newSpeaker);
    }

    @DeleteMapping("/Speaker")
    @ResponseBody
    public void deleteSpeaker(@RequestBody int[] ids) {
        speakerService.deleteSpeaker(ids);
    }

}
