package com.example.liquidbits_springboot.model;

import com.example.liquidbits_springboot.dto.ContainerStatisticsDTO;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "CONTAINER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Container implements Serializable {

    //region Properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTAINER_ID")
    private int containerId;


    @Column(name = "TAPPED")
    private Date tapped;


    @Column(name = "SIZE_ML")
    private int sizeMl;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DRINKTYPE_ID")
    @JsonIgnoreProperties({"name", "alcvalue", "intensity", "drinkSizeS", "drinkSizeL", "lastMaintenance", "lastCleaning"})
    private DrinkType drinkType;

    @JsonIgnore
    @OneToMany(mappedBy = "container",
            cascade = CascadeType.MERGE,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<Drink> drinks = new HashSet<>();


    @Column(name = "UNTAPPED")
    private LocalDateTime untapped;


    @Column(name = "STATUS")
    private String status;
    //endregion

    //region Constructor

    public Container() {
    }

    public Container(int containerId) {
        this.containerId = containerId;
    }

    //endregion


    //region equals and hashCode
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
    //endregion

    //region Getter and Setter

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

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(DrinkType drinkType) {
        this.drinkType = drinkType;
    }

    public Collection<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(Set<Drink> drinks) {
        this.drinks = drinks;
    }

    public LocalDateTime getUntapped() {
        return untapped;
    }

    public void setUntapped(LocalDateTime untapped) {
        this.untapped = untapped;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //endregion Getter and Setter

    //region Methods


    public static double calcBarrelLevel(Container container) {
        double level = 0;
        double dispensed;

        dispensed = container.getDrinks().stream()
                .mapToDouble(drink -> drink.getAmount())
                .sum();

        level = (container.getSizeMl() - dispensed);

        // toDo Prozent
        double percent = level / (container.getSizeMl() / 100);

        return percent;

    }

    public static String setStatusInDTO(Container container) {
        return container.status;
    }



    //endregion Methods


}
