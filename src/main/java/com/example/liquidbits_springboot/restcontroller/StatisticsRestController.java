package com.example.liquidbits_springboot.restcontroller;

import com.example.liquidbits_springboot.api.LogUtils;
import com.example.liquidbits_springboot.dto.ContainerStatisticsDTO;
import com.example.liquidbits_springboot.dto.StatisticsDTO;
import com.example.liquidbits_springboot.dto.TimeStatisticsDTO;
import com.example.liquidbits_springboot.model.Container;
import com.example.liquidbits_springboot.model.Drink;
import com.example.liquidbits_springboot.repository.ContainerRepository;
import com.example.liquidbits_springboot.repository.DrinkRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://10.66.189.77")
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

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "")
    public StatisticsDTO getStats() {
        logger.info(LogUtils.info(className, "getStats"));

        StatisticsDTO stats = new StatisticsDTO();

        List<Container> containers = containerRepository.findAll();


        for (Container container : containers) {
            ContainerStatisticsDTO csDTO = new ContainerStatisticsDTO();
            TimeStatisticsDTO tsDTO = new TimeStatisticsDTO();

            csDTO.setName(container.getDrinkType().getName());
            csDTO.setBarrelLevel(Container.calcBarrelLevel(container));
            csDTO.setStatus(Container.setStatusInDTO(container));

            // ... Daten auswerten
            // ... all drinks from container
            for (Drink drink : container.getDrinks()) {

                //täglich
                //Gruppierung der Drinks nach Stunden und Summierung der Mengen
                Map<Integer, Integer> amountsByHour = container.getDrinks().stream()
                        .filter(d -> d.getTimestamp().toLocalDateTime().getDayOfMonth() == LocalDate.now().getDayOfMonth())
                        .collect(Collectors.groupingBy(
                                d -> d.getTimestamp().getHours(),
                                Collectors.summingInt(Drink::getAmount)
                        ));
                // Erzeugen einer Liste mit Werten, auch für fehlende Stunden mit dem Wert 0
                List<Integer> amountsForDay = new ArrayList<>();
                for (int i = 0; i < 24; i++) {
                    amountsForDay.add(amountsByHour.getOrDefault(i, 0));
                }

                // Setzen der Liste in das tsDTO-Objekt
                tsDTO.setDaily(amountsForDay);

                //monatlich
                //Gruppierung der Drinks nach Stunden und Summierung der Mengen
                Map<Integer, Integer> amountsByDay = container.getDrinks().stream()
                        .filter(d -> d.getTimestamp().toLocalDateTime().getMonthValue() == LocalDate.now().getMonthValue())
                        .collect(Collectors.groupingBy(
                                d -> d.getTimestamp().getDay(),
                                Collectors.summingInt(Drink::getAmount)
                        ));
                // Erzeugen einer Liste mit Werten, auch für fehlende Stunden mit dem Wert 0
                List<Integer> amountsForMonth = new ArrayList<>();
                for (int i = 0; i < LocalDate.now().getMonth().length(LocalDate.now().isLeapYear()); i++) {
                    amountsForMonth.add(amountsByDay.getOrDefault(i, 0));
                }
                // Setzen der Liste in das tsDTO-Objekt
                tsDTO.setMonthly(amountsForMonth);

                //jährlich
                Map<Integer, Integer> amountsByMonth = container.getDrinks().stream()
                        .filter(d -> d.getTimestamp().toLocalDateTime().getYear() == LocalDate.now().getYear())
                        .collect(Collectors.groupingBy(
                                d -> d.getTimestamp().getMonth(),
                                Collectors.summingInt(Drink::getAmount)
                        ));
                // Erzeugen einer Liste mit Werten, auch für fehlende Stunden mit dem Wert 0
                List<Integer> amountsForYear = new ArrayList<>();
                for (int i = 0; i < 12; i++) {
                    amountsForYear.add(amountsByMonth.getOrDefault(i, 0));
                }

                // Setzen der Liste in das tsDTO-Objekt
                tsDTO.setAnnually(amountsForYear);


            }

            stats.getDrinkStatisticsBarrel().add(csDTO);
            stats.getDrinkStatisticsTime().add(tsDTO);

        }

//        java.util.Map<String, Object> properties = new HashMap<>();
//        List<Drink> drinks = drinkRepository.findAll();
//
//        drinks
//                .stream()
//                .collect(Collectors.groupingBy(drink -> drink.getDrinkType().getName()))
//
//
//
//        int dailyDispensed = drinks.stream()
//                .filter(drink -> drink.getTimestamp().equals(LocalDate.now()))
//                .mapToInt(d -> d.getAmount())
//                .sum();
//
//
//        int monthlyDispensed = drinks.stream()
//                .filter(drink -> drink.getTimestamp().getMonth() == LocalDate.now().getMonthValue())
//                .mapToInt(d -> d.getAmount())
//                .sum();
//        properties = Map.of("beer", monthlyDispensed);
//
//        int yearlyDispensed = drinks.stream()
//                .filter(drink -> drink.getTimestamp().getYear() == LocalDate.now().getYear())
//                .mapToInt(d -> d.getAmount())
//                .sum();
//
//        properties.put("yearly", yearlyDispensed);
//
//


        return stats;

    }
}
