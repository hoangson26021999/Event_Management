package com.example.event_management.service.Mail;

import com.example.event_management.service.QrCode.QrCodeGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Service
public class QrMail {

    private String user = "EvMaDHS@gmail.com" ;
    private String password = "evmaadmin" ;

    @Value("${path}")
    private String path_qrcode ;

    @Autowired
    private QrCodeGenerate generater ;


    public void sendQrcode(String receive_mail ) throws MessagingException {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage mailMessage;

        // Step1: setup Mail Server
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        // Step2: get Mail Session
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        mailMessage = new MimeMessage(getMailSession);

        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(receive_mail)); //Thay abc bằng địa chỉ người nhận

        // Bạn có thể chọn CC, BCC
        // generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("cc@gmail.com")); //Địa chỉ cc gmail

        mailMessage.setSubject("QrCode tham dự sự kiện");

        // Tạo phần gửi message
        BodyPart messagePart = new MimeBodyPart();
        messagePart.setText("EvMa kính gửi bạn QrCode để tham dự sự kiện.");
        String htmlText = "<img src=\"cid:image\">";
        messagePart.setContent(htmlText, "text/html");

        BodyPart filePart = new MimeBodyPart();

        DataSource fds = new FileDataSource(path_qrcode);
        filePart.setDataHandler(new DataHandler(fds));
        filePart.setHeader("Content-ID", "<image>");


        // Gộp message và file vào để gửi đi
        Multipart multipart = new MimeMultipart("related");
        multipart.addBodyPart(messagePart);
        multipart.addBodyPart(filePart);
        mailMessage.setContent(multipart);


        // Step3: Send mail
        Transport transport = getMailSession.getTransport("smtp");

        transport.connect("smtp.gmail.com", user, password);
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }
}
