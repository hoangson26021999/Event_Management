package com.example.event_management.controller;

import com.example.event_management.DTO.RegisterDTO;
import com.example.event_management.DTO.SpeakerDTO;
import com.example.event_management.service.IEventService;
import com.example.event_management.service.ISpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BaseController {

    @Autowired
    private UserDetailsService userDetailsService ;

    @Autowired
    private IEventService eventService ;

    @Autowired
    private ISpeakerService speakerService ;

    @GetMapping(value = {"/" , "/home"} )
    public String index(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "home" ;
    }

    @GetMapping(value = {"/home/{event_id}"})
    public String index(Model model ,@PathVariable("event_id") long id) {
        model.addAttribute("event", eventService.getEventbyId(id)) ;
        model.addAttribute("speaker", speakerService.getSpeakerbyId(eventService.getEventbyId(id).getEvent_speaker_id())) ;
        return "event_detail";
    }

    @GetMapping("/home/login")
    public String login() {
        return "login" ;
    }

    @GetMapping("/home/create_registerform")
    public String create_Register(Model model) {
        model.addAttribute("newregister", new RegisterDTO());
        return "create_register";
    }

    @GetMapping("/home/create_speakerform")
    public String create_Speaker(Model model) {
        model.addAttribute("newspeaker", new SpeakerDTO());
        return "create_speaker";
    }

    @RequestMapping("/home/user_home")
    public String defaultAfterLogin() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername() ;
        UserDetails details = userDetailsService.loadUserByUsername(username);

        if (details != null && details.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/home";
        }  else if (details != null && details.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_REGISTER"))) {
            return "redirect:/register/home";
        } else if (details != null && details.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_SPEAKER"))) {
            return "redirect:/speaker/home";
        } else  {
            return "redirect:/home";
        }
    }

}
