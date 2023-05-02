package com.recykal.ticketer.service;

import com.recykal.ticketer.service.EmailService.EmailServiceDeclaration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailServiceDeclaration {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to) {
        SimpleMailMessage sampleMail = new SimpleMailMessage();
        sampleMail.setTo(to);
        sampleMail.setSubject("Invitation to onboard you");
        sampleMail.setText("Hello User,\n" +
                "\n" +
                "Thank you for your interest in our application!\n" +
                "\n" +
                "To sign up, please follow this link : http://192.168.0.225:3000/signup\n" +
                "\n" +
                "If you have any questions or need assistance, please let us know.\n" +
                "\n" +
                "\n" +
                "\n" +
                "Best wishes,\n" +
                "\n" +
                "Admin");
        this.mailSender.send(sampleMail);
    }
}
