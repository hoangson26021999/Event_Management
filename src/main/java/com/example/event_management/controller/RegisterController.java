package com.example.event_management.controller;

import com.example.event_management.dto.EventDTO;
import com.example.event_management.dto.RegisterDTO;
import com.example.event_management.models.AuthenticationResponse;
import com.example.event_management.service.IEventService;
import com.example.event_management.service.IRegisterService;
import com.example.event_management.service.ISpeakerService;
import com.example.event_management.validator.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping("/register_api/getEvsbyReId")
    @ResponseBody
    public List<EventDTO> registerEventDetail () {
        return registerService.getEventsByRegisterID();
    }

    @GetMapping("/register_api/regis_event")
    @ResponseBody
    public ResponseEntity<?> registerEvent (@RequestParam long event_id ) {

        try {
            registerService.registerEvent(event_id) ;
        } catch (Exception e) {
            return ResponseEntity.ok("Invalid");
        }
        return ResponseEntity.ok("Valid");
    }

    @GetMapping("/register_api/cancel_event")
    @ResponseBody
    public ResponseEntity<?> cancelEvent( @RequestParam long event_id) {
        registerService.cancel_event(event_id);
        return ResponseEntity.ok("Cancel Valid");
    }

    @PostMapping("/op_api/create_register")
    @ResponseBody
    public ResponseEntity<?> createRegister(@RequestBody RegisterDTO newregister, BindingResult result) {

        registerValidator.validate(newregister , result);

        // Validate result
        if (result.hasErrors()) {
            return ResponseEntity.ok(newregister) ;
        } else {
            return ResponseEntity.ok(registerService.createRegister(newregister));
        }
    }

    @GetMapping("/register_api/generated")
    @ResponseBody
    public ResponseEntity<?> getQrValue( @RequestParam("id") long event_id) {
        return ResponseEntity.ok(registerService.getQrcodeValue(event_id));
    }

    //-----------------------------------------------------------------------------------------

    @GetMapping("/register/home")
    public String registerHome (Model model) {
        model.addAttribute("events" , eventService.getAllEvents()) ;
        return "/register/register_home" ;
    }

    @GetMapping("/register/{id}")
    public String registerEventDetail (Model model, @PathVariable("id") int id ) {
        model.addAttribute("event" , eventService.getEventbyId(id)) ;
        model.addAttribute("speaker" ,eventService.getEventbyId(id).getEvent_speaker()) ;
        return "/register/register_event_detail" ;
    }

    @GetMapping("/register/your_events")
    public String registerEventDetail (Model model) {
        model.addAttribute("events" ,registerService.getEventsByRegisterID()) ;
        return "/register/register_your_event" ;
    }

    @GetMapping("/register_event/{event_id}")
    public String registerEvent ( @PathVariable("event_id") int event_id ) {
        try {
            registerService.registerEvent(event_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/register/home" ;
    }

// <------------------API------------------->



    @PutMapping("/register/{id}")
    @ResponseBody
    public RegisterDTO editRegister(@RequestBody RegisterDTO editedRegister , @PathVariable("id") int id ) {
        editedRegister.setRegister_id(id);
        return registerService.editRegister(editedRegister);
    }


   /* @PostMapping("/register/cancel_event/{id}")
    public String cancelEvent( @PathVariable("id") long id) {
        registerService.cancel_event(id);
        return "redirect:/register/your_events";
    }*/

    @DeleteMapping("/register")
    @ResponseBody
    public void deleteRegister(@RequestBody long[] ids) {
        registerService.deleteRegister(ids);
    }

}
