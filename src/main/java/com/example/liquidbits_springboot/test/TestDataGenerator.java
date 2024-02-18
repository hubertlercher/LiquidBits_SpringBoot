package com.example.liquidbits_springboot.test;

import com.example.liquidbits_springboot.model.Container;
import com.example.liquidbits_springboot.repository.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class TestDataGenerator {

    @Autowired
    ContainerRepository containerRepository;

    public static void main(String[] args) {


        // Heutiges Datum
        LocalDate currentDate = LocalDate.now();

        // Durchlaufe jeden Tag des aktuellen Jahres
        for (int day = 1; day <= currentDate.lengthOfYear(); day++) {
            // Überprüfe, ob das Datum gültig ist (ignoriere den 30. Februar)
            if (isValidDate(currentDate.getYear(), currentDate.getMonthValue(), day)) {
                // Erstelle ein LocalDateTime-Objekt für den aktuellen Tag um 08:00 Uhr
                LocalDateTime startTime = LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), day, 8, 0);

                // Erstelle ein LocalDateTime-Objekt für den aktuellen Tag um 19:00 Uhr
                LocalDateTime endTime = LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), day, 19, 0);

                // Generiere 150 Datensätze für den aktuellen Tag
                for (int i = 0; i < 150; i++) {
                    // Generiere eine zufällige Zeit zwischen 08:00 und 19:00 Uhr
                    LocalDateTime randomTime = generateRandomTime(startTime, endTime);

                    // Generiere zufällige drinkType_id (1, 2 oder 3)
                    int drinkType = ThreadLocalRandom.current().nextInt(1, 4);












                    // Bestimme die container_id basierend auf drinkType_id
                    int containerId;
                    if (drinkType == 1) {
                        containerId = 5;
                    } else if (drinkType == 2) {
                        containerId = 4;
                    } else {
                        containerId = 3;
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
        if (month == 02 && day == 30 || month == 02 && day == 31) {
            return false;
        }
        return true;
    }








}
