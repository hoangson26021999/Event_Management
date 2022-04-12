package com.example.event_management.service.SpringMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class QrMail {

    @Autowired
    public JavaMailSender emailSender;

    public void sendQrMail() throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(MyConstants.FRIEND_EMAIL);
        helper.setSubject("Test send HTML email");

        // attach the file into email body
        FileSystemResource file = new FileSystemResource(new File("src/main/resources/QrCode/QrTest4.jpg"));

        helper.setText("<img src=\"cid:logo.png\"></img><div>My logo</div>", true);
        helper.addInline("logo.png", file);

        this.emailSender.send(message);
    }


}
