package com.example.liquidbits_springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
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

    @Column(name = "NAME")
    private String name;

    @Column(name = "ALCVALUE")
    private Integer alcvalue;

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
}
