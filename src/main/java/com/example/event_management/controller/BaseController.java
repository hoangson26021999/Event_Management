package com.example.event_management.controller;

import com.example.event_management.entity.AdminEntity;
import com.example.event_management.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class BaseController {

    @Autowired
    UserDetailsService userDetailsService ;

    @Autowired
    private IEventService eventService ;

    @GetMapping(value = {"/" , "/home"} )
    public String index(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "home" ;
    }

    @GetMapping(value = {"/home/{event_id}"})
    public String index(Model model ,@PathVariable("event_id") int id) {
        model.addAttribute("event", eventService.getEventbyId(id)) ;
        return "event_detail";
    }

    @GetMapping("/login")
    public String login() {
        return "login" ;
    }

    @GetMapping("/create_user")
    public String create_User() {
        return "create_user";
    }

    @GetMapping("/create_registerform")
    public String create_Register() {
        return "create_register";
    }

    @GetMapping("/create_speakerform")
    public String create_Speaker() {
        return "create_speaker";
    }

    @RequestMapping("/user_home")
    public String defaultAfterLogin() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername() ;
        UserDetails details = userDetailsService.loadUserByUsername(username);

        if (details != null && details.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return "redirect:/admin_home";
        }  else if (details != null && details.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("REGISTER"))) {
            return "redirect:/register_home";
        } else if (details != null && details.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("SPEAKER"))) {
            return "redirect:/speaker_home";
        } else  {
            return "redirect:/home";
        }
    }

}
