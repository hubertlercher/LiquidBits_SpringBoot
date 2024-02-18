package com.example.liquidbits_springboot.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private TestDataService testDataService;

    @Autowired
    private ContainerService containerService;

    @Override
    public void run(String... args) throws Exception {
        //testDataService.testDataService(); // Aufruf der Methode testDataService
        //containerService.containerRefill();
    }
}
