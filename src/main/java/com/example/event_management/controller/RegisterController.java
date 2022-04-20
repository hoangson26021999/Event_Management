package com.example.event_management.controller;

import com.example.event_management.DTO.RegisterDTO;
import com.example.event_management.service.IRegisterService;
import com.example.event_management.service.springmail.QrMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;

@Controller
public class RegisterController {

    @Autowired
    private IRegisterService registerService ;

    @PostMapping("/createRegister")
    @ResponseBody
    public RegisterDTO createRegister(@RequestBody RegisterDTO newRegister) {
        return registerService.createRegister(newRegister);
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
