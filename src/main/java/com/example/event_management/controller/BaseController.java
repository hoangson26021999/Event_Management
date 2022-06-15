package com.example.event_management.controller;

import com.example.event_management.dto.EventDTO;
import com.example.event_management.dto.RegisterDTO;
import com.example.event_management.dto.SpeakerDTO;
import com.example.event_management.jwt.JwtUtil;
import com.example.event_management.models.AuthenticationRequest;
import com.example.event_management.models.AuthenticationResponse;
import com.example.event_management.service.IEventService;
import com.example.event_management.service.ISpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class BaseController {

    @Autowired
    private UserDetailsService userDetailsService ;

    @Autowired
    private IEventService eventService ;

    @Autowired
    private ISpeakerService speakerService ;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping(value = {"/op_api/getEvents"} )
    @ResponseBody
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping(value = {"/op_api/event/{id}"})
    @ResponseBody
    public EventDTO getEventbyId(@PathVariable("id") long id) {
        return eventService.getEventbyId(id);
    }

    @GetMapping(value = {"/op_api/speaker/{id}"})
    @ResponseBody
    public SpeakerDTO getSpeakerbyId(@PathVariable("id") long id) {
        return speakerService.getSpeakerbyId(id);
    }

    @GetMapping("/op_api/getUserRole")
    @ResponseBody
    public String getUserRole() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserDetails details = userDetailsService.loadUserByUsername(username);

        if (details != null && details.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "admin";
        }  else if (details != null && details.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_REGISTER"))) {
            return "register";
        } else if (details != null && details.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_SPEAKER"))) {
            return "speaker";
        } else  {
            return null;
        }
    }

    @PostMapping( "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    //------------------------------------------------------------------------------------------------------------------

    @GetMapping(value = {"/" , "/home"} )
    public String index(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "home" ;
    }

    @GetMapping(value = {"/home/{event_id}"})
    public String index(Model model ,@PathVariable("event_id") long id) {
        model.addAttribute("event", eventService.getEventbyId(id)) ;
        model.addAttribute("speaker", eventService.getEventbyId(id).getEvent_speaker()) ;
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
        String username = authentication.getName();
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
