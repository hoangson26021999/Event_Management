package com.example.event_management.controller;

import com.example.event_management.DTO.RegisterDTO;
import com.example.event_management.service.IEventService;
import com.example.event_management.service.IRegisterService;
import com.example.event_management.service.ISpeakerService;
import com.example.event_management.service.springmail.QrMail;
import com.example.event_management.validator.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private IRegisterService registerService ;

    @Autowired
    private ISpeakerService speakerService ;

    @Autowired
    private IEventService eventService ;

    @Autowired
    private RegisterValidator registerValidator;

    @GetMapping("/register/home")
    public String registerHome (Model model) {
        model.addAttribute("events" , eventService.getAllEvents()) ;
        return "/register/register_home" ;
    }

    @GetMapping("/register/{id}")
    public String registerEventDetail (Model model, @PathVariable("id") int id ) {
        model.addAttribute("event" , eventService.getEventbyId(id)) ;
        model.addAttribute("speaker" , speakerService.getSpeakerbyId(eventService.getEventbyId(id).getEvent_speaker_id())) ;
        return "/register/register_event_detail" ;
    }

    @GetMapping("/register/your_events")
    public String registerEventDetail (Model model) {
        model.addAttribute("events" ,registerService.getEventsByRegisterID()) ;
        return "/register/register_your_event" ;
    }

    @GetMapping("/register_event/{event_id}")
    public String registerEvent ( @PathVariable("event_id") int event_id ) {
        registerService.registerEvent(event_id);
        return "redirect:/register/home" ;
    }

// <------------------API------------------->

    @PostMapping("/create_register")
    public String createRegister(Model model, @ModelAttribute("newregister") RegisterDTO newregister, BindingResult result) {

        registerValidator.validate(newregister , result);

        // Validate result
        if (result.hasErrors()) {
            model.addAttribute("newregister", newregister );
            return "create_register";
        } else {
            registerService.createRegister(newregister);
            return "redirect:/home";
        }
    }

    @PutMapping("/register/{id}")
    @ResponseBody
    public RegisterDTO editRegister(@RequestBody RegisterDTO editedRegister , @PathVariable("id") int id ) {
        editedRegister.setRegister_id(id);
        return registerService.editRegister(editedRegister);
    }


    @PostMapping("/register/cancel_event/{id}")
    public String cancelEvent( @PathVariable("id") long id) {
        registerService.cancel_event(id);
        return "redirect:/register/your_events";
    }

    @DeleteMapping("/register")
    @ResponseBody
    public void deleteRegister(@RequestBody long[] ids) {
        registerService.deleteRegister(ids);
    }

}
