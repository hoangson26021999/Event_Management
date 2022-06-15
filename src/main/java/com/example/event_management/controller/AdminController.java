package com.example.event_management.controller;

import com.example.event_management.dto.EventDTO;
import com.example.event_management.dto.EventFormDTO;
import com.example.event_management.dto.SpeakerDTO;
import com.example.event_management.service.IAdminService;
import com.example.event_management.service.IEventService;
import com.example.event_management.service.ISpeakerService;
import com.example.event_management.validator.EventValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;


@EnableWebMvc
@Controller
public class AdminController {

    @Autowired
    private IEventService eventService ;

    @Autowired
    private IAdminService adminService ;

    @Autowired
    private ISpeakerService speakerService;

    @Autowired
    private EventValidator eventValidator;

    @GetMapping("/admin_api/getEsbyAdId")
    @ResponseBody
    public List<EventDTO> yourEvents() {
        return adminService.getEventsbyAdminId();
    }

    @DeleteMapping("/admin_api/delete_event")
    @ResponseBody
    public ResponseEntity<?> deleteEvent(@RequestParam(name = "event_id") long id ) {
        eventService.deleteOneEvent(id);
        return ResponseEntity.ok("Cancel Valid");
    }

    @GetMapping("/admin_api/create_event")
    @ResponseBody
    public EventDTO createEventForm () {
        return new EventDTO() ;
    }

    @GetMapping("/admin_api/getAllSps")
    @ResponseBody
    public List<SpeakerDTO> getAllSps () {
        return speakerService.getAllSpeakers();
    }

    @PostMapping("/admin_api/create_event")
    @ResponseBody
    public ResponseEntity<?> createEvt(@RequestBody EventFormDTO newevent , BindingResult result ) {
        eventValidator.validate(newevent ,result);
        // Validate result
        if (result.hasErrors()) {
            return ResponseEntity.ok(newevent);
        } else {
            return ResponseEntity.ok(eventService.createEvent(newevent));
        }
    }

    @PostMapping(value = {"/admin_api/edit_event/{id}" })
    @ResponseBody
    public ResponseEntity<?> editEv( @RequestBody EventFormDTO editevent, @PathVariable("id") long id) {
        return ResponseEntity.ok(eventService.editEvent(editevent, id));
    }

    @PostMapping("/admin_api/check_in")
    @ResponseBody
    public ResponseEntity<?> checkinEvent( @RequestParam("code") String code , @RequestParam("id") long id ) {
        if(adminService.checkinbyCode(code,id)) {
            return  ResponseEntity.ok("Success");
        } else  {
            return  ResponseEntity.ok("Fail");
        }
    }

    //-----------------------------------------------------------------------------------------------------

    // Trả về trang admin home

    @GetMapping (value = { "/admin/home"} )
    public String index(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "/admin/admin_home" ;
    }

    // Trả về trang admin your event

    @GetMapping(value = { "/admin/your_events"} )
    public String yourEvents(Model model) {
        model.addAttribute("events", adminService.getEventsbyAdminId());
        return "/admin/admin_your_event";
    }

    // Trả về trang admin event detail

    @GetMapping(value = {"/admin/{event_id}"})
    public String admin_event_detail(Model model ,@PathVariable("event_id") int id) {
        model.addAttribute("event", eventService.getEventbyId(id)) ;
        model.addAttribute("speaker", eventService.getEventbyId(id).getEvent_speaker()) ;
        return "/admin/admin_event_detail";
    }

    // Trả về trang admin edit detail

    @GetMapping(value = {"/admin/event/{event_id}"})
    public String getEditForm(Model model ,@PathVariable("event_id") int id) {
        model.addAttribute("editevent", eventService.getEventbyId(id)) ;
        return "/admin/admin_edit_event";
    }

    // Trả về trang create event

    @GetMapping("/admin/create_event")
    public String createEventForm (Model model) {
        model.addAttribute("newevent", new EventDTO());
        model.addAttribute("speakers", speakerService.getAllSpeakers());
        return "/admin/create_event" ;
    }

    //---------------------------------------------------------------------------------------------

    // API tạo mới event

//    @PostMapping("/admin/event")
//    public String createEvent(@ModelAttribute("newevent") EventDTO newevent , BindingResult result , Model model) {
//
//        eventValidator.validate(newevent ,result);
//
//        // Validate result
//        if (result.hasErrors()) {
//            model.addAttribute("newevent", newevent );
//            model.addAttribute("speakers", speakerService.getAllSpeakers());
//            return "/admin/create_event" ;
//        } else {
//            eventService.createEvent(newevent);
//            return "redirect:/admin/home" ;
//        }
//    }

    // Check in
//    @PostMapping("/admin/check_in/{id}")
//    public String checkinEvent( Model model, @RequestParam(name = "file") MultipartFile file , @PathVariable("id") long id ) {
//        if(adminService.checkin(file,id)) {
//            model.addAttribute("register", "success");
//        } else  {
//            model.addAttribute("register", "fail");
//        }
//
//        return "/admin/admin_checkin";
//    }

    // API chỉnh sửa thông tin event

    /*@PostMapping(value = {"/admin/event/{id}" })
    public String editEvent( @ModelAttribute("editevent") EventDTO editevent, @PathVariable("id") long id) {
        eventService.editEvent(editevent, id);
        return "redirect:/admin/your_events";
    }*/

    // API xóa event

    @DeleteMapping("/admin/event")
    public void deleteEvents(@RequestBody long[] ids ) {
        eventService.deleteEvent(ids);
    }

    @PostMapping(value = {"/admin/delete_event/{id}"})
    public String deleteOneEvent(@PathVariable("id") int id, Model model ) {
        eventService.deleteOneEvent(id);
        model.addAttribute("events", adminService.getEventsbyAdminId());
        return "redirect:/admin/your_events";
    }

}
