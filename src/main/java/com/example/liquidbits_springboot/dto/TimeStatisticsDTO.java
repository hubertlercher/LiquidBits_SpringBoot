package com.example.liquidbits_springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public class TimeStatisticsDTO {
    //region Properties
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    private List<Double> daily;
    private List<Double> monthly;
    private List<Double> annually;
    private Map<Character, Long> sizesDaily;
    private Map<Character, Long> sizesMonthly;
    private Map<Character, Long> sizesAnnually;

    //region Getter and Setter
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Double> getDaily() {
        return daily;
    }

    public void setDaily(List<Double> daily) {
        this.daily = daily;
    }

    public List<Double> getMonthly() {
        return monthly;
    }

    public void setMonthly(List<Double> monthly) {
        this.monthly = monthly;
    }

    public List<Double> getAnnually() {
        return annually;
    }

    public void setAnnually(List<Double> annually) {
        this.annually = annually;
    }

    public java.util.Map<Character, Long> getSizesDaily() {
        return sizesDaily;
    }

    public void setSizesDaily(java.util.Map<Character, Long> sizesDaily) {
        this.sizesDaily = sizesDaily;
    }

    public java.util.Map<Character, Long> getSizesMonthly() {
        return sizesMonthly;
    }

    public void setSizesMonthly(java.util.Map<Character, Long> sizesMonthly) {
        this.sizesMonthly = sizesMonthly;
    }

    public java.util.Map<Character, Long> getSizesAnnually() {
        return sizesAnnually;
    }

    public void setSizesAnnually(java.util.Map<Character, Long> sizesAnnually) {
        this.sizesAnnually = sizesAnnually;
    }




    //endregion


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
