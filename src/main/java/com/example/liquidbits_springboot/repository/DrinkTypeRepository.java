package com.example.liquidbits_springboot.repository;

import com.example.liquidbits_springboot.model.Container;
import com.example.liquidbits_springboot.model.DrinkType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface DrinkTypeRepository extends JpaRepository<DrinkType, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE DrinkType d SET d.drinkSizeS = :newDrinkSizeS WHERE d.name = :drinkTypeName")
    void updateDrinkSizeSByName(String drinkTypeName, int newDrinkSizeS);

    @Transactional
    @Modifying
    @Query("UPDATE DrinkType d SET d.drinkSizeL = :newDrinkSizeL WHERE d.name = :drinkTypeName")
    void updateDrinkSizeLByName(String drinkTypeName, int newDrinkSizeL);

    @Transactional
    @Modifying
    @Query("UPDATE DrinkType d SET d.intensity = :newIntensity WHERE d.name = :drinkTypeName")
    void updateIntensityByName(String drinkTypeName, Integer newIntensity);

    @Transactional
    @Modifying
    @Query("UPDATE DrinkType d SET d.drinkSizeS = :newDrinkSizeS WHERE d.drinkTypeId = :drinkTypeId")
    void updateDrinkSizeSById(int drinkTypeId, int newDrinkSizeS);

    @Transactional
    @Modifying
    @Query("UPDATE DrinkType d SET d.drinkSizeL = :newDrinkSizeL WHERE d.drinkTypeId = :drinkTypeId")
    void updateDrinkSizeLById(int drinkTypeId, int newDrinkSizeL);


    @Transactional
    @Modifying
    @Query("UPDATE DrinkType d SET d.intensity = :newIntensity WHERE d.drinkTypeId = :drinkTypeId")
    void updateIntensityById(int drinkTypeId, int newIntensity);


}
