package com.example.liquidbits_springboot.restcontroller;

import com.example.liquidbits_springboot.api.LogUtils;
import com.example.liquidbits_springboot.model.Contact;
import com.example.liquidbits_springboot.model.Container;
import com.example.liquidbits_springboot.repository.ContactRepository;
import com.example.liquidbits_springboot.repository.ContainerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/contacts")
public class ContactRestController {

    private static final Logger logger = LogManager.getLogger(ContactRestController.class);
    private static final String className = "ContactRestController";

    @Autowired
    ContactRepository contactRepository;

    // http://localhost:8082/contacts

    @GetMapping(value = "")
    public ResponseEntity<?> getAll() {
        logger.info(LogUtils.info(className, "getAll"));

        ResponseEntity<?> result;
        List<Contact> contacts = contactRepository.findAll();
        result = new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);

        return result;
    }


    @PutMapping(value = "")
    public ResponseEntity<?> update(@Valid @RequestBody Contact contact, BindingResult bindingResult) {
        logger.info(LogUtils.info(className, "update", String.format("(%s)", contact)));

        boolean error = false;
        String errorMessage = "";

        if (!error) {
            error = bindingResult.hasErrors();
            errorMessage = bindingResult.toString();
        }

        if (!error) {
            try {
                contactRepository.save(contact);
            } catch (Exception e) {
                e.printStackTrace();
                error = true;
                errorMessage = e.toString();
            }
        }

        ResponseEntity<?> result;
        if (!error) {
            result = new ResponseEntity<Contact>(contact, HttpStatus.OK);
        } else {
            result = new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return result;
    }


}
