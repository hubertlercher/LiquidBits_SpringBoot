package com.example.liquidbits_springboot.restcontroller;

import com.example.liquidbits_springboot.utilities.LogUtils;
import com.example.liquidbits_springboot.model.DrinkType;
import com.example.liquidbits_springboot.model.MaintenanceLog;
import com.example.liquidbits_springboot.repository.DrinkTypeRepository;
import com.example.liquidbits_springboot.repository.MaintenanceLogRepository;
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
import java.util.stream.Collectors;


@RestController
@RequestMapping("/logs")
public class MaintenanceLogRestController {

    private static final Logger logger = LogManager.getLogger(MaintenanceLogRestController.class);
    private static final String className = "MaintenanceLogRestController";

    @Autowired
    MaintenanceLogRepository maintenanceLogRepository;
    @Autowired
    DrinkTypeRepository drinkTypeRepository;


    // http://localhost:8082/
    @GetMapping(value = "")
    public ResponseEntity<?> getAllLogs(){
        logger.info(LogUtils.info(className, "getAllLogs"));

        ResponseEntity<?> result;

        List<MaintenanceLog> logs = maintenanceLogRepository.findAll();

        result = new ResponseEntity<List<MaintenanceLog>>(logs, HttpStatus.OK);

        return result;
    }


    @GetMapping("{drinkTypeId}")
    public ResponseEntity<?> getLogsByDrinkTypeId(@PathVariable int drinkTypeId) {
        logger.info(LogUtils.info(className, "getLogsByDrinkTypeId", String.format("(%s)",drinkTypeId)));

        ResponseEntity<?> result;

        Optional<DrinkType> optDrinkType = drinkTypeRepository.findById(drinkTypeId);

        if(optDrinkType.isPresent()) {
            DrinkType drinkType = optDrinkType.get();
            List<MaintenanceLog> logs = drinkType.getLogs().stream().collect(Collectors.toList());
            result = new ResponseEntity<List<MaintenanceLog>>(logs, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(String.format("DrinkType mit der ID %d nicht verfügbar", drinkTypeId), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }



    @PutMapping(value = "")
    public ResponseEntity<?> add(@Valid @RequestBody MaintenanceLog log, BindingResult bindingResult) {
        logger.info(LogUtils.info(className, "add", String.format("(%s)",log)));

        boolean error = false;
        String errorMessage = "";

        if(!error) {
            error = bindingResult.hasErrors();
            errorMessage = bindingResult.toString();
        }

        if(!error) {
            try {
                maintenanceLogRepository.save(log);
            } catch (Exception e) {
                e.printStackTrace();
                error = true;
                errorMessage = e.toString();
            }
        }

        ResponseEntity<?> result;
        if(!error) {
            result = new ResponseEntity<MaintenanceLog>(log, HttpStatus.OK);
        }
        else {
            result = new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }

}
