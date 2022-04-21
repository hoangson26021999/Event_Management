package com.example.event_management.controller;

import com.example.event_management.DTO.RegisterDTO;
import com.example.event_management.service.IRegisterService;
import com.example.event_management.service.springmail.QrMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Controller
public class RegisterController {

    @Autowired
    private IRegisterService registerService ;

    @PostMapping("/Register")
    @ResponseBody
    public RegisterDTO createRegister(@RequestBody RegisterDTO newRegister) {
        return registerService.createRegister(newRegister);
    }

    @PutMapping("/Register/{id}")
    @ResponseBody
    public RegisterDTO editRegister(@RequestBody RegisterDTO editedRegister , @PathVariable("id") int id ) {
        editedRegister.setRegister_id(id);
        return registerService.editRegister(editedRegister);
    }

    @DeleteMapping("/Register")
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
