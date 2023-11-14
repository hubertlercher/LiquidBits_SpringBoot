package com.example.liquidbits_springboot.controller;

import com.example.liquidbits_springboot.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/drinks")
public class DrinkController {

    @Autowired
    DrinkRepository drinkRepository;



}
