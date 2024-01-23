package com.example.liquidbits_springboot.restcontroller;

import com.example.liquidbits_springboot.model.Drink;
import com.example.liquidbits_springboot.model.Email;
import com.example.liquidbits_springboot.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public String sendEmail(@Valid @RequestBody Email email) {
        emailService.sendEmail(email.getTo(), email.getSubject(), email.getText());
        return "E-Mail erfolgreich gesendet!";
    }

}
