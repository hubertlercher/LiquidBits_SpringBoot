package com.example.liquidbits_springboot.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

@Entity
@Table(name = "DRINKTYPE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DrinkType implements Serializable {

    //region Properties
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DRINKTYPE_ID")
    private int drinkTypeId;
    @JsonIgnore
    @Column(name = "NAME")
    private String name;
    @JsonIgnore
    @Column(name = "ALCVALUE")
    private Integer alcvalue;
    @JsonIgnore
    @Column(name = "INTENSITY")
    private Integer intensity;
    @JsonIgnore
    @OneToMany(mappedBy = "drinkType",
            cascade = CascadeType.MERGE,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Collection<Container> containers;
    @JsonIgnore
    @OneToMany(mappedBy = "drinkType",
            cascade = CascadeType.MERGE,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Collection<Drink> drinks;

    @JsonIgnore
    @Column(name = "DRINKSIZE_S")
    private int drinkSizeS;

    @JsonIgnore
    @Column(name = "DRINKSIZE_L")
    private int drinkSizeL;
    //endregion

    //region Constructor

    public DrinkType() {

    }

    public DrinkType(int drinkTypeId) {
        this.drinkTypeId = drinkTypeId;
    }

    //endregion Constructor

    //region Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAlcvalue() {
        return alcvalue;
    }

    public void setAlcvalue(Integer alcvalue) {
        this.alcvalue = alcvalue;
    }

    public Collection<Container> getContainers() {
        return containers;
    }

    public void setContainers(Collection<Container> containers) {
        this.containers = containers;
    }

    public Collection<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(Collection<Drink> drinks) {
        this.drinks = drinks;
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
    //endregion


    //region hashCode and equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrinkType drinkType = (DrinkType) o;
        return Objects.equals(name, drinkType.name) && Objects.equals(alcvalue, drinkType.alcvalue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, alcvalue);
    }
    //endregion

    //region Methods

    @JsonAnyGetter
    public java.util.Map<String, Object> Stats() {

        int dailyDispensed = this.getDrinks().stream()
                .filter(drink -> drink.getTimestamp().equals(LocalDate.now()))
                .mapToInt(d -> d.getAmount())
                .sum();

        int monthlyDispensed = this.getDrinks().stream()
                .filter(drink -> drink.getTimestamp().getMonth() == LocalDate.now().getMonthValue())
                .mapToInt(d -> d.getAmount())
                .sum();

        int yearlyDispensed = this.getDrinks().stream()
                .filter(drink -> drink.getTimestamp().getYear() == LocalDate.now().getYear())
                .mapToInt(d -> d.getAmount())
                .sum();

        java.util.Map<String, Object> properties = new HashMap<>();
        properties.put("today", dailyDispensed);
        properties.put("monthly", monthlyDispensed);
        properties.put("yearly", yearlyDispensed);

        return properties;
    }

    //endregion Methods



}
