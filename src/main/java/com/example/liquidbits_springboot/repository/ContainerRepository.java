package com.example.liquidbits_springboot.repository;

import com.example.liquidbits_springboot.model.Container;
import com.example.liquidbits_springboot.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContainerRepository extends JpaRepository<Container, Integer> {

    List<Container> findContainerByTappedIsNotNullAndAndUntappedIsNull();


}
