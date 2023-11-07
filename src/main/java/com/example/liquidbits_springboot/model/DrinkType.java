package com.example.liquidbits_springboot.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "DRINK-TYPE", schema = "liquidbits", catalog = "")
public class DrinkType {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DRINK-TYPE_ID")
    private int drinkTypeId;

    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "ALCVALUE")
    private Integer alcvalue;
    @OneToMany(mappedBy = "drinkTypeByDrinkTypeId")
    private Collection<Container> containersByDrinkTypeId;
    @OneToMany(mappedBy = "drinkTypeByDrinkTypeId")
    private Collection<Drink> drinksByDrinkTypeId;

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

    public Collection<Container> getContainersByDrinkTypeId() {
        return containersByDrinkTypeId;
    }

    public void setContainersByDrinkTypeId(Collection<Container> containersByDrinkTypeId) {
        this.containersByDrinkTypeId = containersByDrinkTypeId;
    }

    public Collection<Drink> getDrinksByDrinkTypeId() {
        return drinksByDrinkTypeId;
    }

    public void setDrinksByDrinkTypeId(Collection<Drink> drinksByDrinkTypeId) {
        this.drinksByDrinkTypeId = drinksByDrinkTypeId;
    }
}
