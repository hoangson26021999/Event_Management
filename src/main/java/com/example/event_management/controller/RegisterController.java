package com.example.event_management.controller;

import com.example.event_management.DTO.RegisterDTO;
import com.example.event_management.service.IRegisterService;
import com.example.event_management.service.springmail.QrMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@Controller
public class RegisterController {

    @Autowired
    private IRegisterService registerService ;

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
    @GetMapping("/create_register")
    public String createRegisterForm (Model model) {
        model.addAttribute("newregister", new RegisterDTO());
        return "create_register" ;
    }

    @GetMapping("/register_event")
    public String registerEvent ( @RequestParam ( name = "register_id" ) int register_id , @RequestParam ( name = "event_id" ) int event_id ) {
        registerService.registerEvent(register_id ,event_id);
        return "/home" ;
    }

    @GetMapping("/register_home")
    public String registerHome () {
        return "/register/register_home" ;
    }
}
