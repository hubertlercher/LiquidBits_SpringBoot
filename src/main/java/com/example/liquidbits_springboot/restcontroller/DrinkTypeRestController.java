package com.example.liquidbits_springboot.restcontroller;

import com.example.liquidbits_springboot.api.LogUtils;
import com.example.liquidbits_springboot.model.Drink;
import com.example.liquidbits_springboot.model.DrinkType;
import com.example.liquidbits_springboot.repository.DrinkRepository;
import com.example.liquidbits_springboot.repository.DrinkTypeRepository;
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
@RequestMapping("/drinkTypes")
public class DrinkTypeRestController {

    private static final Logger logger = LogManager.getLogger(DrinkTypeRestController.class);
    private static final String className = "DrinkTypeRestController";

    @Autowired
    DrinkTypeRepository drinkTypeRepository;

    // http://localhost:8081/drinkTypes
    @GetMapping(value = "")
    public ResponseEntity<?> getAll() {
        logger.info(LogUtils.info(className, "getDrinkTypes"));

        ResponseEntity<?> result;
        List<DrinkType> drinkTypes = drinkTypeRepository.findAll();

        result = new ResponseEntity<List<DrinkType>>(drinkTypes, HttpStatus.OK);

        return result;
    }

    @GetMapping(value = "{drinkTypeId}")
    public ResponseEntity<?> getByIdPV(@PathVariable Integer drinkTypeId) {
        logger.info(LogUtils.info(className, "getByIdPV", String.format("(%d)", drinkTypeId)));

        ResponseEntity<?> result;
        Optional<DrinkType> optDrinkType = drinkTypeRepository.findById(drinkTypeId);
        if (optDrinkType.isPresent()) {
            DrinkType drinkType = optDrinkType.get();

            result = new ResponseEntity<DrinkType>(drinkType, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(String.format("DrinkType mit der Id = %d nicht vorhanden", drinkTypeId),
                    HttpStatus.NO_CONTENT);
        }
        return result;
    }


    @PutMapping(value = "")
    public ResponseEntity<?> update(@Valid @RequestBody DrinkType drinktype, BindingResult bindingResult) {
        logger.info(LogUtils.info(className, "update", String.format("(%s)", drinktype)));

        boolean error = false;
        String errorMessage = "";

        if (!error) {
            error = bindingResult.hasErrors();
            errorMessage = bindingResult.toString();
        }

        if (!error) {
            try {
                drinkTypeRepository.save(drinktype);
            } catch (Exception e) {
                e.printStackTrace();
                error = true;
                errorMessage = e.toString();
            }
        }

        ResponseEntity<?> result;
        if (!error) {
            result = new ResponseEntity<DrinkType>(drinktype, HttpStatus.OK);
        } else {
            result = new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return result;
    }


}
