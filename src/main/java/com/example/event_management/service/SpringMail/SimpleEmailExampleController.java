package com.example.event_management.service.SpringMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Controller
public class SimpleEmailExampleController {

    @Autowired
    public JavaMailSender emailSender;

    @ResponseBody
    @RequestMapping("/sendQrMail")
    public String sendHtmlEmail() throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(MyConstants.FRIEND_EMAIL);
        helper.setSubject("Test send HTML email");

        // attach the file into email body
        File attachment = new File("QrCode/QrTest4.jpg") ;
        FileSystemResource file = new FileSystemResource(new File("src/main/resources/QrCode/QrTest4.jpg"));

        helper.setText("<img src=\"cid:logo.png\"></img><div>My logo</div>", true);
        helper.addInline("logo.png", file);

        this.emailSender.send(message);

        return "Email Qrcode Sent!";
    }

}