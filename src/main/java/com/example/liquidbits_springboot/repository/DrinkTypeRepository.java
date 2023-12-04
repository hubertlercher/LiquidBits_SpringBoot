package com.example.liquidbits_springboot.repository;

import com.example.liquidbits_springboot.model.Container;
import com.example.liquidbits_springboot.model.DrinkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DrinkTypeRepository extends JpaRepository<DrinkType, Integer> {
}
