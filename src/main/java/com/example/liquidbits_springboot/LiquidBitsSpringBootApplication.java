package com.example.liquidbits_springboot;

import com.example.liquidbits_springboot.swaggerConfig.swaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableJpaAuditing
public class LiquidBitsSpringBootApplication extends swaggerConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(LiquidBitsSpringBootApplication.class, args);
    }

}
