package com.example.event_management.controller;


import com.example.event_management.DTO.SpeakerDTO;
import com.example.event_management.service.IEventService;
import com.example.event_management.service.ISpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Secured("ROLE_SPEAKER")
@Controller
public class SpeakerController {

    @Autowired
    private ISpeakerService speakerService ;

    @Autowired
    private IEventService eventService ;

    @GetMapping("/speaker_home")
    public String speakerHome (Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "/speaker/speaker_home" ;
    }

    @GetMapping("/speaker/{id}")
    public String speakerEventDetail ( Model model ,@PathVariable("id") int id ) {
        model.addAttribute("event" , eventService.getEventbyId(id)) ;
        return "/speaker/speaker_event_detail" ;
    }

    @GetMapping("/speaker/your_event")
    public String speakerYourEvent (Model model ) {
        model.addAttribute("events", speakerService.getEventsbySpeakerId());
        return "/speaker/speaker_your_event" ;
    }

    @PostMapping("/speaker")
    @ResponseBody
    public String createSpeaker(@ModelAttribute SpeakerDTO newSpeaker) {
        speakerService.createSpeaker(newSpeaker);
        return "home";
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

}
