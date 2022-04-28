package com.example.event_management.controller;

import com.example.event_management.DTO.EventDTO;
import com.example.event_management.service.IAdminService;
import com.example.event_management.service.IEventService;
import com.example.event_management.service.ISpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Secured("ROLE_ADMIN")
@Controller
public class AdminController {

    @Autowired
    private IEventService eventService ;

    @Autowired
    private IAdminService adminService ;

    @Autowired
    private ISpeakerService speakerService;

    // Trả về trang admin home

    @GetMapping (value = { "/admin_home"} )
    public String index(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "/admin/admin_home" ;
    }

    // Trả về trang admin your event

    @GetMapping(value = { "/admin_your_events"} )
    public String yourEvents(Model model) {
        model.addAttribute("events", adminService.getEventsbyAdminId());
        return "/admin/admin_your_event";
    }

    // Trả về trang admin event detail

    @GetMapping(value = {"/admin/{event_id}"})
    public String admin_event_detail(Model model ,@PathVariable("event_id") int id) {
        model.addAttribute("event", eventService.getEventbyId(id)) ;
        model.addAttribute("speaker", speakerService.getSpeakerbyId(eventService.getEventbyId(id).getEvent_speaker_id())) ;
        return "/admin/admin_event_detail";
    }

    // Trả về trang admin edit detail

    @GetMapping(value = {"/event/{event_id}"})
    public String getEditForm(Model model ,@PathVariable("event_id") int id) {
        model.addAttribute("editevent", eventService.getEventbyId(id)) ;
        return "/admin/admin_edit_event";
    }

    // Trả về trang create event

    @GetMapping("/create_event")
    public String createEventForm (Model model) {
        model.addAttribute("newevent", new EventDTO());
        return "/admin/create_event" ;
    }

    //---------------------------------------------------------------------------------------------

    // API tạo mới event

    @PostMapping("/event")
    public String createEvent( @ModelAttribute("newevent") EventDTO newevent) {
        eventService.createEvent(newevent);
        return "redirect:/admin_home" ;
    }

    // API chỉnh sửa thông tin event

    @PostMapping(value = {"/event/{id}" })
    public String editEvent( @ModelAttribute("editevent") EventDTO editevent, @PathVariable("id") int id) {
        editevent.setEvent_id(id);
        eventService.editEvent(editevent);
        return "redirect:/admin_your_events";
    }

    // API xóa event

    @DeleteMapping("/event")
    public void deleteEvents(@RequestBody int[] ids ) {
        eventService.deleteEvent(ids);
    }

    @DeleteMapping(value = {"/event/{id}"})
    public String deleteOneEvent(@PathVariable("id") int id, Model model ) {
        eventService.deleteOneEvent(id);
        model.addAttribute("events", adminService.getEventsbyAdminId());
        return "redirect:/admin_your_events";
    }

}
