package com.example.liquidbits_springboot.service;

import com.example.liquidbits_springboot.model.Container;
import com.example.liquidbits_springboot.repository.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContainerService {

    @Autowired
    private ContainerRepository containerRepository;

    public void containerRefill() {
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
            }
        }
    }
}
