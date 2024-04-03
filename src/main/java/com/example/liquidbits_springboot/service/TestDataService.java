package com.example.liquidbits_springboot.service;

import com.example.liquidbits_springboot.utilities.LogUtils;
import com.example.liquidbits_springboot.model.*;
import com.example.liquidbits_springboot.repository.ContainerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TestDataService {

    private static final Logger logger = LogManager.getLogger(TestDataService.class);

    @Autowired
    ContainerRepository containerRepository;

    @Autowired
    private EntityManager entityManager; // Spring Data JPA EntityManager

    @Autowired
    ContainerService containerService;

    @Transactional
    public void testDataService() {

        entityManager.createQuery("DELETE FROM Drink ").executeUpdate();

        Session session = entityManager.unwrap(Session.class);

        // Heutiges Datum
        LocalDate currentDate = LocalDate.now();

        // Durchlaufe jeden Tag des aktuellen Jahres
        for (int day = 1; day <= currentDate.getDayOfYear(); day++) {
            // Überprüfe, ob das Datum gültig ist (ignoriere den 30. Februar)
            if (isValidDate(currentDate.getYear(), currentDate.getMonthValue(), day)) {
                // Erstelle ein LocalDateTime-Objekt für den aktuellen Tag um 08:00 Uhr
                //LocalDateTime startTime = LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), day, 8, 0);

                LocalDate date = LocalDate.ofYearDay(currentDate.getYear(), day);
                LocalDateTime startTime = date.atTime(8,0);

                // Erstelle ein LocalDateTime-Objekt für den aktuellen Tag um 19:00 Uhr
                LocalDateTime endTime = date.atTime(19,0);

                // Generiere x Datensätze für den aktuellen Tag
                for (int i = 0; i < 50; i++) {
                    // Generiere eine zufällige Zeit zwischen 08:00 und 19:00 Uhr
                    Timestamp randomTimeStamp = Timestamp.valueOf(generateRandomTime(startTime, endTime));

                    // Generiere zufällige drinkType_id (1, 2 oder 3)
                    int drinkType = ThreadLocalRandom.current().nextInt(1, 4);



                    List<Container> allTappedContainers = containerRepository.findContainersByTappedIsNotNullAndAndUntappedIsNull();
                    for (Container container : allTappedContainers) {
                        if (Container.calcBarrelLevel(container) <= 0) {
                            // Erstelle neuen Container mit passender drinktype_id
                            Container newContainer = new Container();
                            newContainer.setTapped(LocalDateTime.now());
                            newContainer.setSizeMl(container.getSizeMl());
                            newContainer.setDrinkType(container.getDrinkType());
                            newContainer.setStatus("OK");
                            container.setUntapped(LocalDateTime.now());


                            // Speichere den neuen Container in der Datenbank
                            containerRepository.save(newContainer);
                            containerRepository.save(container);


                            logger.info(LogUtils.info(TestDataService.class.getSimpleName(), "testDataGenerator",
                                    String.format("Füllstand: %f, Container: %d ", Container.calcBarrelLevel(container), container.getContainerId())));

                            System.out.printf("Füllstand: %f, Container: %d ", Container.calcBarrelLevel(container), container.getContainerId());


                        }

                        logger.info(LogUtils.info(TestDataService.class.getSimpleName(), "testDataGeneratorAusserhalbIf",
                                String.format("Füllstand: %f, Container: %d ", Container.calcBarrelLevel(container), container.getContainerId())));

                    }




                    int containerId;
                    if (drinkType == 1) {
                        containerId = containerRepository.findContainerByDrinkType_DrinkTypeIdAndUntappedIsNullAndTappedIsNotNull(1).get().getContainerId();
                    }
                    else if (drinkType == 2) {
                        containerId = containerRepository.findContainerByDrinkType_DrinkTypeIdAndUntappedIsNullAndTappedIsNotNull(2).get().getContainerId();
                    } else {
                        containerId = containerRepository.findContainerByDrinkType_DrinkTypeIdAndUntappedIsNullAndTappedIsNotNull(3).get().getContainerId();
                    }














                    // Generiere amount basierend auf drinkType_id
                    int amount;
                    if (drinkType == 1) {
                        amount = ThreadLocalRandom.current().nextInt(2) == 0 ? 300 : 500;
                    } else if (drinkType == 2) {
                        amount = ThreadLocalRandom.current().nextInt(2) == 0 ? 125 : 250;
                    } else {
                        amount = ThreadLocalRandom.current().nextInt(2) == 0 ? 250 : 500;
                    }

                    DrinkType drinkTypeEntity = new DrinkType();
                    drinkTypeEntity.setDrinkTypeId(drinkType);

                    Container containerEntity = new Container();
                    containerEntity.setContainerId(containerId);

                    Device device = new Device();
                    device.setDeviceId(1);

                    User user = new User();
                    user.setUserId(ThreadLocalRandom.current().nextInt(1,4));





                    Drink drink = new Drink(amount, drinkTypeEntity, containerEntity, device, user, randomTimeStamp);

                    session.save(drink);



                    /*
                    // Erstelle den INSERT-Befehl mit den zufälligen Werten
                    String insertStatement = String.format("INSERT INTO DRINK(amount, drinktype_id, container_id, device_id, user_id, TIMESTAMP) " +
                                    "VALUES (%d, %d, %d, 1, %d, '%s');",
                            amount, // amount
                            drinkType, // drinktype_id
                            containerId, // container_id
                            ThreadLocalRandom.current().nextInt(1, 4), // user_id
                            randomTime); // TIMESTAMP

                    // Ausgabe des INSERT-Befehls
                    System.out.println(insertStatement);

                     */
                }
            }
        }
    }

    // Methode zur Generierung einer zufälligen Zeit zwischen startTime und endTime
    private static LocalDateTime generateRandomTime(LocalDateTime startTime, LocalDateTime endTime) {
        long startMillis = startTime.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endMillis = endTime.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();
        long randomMillis = ThreadLocalRandom.current().nextLong(startMillis, endMillis + 1);
        return LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(randomMillis), java.time.ZoneId.systemDefault());
    }

    // Methode zur Überprüfung, ob das Datum gültig ist (ignoriert den 30. Februar)
    private static boolean isValidDate(int year, int month, int day) {
        if (month == 02 && day == 30 || month == 02 && day == 31 || month == 02 && day == 31) {
            return false;
        }
        return true;
    }
}

