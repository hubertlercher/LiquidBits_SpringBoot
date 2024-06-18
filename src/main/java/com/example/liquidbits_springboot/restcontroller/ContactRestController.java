package com.example.liquidbits_springboot.restcontroller;

import com.example.liquidbits_springboot.model.User;
import com.example.liquidbits_springboot.utilities.LogUtils;
import com.example.liquidbits_springboot.model.Contact;
import com.example.liquidbits_springboot.repository.ContactRepository;
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

    @PostMapping(value = "")
    public ResponseEntity<?> add(@Valid @RequestBody Contact contact, BindingResult bindingResult) {
        logger.info(LogUtils.info(className, "add", String.format("(%s)", contact)));

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

    @DeleteMapping(value = "{contactId}")
    public ResponseEntity<?> deleteByIdPV1(@PathVariable Integer contactId) {

        logger.info(LogUtils.info(className, "deleteByIdPV1", String.format("(%s)", contactId)));

        boolean error = false;
        String errorMessage = "";
        Contact contact = null;
        ResponseEntity<?> result;

        Optional<Contact> optContact = contactRepository.findById(contactId);
        if (!error) {
            if (optContact.isPresent()) {
                contact = optContact.get();
            } else {
                error = true;
                errorMessage = String.format("Kontakt mit der ID %d nicht gefunden", contactId);
            }
        }

        if (!error) {
            try {
                contactRepository.deleteById(contactId);
            } catch (Exception e) {
                error = true;
                errorMessage = e.toString();
            }
        }

        if (!error) {
            result = new ResponseEntity<Contact>(contact, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(errorMessage, HttpStatus.OK);
        }
        return result;
    }
}
