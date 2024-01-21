package com.example.liquidbits_springboot.restcontroller;

import com.example.liquidbits_springboot.api.LogUtils;
import com.example.liquidbits_springboot.model.Container;
import com.example.liquidbits_springboot.model.DrinkType;
import com.example.liquidbits_springboot.repository.ContainerRepository;
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
@RequestMapping("/containers")
public class ContainerRestController {

    private static final Logger logger = LogManager.getLogger(ContainerRestController.class);
    private static final String className = "ContainerRestController";

    @Autowired
    ContainerRepository containerRepository;

    // http://localhost:8082/containers
    @GetMapping(value = "{containerId}")
    public ResponseEntity<?> getByIdPV(@PathVariable Integer containerId) {
        logger.info(LogUtils.info(className, "getByIdPV", String.format("(%d)", containerId)));

        ResponseEntity<?> result;
        Optional<Container> optContainer = containerRepository.findById(containerId);
        if (optContainer.isPresent()) {
            Container container = optContainer.get();
            result = new ResponseEntity<Container>(container, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(String.format("Container mit der Id = %d nicht vorhanden", containerId),
                    HttpStatus.NO_CONTENT);
        }
        return result;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getTappedContainers() {
        logger.info(LogUtils.info(className, "getTappedContainers"));

        ResponseEntity<?> result;
        List<Container> containers = containerRepository.findContainersByTappedIsNotNullAndAndUntappedIsNull();

        result = new ResponseEntity<List<Container>>(containers, HttpStatus.OK);

        return result;
    }

    @GetMapping(value = "/drinkTypeId/{drinkTypeId}")
    public ResponseEntity<?> getTappedContainersByDrinkTypeId(@PathVariable Integer drinkTypeId) {
        logger.info(LogUtils.info(className, "getTappedContainersByDrinkTypeId", String.format("(%d)", drinkTypeId)));

        ResponseEntity<?> result;
        Optional<Container> optContainer = containerRepository.findContainerByDrinkType_DrinkTypeIdAndUntappedIsNullAndTappedIsNotNull(drinkTypeId);

        if (optContainer.isPresent()) {
            Container container = optContainer.get();
            result = new ResponseEntity<Container>(container, HttpStatus.OK);
        } else {
        result = new ResponseEntity<>(String.format("DrinkType mit der Id = %d nicht vorhanden", drinkTypeId),
                HttpStatus.NO_CONTENT);
    }


        return result;
    }





    @PutMapping(value = "")
    public ResponseEntity<?> update(@Valid @RequestBody Container container, BindingResult bindingResult) {
        logger.info(LogUtils.info(className, "update", String.format("(%s)", container)));

        boolean error = false;
        String errorMessage = "";

        if (!error) {
            error = bindingResult.hasErrors();
            errorMessage = bindingResult.toString();
        }

        if (!error) {
            try {
                containerRepository.save(container);
            } catch (Exception e) {
                e.printStackTrace();
                error = true;
                errorMessage = e.toString();
            }
        }

        ResponseEntity<?> result;
        if (!error) {
            result = new ResponseEntity<Container>(container, HttpStatus.OK);
        } else {
            result = new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return result;
    }


}
