package com.example.liquidbits_springboot.restcontroller;

import com.example.liquidbits_springboot.model.Drink;
import com.example.liquidbits_springboot.repository.DrinkRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.liquidbits_springboot.api.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/drinks")
public class DrinkRestController {

    private static final Logger logger = LogManager.getLogger(DrinkRestController.class);
    private static final String className = "DrinkRestController";

    @Autowired
    DrinkRepository drinkRepository;

    // http://localhost:8082/
    @GetMapping(value = "{drinkId}")
    public ResponseEntity<?> getByIdPV(@PathVariable Integer drinkId){
        logger.info(LogUtils.info(className, "getByIdPV", String.format("(%d)",drinkId)));

        ResponseEntity<?> result;
        Optional<Drink> optDrink = drinkRepository.findById(drinkId);
        if (optDrink.isPresent()){
            Drink drink = optDrink.get();

            result = new ResponseEntity<Drink>(drink, HttpStatus.OK);
        }
        else {
            result = new ResponseEntity<>(String.format("Getränkeausschank mit der Id = %d nicht vorhanden", drinkId),
                    HttpStatus.NO_CONTENT);
        }
        return result;
    }


    @PutMapping(value = "")
    public ResponseEntity<?> add(@Valid @RequestBody Drink drink, BindingResult bindingResult) {
        logger.info(LogUtils.info(className, "add", String.format("(%s)",drink)));

        boolean error = false;
        String errorMessage = "";

        if(!error) {
            error = bindingResult.hasErrors();
            errorMessage = bindingResult.toString();
        }

        if(!error) {
            try {
                drinkRepository.save(drink);
            } catch (Exception e) {
                e.printStackTrace();
                error = true;
                errorMessage = e.toString();
            }
        }

        ResponseEntity<?> result;
        if(!error) {
            result = new ResponseEntity<Drink>(drink, HttpStatus.OK);
        }
        else {
            result = new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return result;
    }



}
