package com.example.liquidbits_springboot.dto;

import org.hibernate.mapping.Map;

import java.time.LocalDate;
import java.util.List;

public class TimeStatisticsDTO {

    private String name;
    private LocalDate date = LocalDate.now();
    private List<Integer> daily;
    private List<Integer> monthly;
    private List<Integer> annually;


    //region Getter and Setter
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Integer> getDaily() {
        return daily;
    }

    public void setDaily(List<Integer> daily) {
        this.daily = daily;
    }

    public List<Integer> getMonthly() {
        return monthly;
    }

    public void setMonthly(List<Integer> monthly) {
        this.monthly = monthly;
    }

    public List<Integer> getAnnually() {
        return annually;
    }

    public void setAnnually(List<Integer> annually) {
        this.annually = annually;
    }
    //endregion


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
