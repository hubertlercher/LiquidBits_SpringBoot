package com.example.liquidbits_springboot.dto;

import com.example.liquidbits_springboot.model.Container;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.JoinColumn;

import java.time.LocalDate;

public class ContainerStatisticsDTO {
    private int drinkTypeId;
    private String name;
    private String status;
    private double barrelLevel;
    private int drinkSizeL;
    private int drinkSizeS;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate lastMaintenance;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate nextMaintenance;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate lastCleaning;
    private Integer intensity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBarrelLevel() {
        return barrelLevel;
    }

    public void setBarrelLevel(double barrelLevel) {
        this.barrelLevel = barrelLevel;
    }

    public int getDrinkSizeL() {
        return drinkSizeL;
    }

    public void setDrinkSizeL(int drinkSizeL) {
        this.drinkSizeL = drinkSizeL;
    }

    public int getDrinkSizeS() {
        return drinkSizeS;
    }

    public void setDrinkSizeS(int drinkSizeS) {
        this.drinkSizeS = drinkSizeS;
    }

    public LocalDate getLastMaintenance() {
        return lastMaintenance;
    }

    public void setLastMaintenance(LocalDate lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }

    public LocalDate getNextMaintenance() {
        return nextMaintenance;
    }

    public void setNextMaintenance(LocalDate nextMaintenance) {
        this.nextMaintenance = nextMaintenance;
    }

    public int getDrinkTypeId() {
        return drinkTypeId;
    }

    public void setDrinkTypeId(int drinkTypeId) {
        this.drinkTypeId = drinkTypeId;
    }

    public Integer getIntensity() {
        return intensity;
    }

    public void setIntensity(Integer intensity) {
        this.intensity = intensity;
    }

    public LocalDate getLastCleaning() {
        return lastCleaning;
    }

    public void setLastCleaning(LocalDate lastCleaning) {
        this.lastCleaning = lastCleaning;
    }
}

