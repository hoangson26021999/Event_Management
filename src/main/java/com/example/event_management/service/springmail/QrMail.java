package com.example.event_management.service.springmail;

import com.example.event_management.entity.EventEntity;
import com.example.event_management.entity.RegisterEntity;
import com.example.event_management.service.qrcode.QrCodeGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@Service
public class QrMail {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private QrCodeGenerate qrCodeGenerate;

    public void sendQrMail(EventEntity event , RegisterEntity register) throws MessagingException {

        qrCodeGenerate.setInput( Integer.toString((int) event.getEventId()) + register.getRegisterId());
        try {
            qrCodeGenerate.generateQrCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(register.getRegisterEmail());
        helper.setSubject("Thư xác nhận tham gia sự kiện " + event.getEventName() );

        // attach the file into email body
        FileSystemResource file = new FileSystemResource(new File("src/main/resources/QrCode/QrTest.jpg"));

        helper.setText("<img src=\"cid:logo.png\"></img><div>My logo</div>", true);
        helper.addInline("logo.png", file);

        this.emailSender.send(message);
    }


}
