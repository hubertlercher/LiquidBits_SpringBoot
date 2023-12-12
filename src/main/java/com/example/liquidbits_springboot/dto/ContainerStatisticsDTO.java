package com.example.liquidbits_springboot.dto;

import com.example.liquidbits_springboot.model.Container;

public class ContainerStatisticsDTO {

    private String name;
    private String status;
    private int barrelLevel;
    private int drinkSizeL;
    private int drinkSizeS;

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

    public int getBarrelLevel() {
        return barrelLevel;
    }

    public void setBarrelLevel(int barrelLevel) {
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
}
