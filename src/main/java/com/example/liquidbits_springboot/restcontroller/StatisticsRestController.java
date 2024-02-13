package com.example.liquidbits_springboot.restcontroller;

import com.example.liquidbits_springboot.api.ErrorsUtils;
import com.example.liquidbits_springboot.api.LogUtils;
import com.example.liquidbits_springboot.dto.*;
import com.example.liquidbits_springboot.model.Container;
import com.example.liquidbits_springboot.model.Drink;
import com.example.liquidbits_springboot.model.DrinkType;
import com.example.liquidbits_springboot.model.User;
import com.example.liquidbits_springboot.repository.ContainerRepository;
import com.example.liquidbits_springboot.repository.DrinkRepository;
import com.example.liquidbits_springboot.repository.DrinkTypeRepository;
import com.example.liquidbits_springboot.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("stats")
public class StatisticsRestController {

    //region properties
    private static final Logger logger = LogManager.getLogger(StatisticsRestController.class);
    private static final String className = "StatisticsRestController";
    //endregion

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    ContainerRepository containerRepository;

    @Autowired
    DrinkTypeRepository drinkTypeRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "")
    public StatisticsDTO getStats() {
        logger.info(LogUtils.info(className, "getStats"));

        StatisticsDTO stats = new StatisticsDTO();

        List<Container> containersTapped = containerRepository.findContainersByTappedIsNotNullAndAndUntappedIsNull();
        List<DrinkType> drinkTypes = drinkTypeRepository.findAll();




        for (Container container : containersTapped) {

            ContainerStatisticsDTO csDTO = new ContainerStatisticsDTO();

            csDTO.setDrinkTypeId(container.getDrinkType().getDrinkTypeId());
            csDTO.setName(container.getDrinkType().getName());
            csDTO.setBarrelLevel(Container.calcBarrelLevel(container));
            csDTO.setLastMaintenance(container.getDrinkType().getLastMaintenance());
            csDTO.setNextMaintenance(csDTO.getLastMaintenance().plusMonths(6));
            csDTO.setLastCleaning(container.getDrinkType().getLastCleaning());
            csDTO.setDrinkSizeS(container.getDrinkType().getDrinkSizeS());
            csDTO.setDrinkSizeL(container.getDrinkType().getDrinkSizeL());
            csDTO.setStatus(Container.setStatusInDTO(container));
            csDTO.setIntensity(container.getDrinkType().getIntensity());
            stats.getDrinkStatisticsBarrel().add(csDTO);


        }


        for(DrinkType drinkType : drinkTypes) {

            TimeStatisticsDTO tsDTO = new TimeStatisticsDTO();
            DrinkStatisticsDTO dsDTO = new DrinkStatisticsDTO();
            tsDTO.setName(drinkType.getName());
            tsDTO.setDate(LocalDate.of(2023,12,19));
            dsDTO.setName(drinkType.getName());

            // ... Daten auswerten - StatisticsTime
            // ... all drinks from container
            for (Drink drink : drinkType.getDrinks()) {

                //täglich
                //Gruppierung der Drinks nach Stunden und Summierung der Mengen
                Map<Integer, Double> amountsByHour = drinkType.getDrinks().stream()
                        .filter(d -> d.getTimestamp().toLocalDateTime().getDayOfMonth() == LocalDate.of(2023,12,19).getDayOfMonth())
                        .collect(Collectors.groupingBy(
                                d -> d.getTimestamp().getHours(),
                                Collectors.summingDouble(Drink::getAmount)
                        ));
                // Erzeugen einer Liste mit Werten, auch für fehlende Stunden mit dem Wert 0
                List<Double> amountsForDay = new ArrayList<>();
                for (int i = 0; i < 24; i++) {
                    amountsForDay.add(amountsByHour.getOrDefault(i, Double.valueOf(0)));
                }

                // Setzen der Liste in das tsDTO-Objekt
                tsDTO.setDaily(amountsForDay);

                //DrinkStatistics Wochen definieren

                //DrinkStatistics Berechnung wöchentlich
                /*drinkType.getDrinks().stream()
                        .filter(d -> d.getTimestamp().toLocalDateTime().isAfter(startOfWeek) && d.getTimestamp().toLocalDateTime().isBefore(endOfWeek))
                        .count();*/

                //monatlich
                //Gruppierung der Drinks nach Stunden und Summierung der Mengen
                Map<Integer, Double> amountsByDay = drinkType.getDrinks().stream()
                        .filter(d -> d.getTimestamp().toLocalDateTime().getMonthValue() == LocalDate.of(2023,12,19).getMonthValue())
                        .collect(Collectors.groupingBy(
                                d -> d.getTimestamp().toLocalDateTime().getDayOfMonth(),
                                Collectors.summingDouble(Drink::getAmount)
                        ));
                // Erzeugen einer Liste mit Werten, auch für fehlende Stunden mit dem Wert 0
                List<Double> amountsForMonth = new ArrayList<>();
                for (int i = 0; i < LocalDate.now().getMonth().length(LocalDate.now().isLeapYear()); i++) {
                    amountsForMonth.add(amountsByDay.getOrDefault(i, Double.valueOf(0)));
                }
                // Setzen der Liste in das tsDTO-Objekt
                tsDTO.setMonthly(amountsForMonth);

                //jährlich
                Map<Integer, Double> amountsByMonth = drinkType.getDrinks().stream()
                        .filter(d -> d.getTimestamp().toLocalDateTime().getYear() == LocalDate.of(2023,12,19).getYear())
                        .collect(Collectors.groupingBy(
                                d -> d.getTimestamp().getMonth(),
                                Collectors.summingDouble(Drink::getAmount)
                        ));
                // Erzeugen einer Liste mit Werten, auch für fehlende Stunden mit dem Wert 0
                List<Double> amountsForYear = new ArrayList<>();
                for (int i = 0; i < 12; i++) {
                    amountsForYear.add(amountsByMonth.getOrDefault(i, Double.valueOf(0)));
                }

                // Setzen der Liste in das tsDTO-Objekt
                tsDTO.setAnnually(amountsForYear);

            }

            stats.getDrinkStatisticsTime().add(tsDTO);

        }

        List<User> users = userRepository.findAll();

        for(User user : users) {
            UserStatisticsDTO usDTO = new UserStatisticsDTO();

            usDTO.setSurname(user.getSurname());
            usDTO.setFirstname(user.getFirstname());
            usDTO.setImage(user.getImage());

            for(Drink drink : user.getDrinks()) {

                double drinksServedL = user.getDrinks()
                        .stream()
                        .filter(d -> d.getTimestamp().toLocalDateTime().getMonthValue() == LocalDate.now().getMonthValue())
                        .mapToDouble(drinks -> drinks.getAmount())
                        .sum();

                drinksServedL = drinksServedL/1000;                                 //Umrechnung in Liter

                drinksServedL = Math.round(drinksServedL * 100.0) / 100.0;          //Runden auf zwei Nachkommastellen

                usDTO.setDrinksServedL(drinksServedL);
            }
            stats.getUserStatistics().add(usDTO);
        }


        return stats;
    }

    @GetMapping(value = "/drinkStatisticsBarrel/{drinkTypeId}")
    public ContainerStatisticsDTO getDrinkStatisticsBarrel(@PathVariable Integer drinkTypeId) {
        logger.info(LogUtils.info(className, "getDrinkStatisticsBarrel"));
        ContainerStatisticsDTO csDTO = new ContainerStatisticsDTO();

        Optional<Container> optionalContainer = containerRepository.findById(drinkTypeId);

        if(optionalContainer.isPresent()) {
            Container container = optionalContainer.get();
                csDTO.setName(container.getDrinkType().getName());
                csDTO.setBarrelLevel(Container.calcBarrelLevel(container));
                csDTO.setLastMaintenance(container.getDrinkType().getLastMaintenance());
                csDTO.setNextMaintenance(csDTO.getLastMaintenance().plusMonths(6));
                csDTO.setLastCleaning(container.getDrinkType().getLastCleaning());
                csDTO.setDrinkSizeS(container.getDrinkType().getDrinkSizeS());
                csDTO.setDrinkSizeL(container.getDrinkType().getDrinkSizeL());
                csDTO.setStatus(Container.setStatusInDTO(container));
        }

        return csDTO;
    }


    @PutMapping(value = "/drinkStatisticsBarrel/{drinkTypeName}/{drinkSizeS}/{drinkSizeL}/{intensity}")
    public void updateDrinkSizesIntensity(@PathVariable String drinkTypeName, @PathVariable int drinkSizeS, @PathVariable int drinkSizeL, @PathVariable int intensity) {
        logger.info(LogUtils.info(className, "updateDrinkSizes"));
        ContainerStatisticsDTO csDTO = new ContainerStatisticsDTO();

        drinkTypeRepository.updateDrinkSizeSByName(drinkTypeName, drinkSizeS);
        drinkTypeRepository.updateDrinkSizeLByName(drinkTypeName, drinkSizeL);
        drinkTypeRepository.updateIntensityByName(drinkTypeName, intensity);

    }







    @PutMapping(value = "")
    public ResponseEntity update(@Valid @RequestBody
                                 StatisticsDTO statisticsDTO, BindingResult bindingResult) {

        logger.info(LogUtils.info(className, "update", String.format("(%s)", statisticsDTO)));

        boolean error = false;
        String errorMessage = "";

        if (!error) {
            error = bindingResult.hasErrors();
            errorMessage = bindingResult.toString();
        }

        if (!error) {
            try {

                int[] drinkSizesS = statisticsDTO.getDrinkStatisticsBarrel()
                        .stream()
                        .flatMapToInt(containerStatisticsDTO -> IntStream.of(containerStatisticsDTO.getDrinkSizeS()))
                        .toArray();

                for (int i = 0; i < drinkSizesS.length; i++) {
                    drinkTypeRepository.updateDrinkSizeSById(i+1, drinkSizesS[i]);
                }


                int[] drinkSizesL = statisticsDTO.getDrinkStatisticsBarrel()
                        .stream()
                        .flatMapToInt(containerStatisticsDTO -> IntStream.of(containerStatisticsDTO.getDrinkSizeL()))
                        .toArray();

                for (int i = 0; i < drinkSizesL.length; i++) {
                    drinkTypeRepository.updateDrinkSizeLById(i+1, drinkSizesL[i]);
                }


            } catch (Exception e) {
                e.printStackTrace();
                error = true;
                errorMessage = ErrorsUtils.getErrorMessage(e);
            }
        }

        ResponseEntity<?> result;
        if (!error) {
            result = new ResponseEntity<StatisticsDTO>(statisticsDTO, HttpStatus.OK);
        } else {
            result = new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;

    }



    /*@PutMapping(value = "/drinkStatisticsBarrel/{drinkTypeId}/{drinkSizeS}/{drinkSizeL}")
    public ResponseEntity updateDrinkStatisticsBarrel(@Valid @RequestBody @PathVariable Integer drinkTypeId, @PathVariable Integer drinkSizeS,
                                @PathVariable Integer drinkSizeL, BindingResult bindingResult) {

        logger.info(LogUtils.info(className, "updateDrinkStatisticsBarrel", String.format("(S: %d. L: %d)", drinkSizeS, drinkSizeL )));

        boolean error = false;
        String errorMessage = "";

        if (!error) {
            error = bindingResult.hasErrors();
            errorMessage = bindingResult.toString();
        }

        if (!error) {
            try {

                drinkTypeRepository.updateDrinkSizeSById(drinkTypeId, drinkSizeS);
                drinkTypeRepository.updateDrinkSizeLById(drinkTypeId, drinkSizeL);

            } catch (Exception e) {
                e.printStackTrace();
                error = true;
                errorMessage = ErrorsUtils.getErrorMessage(e);
            }
        }

        ResponseEntity<?> result;
        if (!error) {
            result = new ResponseEntity<ContainerStatisticsDTO>(HttpStatus.OK);
        } else {
            result = new ResponseEntity<String>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;

    }*/







}
