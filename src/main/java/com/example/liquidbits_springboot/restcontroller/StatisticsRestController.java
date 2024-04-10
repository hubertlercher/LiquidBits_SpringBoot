package com.example.liquidbits_springboot.restcontroller;

import com.example.liquidbits_springboot.utilities.ErrorsUtils;
import com.example.liquidbits_springboot.utilities.LogUtils;
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


        for (DrinkType drinkType : drinkTypes) {
            TimeStatisticsDTO tsDTO = new TimeStatisticsDTO();
            tsDTO.setName(drinkType.getName());
            tsDTO.setDate(LocalDate.now());
            // ... Daten auswerten - StatisticsTime
            // ... all drinks from container
            //Stunden pro Tag
            //Gruppierung der Drinks nach Stunden und Summierung der Mengen
            Map<Integer, Double> amountsByHour = drinkType.getDrinks().stream()
                    .filter(d -> d.getTimestamp().toLocalDateTime().getDayOfMonth()
                            == LocalDate.now().getDayOfMonth())
                    .collect(Collectors.groupingBy(
                            d -> d.getTimestamp().getHours(),
                            Collectors.summingDouble(Drink::getAmount)
                    ));

            // Erzeugen einer Liste mit Werten, auch für fehlende Stunden mit dem Wert 0
            List<Double> amountsForDay = new ArrayList<>();
            for (int i = 0; i < 24; i++) {
                amountsForDay.add(amountsByHour.getOrDefault(i, Double.valueOf(0)));
            }

            //Werte fürs Frontend in Liter umrechnen
            for (int i = 0; i < amountsForDay.size(); i++) {
                amountsForDay.set(i, amountsForDay.get(i) / 1000.0);
            }

            //Auswertung verschiedener Getränkegrößen
            Map<Character, Long> drinksPerSizeDaily = drinkType.getDrinks()
                    .stream()
                    .filter(d -> d.getTimestamp().toLocalDateTime().getDayOfMonth() == LocalDate.now().getDayOfMonth())
                    .collect(Collectors.groupingBy(
                            d -> d.isDrinkSizeSOrL(),
                            Collectors.counting()
                    ));


            // Setzen der Liste in das tsDTO-Objekt
            tsDTO.setDaily(amountsForDay);
            tsDTO.setSizesDaily(drinksPerSizeDaily);


            //Tage pro Monat
            //Gruppierung der Drinks nach Stunden und Summierung der Mengen
            Map<Integer, Double> amountsByDay = drinkType.getDrinks().stream()
                    .filter(d -> d.getTimestamp().toLocalDateTime().getMonthValue() == LocalDate.now().getMonthValue())
                    .collect(Collectors.groupingBy(
                            d -> d.getTimestamp().toLocalDateTime().getDayOfMonth(),
                            Collectors.summingDouble(Drink::getAmount)
                    ));

            for (Double value : amountsByDay.values()) {
                value = value / 1000;
            }

            // Erzeugen einer Liste mit Werten, auch für fehlende Tage mit dem Wert 0
            List<Double> amountsForMonth = new ArrayList<>();
            for (int i = 0; i < LocalDate.now().getMonth().length(LocalDate.now().isLeapYear()); i++) {
                amountsForMonth.add(amountsByDay.getOrDefault(i + 1, Double.valueOf(0)));
            }

            //Werte fürs Frontend in Liter umrechnen
            for (int i = 0; i < amountsForMonth.size(); i++) {
                amountsForMonth.set(i, amountsForMonth.get(i) / 1000.0);
            }

            //Auswertung verschiedener Getränkegrößen
            Map<Character, Long> drinksPerSizeMonthly = drinkType.getDrinks()
                    .stream()
                    .filter(d -> d.getTimestamp().toLocalDateTime().getMonthValue() == LocalDate.now().getMonthValue())
                    .collect(Collectors.groupingBy(
                            d -> d.isDrinkSizeSOrL(),
                            Collectors.counting()
                    ));


            // Setzen der Liste in das tsDTO-Objekt
            tsDTO.setMonthly(amountsForMonth);
            tsDTO.setSizesMonthly(drinksPerSizeMonthly);


            //Monate pro Jahr
            Map<Integer, Double> amountsByMonth = drinkType.getDrinks().stream()
                    .filter(d -> d.getTimestamp().toLocalDateTime().getYear() == LocalDate.now().getYear())
                    .collect(Collectors.groupingBy(
                            d -> d.getTimestamp().toLocalDateTime().getMonth().getValue(),
                            Collectors.summingDouble(Drink::getAmount)
                    ));

            for (Double value : amountsByMonth.values()) {
                value = value / 1000;
            }

            // Erzeugen einer Liste mit Werten, auch für fehlende Stunden mit dem Wert 0
            List<Double> amountsForYear = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                amountsForYear.add(amountsByMonth.getOrDefault(i+1, Double.valueOf(0)));
            }

            //Werte fürs Frontend in Liter umrechnen
            for (int i = 0; i < amountsForYear.size(); i++) {
                amountsForYear.set(i, amountsForYear.get(i) / 1000.0);
            }

            //Auswertung verschiedener Getränkegrößen
            Map<Character, Long> drinksPerSizeAnnually = drinkType.getDrinks()
                    .stream()
                    .filter(d -> d.getTimestamp().toLocalDateTime().getYear() == LocalDate.now().getYear())
                    .collect(Collectors.groupingBy(
                            d -> d.isDrinkSizeSOrL(),
                            Collectors.counting()
                    ));


            // Setzen der Liste in das tsDTO-Objekt
            tsDTO.setAnnually(amountsForYear);
            tsDTO.setSizesAnnually(drinksPerSizeAnnually);

            stats.getDrinkStatisticsTime().add(tsDTO);

        }


        //}

        List<User> users = userRepository.findAll();
        for (User user : users) {
            UserStatisticsDTO usDTO = new UserStatisticsDTO();
            usDTO.setUserId(user.getUserId());
            usDTO.setSurname(user.getSurname());
            usDTO.setFirstname(user.getFirstname());
            usDTO.setImage(user.getImage());

            double drinksServedL = user.getDrinks()
                    .stream()
                    .filter(d -> d.getTimestamp().toLocalDateTime().getMonthValue() == LocalDate.now().getMonthValue())
                    .mapToDouble(drinks -> drinks.getAmount())
                    .sum();

            //Umrechnung in Liter
            drinksServedL = drinksServedL / 1000;
            //Runden auf zwei Nachkommastellen
            drinksServedL = Math.round(drinksServedL * 100.0) / 100.0;
            usDTO.setDrinksServedL(drinksServedL);

            stats.getUserStatistics().add(usDTO);
        }
        return stats;
    }
