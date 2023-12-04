package com.example.liquidbits_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LiquidBitsSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiquidBitsSpringBootApplication.class, args);
    }

}
