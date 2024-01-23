package com.example.liquidbits_springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.mapping.Map;

import java.time.LocalDate;
import java.util.List;


public class TimeStatisticsDTO {

    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    private List<Double> daily;
    private List<Double> monthly;
    private List<Double> annually;


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
    //endregion


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
