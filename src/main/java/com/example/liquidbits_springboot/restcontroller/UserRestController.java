package com.example.liquidbits_springboot.restcontroller;

import com.example.liquidbits_springboot.utilities.LogUtils;
import com.example.liquidbits_springboot.model.User;
import com.example.liquidbits_springboot.repository.UserRepository;
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
@RequestMapping("/users")
public class UserRestController {

    private static final Logger logger = LogManager.getLogger(UserRestController.class);
    private static final String className = "UserRestController";

    @Autowired
    UserRepository userRepository;

    // http://localhost:8081/users
    @GetMapping(value = "{userId}")
    public ResponseEntity<?> getByIdPV(@PathVariable Integer userId) {
        logger.info(LogUtils.info(className, "getByIdPV", String.format("(%d)", userId)));

        ResponseEntity<?> result;
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = optUser.get();

            result = new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(String.format("User mit der Id = %d nicht vorhanden", userId),
                    HttpStatus.NO_CONTENT);
        }
        return result;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getAll() {
        logger.info(LogUtils.info(className, "getDrinkTypes"));

        ResponseEntity<?> result;
        List<User> users = userRepository.findAll();

        result = new ResponseEntity<List<User>>(users, HttpStatus.OK);

        return result;
    }


    @PutMapping(value = "")
    public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult bindingResult) {
        logger.info(LogUtils.info(className, "update", String.format("(%s)", user)));

        boolean error = false;
        String errorMessage = "";

        if (!error) {
            error = bindingResult.hasErrors();
            errorMessage = bindingResult.toString();
        }

        if (!error) {
            try {
                userRepository.save(user);
            } catch (Exception e) {
                e.printStackTrace();
                error = true;
                errorMessage = e.toString();
            }
        }

        ResponseEntity<?> result;
        if (!error) {
            result = new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
            result = new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return result;
    }


    @DeleteMapping(value = "{userId}")
    public ResponseEntity<?> deleteByIdPV1(@PathVariable Integer userId) {

        logger.info(LogUtils.info(className, "deleteByIdPV1", String.format("(%s)", userId)));

        boolean error = false;
        String errorMessage = "";
        User user = null;
        ResponseEntity<?> result;

        Optional<User> optUser = userRepository.findById(userId);
        if (!error) {
            if (optUser.isPresent()) {
                user = optUser.get();
            } else {
                error = true;
                errorMessage = String.format("User mit der ID %d nicht gefunden", userId);
            }
        }

        if (!error) {
            try {
                userRepository.deleteById(userId);
            } catch (Exception e) {
                error = true;
                errorMessage = e.toString();
            }
        }

        if (!error) {
            result = new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(errorMessage, HttpStatus.OK);
        }
        return result;
    }

}