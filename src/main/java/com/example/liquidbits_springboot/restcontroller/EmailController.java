package com.example.liquidbits_springboot.restcontroller;

import com.example.liquidbits_springboot.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        emailService.sendEmail(to, subject, text);
        return "E-Mail erfolgreich gesendet!";
    }
}
