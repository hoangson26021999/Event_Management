package com.example.event_management.controller;


import com.example.event_management.dto.EventDTO;
import com.example.event_management.dto.PresentationDTO;
import com.example.event_management.dto.SpeakerDTO;
import com.example.event_management.service.IEventService;
import com.example.event_management.service.IPresentationService;
import com.example.event_management.service.ISpeakerService;
import com.example.event_management.validator.PresentationValidator;
import com.example.event_management.validator.SpeakerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class SpeakerController {

    @Autowired
    private ISpeakerService speakerService ;

    @Autowired
    private IEventService eventService ;

    @Autowired
    private SpeakerValidator speakerValidator ;

    @Autowired
    private IPresentationService presentationService ;

    @Autowired
    private PresentationValidator presentationValidator;

    @GetMapping("/speaker_api/getEvsbySpId")
    @ResponseBody
    public List<EventDTO> speakerYourEvent () {
        return speakerService.getEventsbySpeakerId();
    }

    @GetMapping("/speaker_api/getPresbySpId")
    @ResponseBody
    public List<PresentationDTO> speakerYourPresentaion () {
        return speakerService.getPresentaionsbyId() ;
    }

    @GetMapping("/speaker_api/pres/{id}")
    @ResponseBody
    public PresentationDTO presentationDetail (@PathVariable("id") long id) {
        return presentationService.getPresentationbyId(id) ;
    }

    //------------------------------------------------------------------------------

    @GetMapping("/speaker/home")
    public String speakerHome (Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "/speaker/speaker_home" ;
    }

    @GetMapping("/speaker/{id}")
    public String speakerEventDetail ( Model model ,@PathVariable("id") int id ) {
        model.addAttribute("event" , eventService.getEventbyId(id)) ;
        model.addAttribute("speaker" ,eventService.getEventbyId(id).getEvent_speaker()) ;
        return "/speaker/speaker_event_detail" ;
    }

    @GetMapping("/speaker/your_events")
    public String speakerYourEvent (Model model ) {
        model.addAttribute("events", speakerService.getEventsbySpeakerId());
        return "/speaker/speaker_your_event" ;
    }

    @GetMapping("/speaker/your_presentations")
    public String speakerYourPresentaion (Model model) {
        model.addAttribute("presentations", speakerService.getPresentaionsbyId());
        return "/speaker/speaker_presentation" ;
    }

    @GetMapping("/speaker/create_presentation_form")
    public String speakerYourPresentaionForm (Model model) {
        model.addAttribute("newpresentation", new PresentationDTO());
        return "/speaker/speaker_create_presentation" ;
    }

    @GetMapping("/speaker/presentation_detail/{id}")
    public String presentationDetail (Model model , @PathVariable("id") long id) {
        model.addAttribute("presentation" , presentationService.getPresentationbyId(id)) ;
        return "/presentation/presentation_detail" ;
    }

    @GetMapping("/speaker/presentation/{id}")
    public String editPresentation(@PathVariable("id") long id , Model model) {
        model.addAttribute("presentation",presentationService.getPresentationbyId(id)) ;
        return "/speaker/speaker_edit_presentation";
    }

// <---------------API--------------------------->

    @PostMapping("/speaker/create_presentation")
    public String createPresentation(@ModelAttribute("presentation") PresentationDTO newPresentation , BindingResult result , Model model ) {

        presentationValidator.validate(newPresentation ,result);

        // Validate result
        if (result.hasErrors()) {
            model.addAttribute("presentation", newPresentation );
            return "/speaker/speaker_create_presentation";
        } else {
            presentationService.createPresentation(newPresentation) ;
            return "redirect:/speaker/your_presentations";
        }

    }

    @PostMapping("/speaker/edit_presentation/{id}")
    public String editPresentation(@ModelAttribute("presentation") PresentationDTO editPresentation , Model model , BindingResult result , @PathVariable("id") long id) {

        presentationValidator.validate(editPresentation ,result);

        // Validate result
        if (result.hasErrors()) {
            model.addAttribute("presentation", editPresentation );
            return "/speaker/speaker_create_presentation";
        } else {
            presentationService.editPresentation(editPresentation ,id);
            return "redirect:/speaker/your_presentations";
        }
    }

    @PostMapping("/speaker/delete_presentation/{id}")
    public String deletePresentation(@PathVariable("id") long id) {

        presentationService.deletePresentationbyId(id);
        return "redirect:/speaker/your_presentations" ;
    }


    // Tạo mới speaker
    @PostMapping("/create_speaker")
    public String createSpeaker(@ModelAttribute("newspeaker") SpeakerDTO newSpeaker , BindingResult result , Model model ) {

        speakerValidator.validate(newSpeaker ,result);

        // Validate result
        if (result.hasErrors()) {
            model.addAttribute("newspeaker", newSpeaker );
            return "create_speaker";
        } else {
            speakerService.createSpeaker(newSpeaker);
            return "redirect:/home";
        }
    }

    @PutMapping("/speaker/{id}")
    @ResponseBody
    public SpeakerDTO editSpeaker(@RequestBody SpeakerDTO newSpeaker , @PathVariable("id") int id ) {
        newSpeaker.setId(id);
        return speakerService.editSpeaker(newSpeaker);
    }

    @DeleteMapping("/speaker")
    @ResponseBody
    public void deleteSpeaker(@RequestBody long[] ids) {
        speakerService.deleteSpeaker(ids);
    }


}
