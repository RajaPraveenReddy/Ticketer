package com.recykal.ticketer.controller;

import com.recykal.ticketer.entity.EmailInvite;
import com.recykal.ticketer.service.EmailService.EmailServiceDeclaration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class EmailController {
    @Autowired
    private EmailServiceDeclaration emailSenderService;

    @PostMapping("/sendMail")
    public ResponseEntity sendMail(@RequestBody EmailInvite emailMessage){
        this.emailSenderService.sendEmail(emailMessage.getTo());
        return ResponseEntity.ok("Successfully sent");
    }
}
