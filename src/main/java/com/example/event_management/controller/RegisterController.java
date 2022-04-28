package com.example.event_management.controller;

import com.example.event_management.DTO.RegisterDTO;
import com.example.event_management.service.IEventService;
import com.example.event_management.service.IRegisterService;
import com.example.event_management.service.ISpeakerService;
import com.example.event_management.service.springmail.QrMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Secured("ROLE_REGISTER")
@Controller
public class RegisterController {

    @Autowired
    private IRegisterService registerService ;

    @Autowired
    private ISpeakerService speakerService ;

    @Autowired
    private IEventService eventService ;

    @GetMapping("/register_home")
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

    @GetMapping("/register/your_event")
    public String registerEventDetail (Model model) {
        model.addAttribute("events" ,registerService.getEventsByRegisterID()) ;
        return "/register/register_your_event" ;
    }

    @GetMapping("/register_event/{event_id}")
    public String registerEvent ( @PathVariable("event_id") int event_id ) {
        registerService.registerEvent(event_id);
        return "redirect:/register_home" ;
    }

    @PostMapping("/register")
    @ResponseBody
    public RegisterDTO createRegister( @ModelAttribute("newregister") RegisterDTO newregister) {
        return registerService.createRegister(newregister);
    }

    @PutMapping("/register/{id}")
    @ResponseBody
    public RegisterDTO editRegister(@RequestBody RegisterDTO editedRegister , @PathVariable("id") int id ) {
        editedRegister.setRegister_id(id);
        return registerService.editRegister(editedRegister);
    }

    @DeleteMapping("/register")
    @ResponseBody
    public void deleteRegister(@RequestBody int[] ids) {
        registerService.deleteRegister(ids);
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
