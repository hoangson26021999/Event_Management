package com.example.event_management.controller;

import com.example.event_management.service.SpringMail.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Controller
public class BaseController {

    @Autowired
    public JavaMailSender emailSender;

    @RequestMapping("/")
    public String index() {
        return "index" ;
    }

    @GetMapping("/login")
    public String login() { return "login" ;}


}
