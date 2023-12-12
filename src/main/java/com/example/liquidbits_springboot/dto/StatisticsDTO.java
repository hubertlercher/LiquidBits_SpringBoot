package com.example.liquidbits_springboot.dto;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.*;

public class StatisticsDTO {

    //region Properties
    private List<ContainerStatisticsDTO> drinkStatisticsBarrel = new ArrayList<>();

    private List<TimeStatisticsDTO> drinkStatisticsTime = new ArrayList<>();
    //endregion

    //region Getter and Setter
    public List<ContainerStatisticsDTO> getDrinkStatisticsBarrel() {
        return drinkStatisticsBarrel;
    }

    public void setDrinkStatisticsBarrel(List<ContainerStatisticsDTO> drinkStatisticsBarrel) {
        this.drinkStatisticsBarrel = drinkStatisticsBarrel;
    }

    public List<TimeStatisticsDTO> getDrinkStatisticsTime() {
        return drinkStatisticsTime;
    }

    public void setDrinkStatisticsTime(List<TimeStatisticsDTO> drinkStatisticsTime) {
        this.drinkStatisticsTime = drinkStatisticsTime;
    }
    //endregion
}


