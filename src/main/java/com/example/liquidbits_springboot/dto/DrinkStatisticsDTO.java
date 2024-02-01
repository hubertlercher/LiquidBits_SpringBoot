package com.example.liquidbits_springboot.dto;

import java.time.LocalDateTime;
import java.util.List;

public class DrinkStatisticsDTO {

    //region Properties
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime startOfWeek = now.withHour(0).withMinute(0).withSecond(0).withNano(0);
    LocalDateTime endOfWeek = startOfWeek.plusWeeks(1);
    private String name;
    private List<Double> servedWeekly;
    private List<Double> servedMonthly;
    private List<Double> servedYearly;
    //endregion Properties

    //region Getter and Setter

    public List<Double> getServedWeekly() {
        return servedWeekly;
    }

    public void setServedWeekly(List<Double> servedWeekly) {
        this.servedWeekly = servedWeekly;
    }

    public List<Double> getServedMonthly() {
        return servedMonthly;
    }

    public void setServedMonthly(List<Double> servedMonthly) {
        this.servedMonthly = servedMonthly;
    }

    public List<Double> getServedYearly() {
        return servedYearly;
    }

    public void setServedYearly(List<Double> servedYearly) {
        this.servedYearly = servedYearly;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion Getter and Setter



}
