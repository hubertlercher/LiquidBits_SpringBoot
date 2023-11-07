package com.example.liquidbits_springboot.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Container {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CONTAINER_ID")
    private int containerId;
    @Basic
    @Column(name = "TAPPED")
    private Date tapped;
    @Basic
    @Column(name = "SIZE_ML")
    private int sizeMl;
    @ManyToOne
    @JoinColumn(name = "DRINK-TYPE_ID", referencedColumnName = "DRINK-TYPE_ID", nullable = false)
    private DrinkType drinkTypeByDrinkTypeId;
    @OneToMany(mappedBy = "containerByContainerId")
    private Collection<Drink> drinksByContainerId;

    public int getContainerId() {
        return containerId;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }

    public Date getTapped() {
        return tapped;
    }

    public void setTapped(Date tapped) {
        this.tapped = tapped;
    }

    public int getSizeMl() {
        return sizeMl;
    }

    public void setSizeMl(int sizeMl) {
        this.sizeMl = sizeMl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container container = (Container) o;
        return containerId == container.containerId && sizeMl == container.sizeMl && Objects.equals(tapped, container.tapped);
    }

    @Override
    public int hashCode() {
        return Objects.hash(containerId, tapped, sizeMl);
    }

    public DrinkType getDrinkTypeByDrinkTypeId() {
        return drinkTypeByDrinkTypeId;
    }

    public void setDrinkTypeByDrinkTypeId(DrinkType drinkTypeByDrinkTypeId) {
        this.drinkTypeByDrinkTypeId = drinkTypeByDrinkTypeId;
    }

    public Collection<Drink> getDrinksByContainerId() {
        return drinksByContainerId;
    }

    public void setDrinksByContainerId(Collection<Drink> drinksByContainerId) {
        this.drinksByContainerId = drinksByContainerId;
    }
}