/*
    @GetMapping(value = "/drinkStatisticsBarrel/{drinkTypeId}")
    public ContainerStatisticsDTO getDrinkStatisticsBarrel(@PathVariable Integer drinkTypeId) {
        logger.info(LogUtils.info(className, "getDrinkStatisticsBarrel"));
        ContainerStatisticsDTO csDTO = new ContainerStatisticsDTO();

        Optional<Container> optionalContainer = containerRepository.findById(drinkTypeId);

        if (optionalContainer.isPresent()) {
            Container container = optionalContainer.get();
            csDTO.setDrinkTypeId(container.getDrinkType().getDrinkTypeId());
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
*/

    @PutMapping(value = "/drinkStatisticsBarrel/{drinkTypeName}")
    public void updateDrinkSizesIntensity(@PathVariable String drinkTypeName,
                                          @RequestParam(name = "S") int drinkSizeS,
                                          @RequestParam(name = "L") int drinkSizeL,
                                          @RequestParam(name = "intensity", required = false) Integer intensity) {
        logger.info(LogUtils.info(className, "updateDrinkSizes"));
        ContainerStatisticsDTO csDTO = new ContainerStatisticsDTO();

        drinkTypeRepository.updateDrinkSizeSByName(drinkTypeName, drinkSizeS);
        drinkTypeRepository.updateDrinkSizeLByName(drinkTypeName, drinkSizeL);
        drinkTypeRepository.updateIntensityByName(drinkTypeName, intensity);

    }
}
