package com.example.liquidbits_springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "DRINK")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Drink implements Serializable {

    //region Properties
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DRINK_ID")
    private int drinkId;

    @Column(name = "AMOUNT")
    private Integer amount;
    @JsonIgnoreProperties({"name", "alcvalue", "intensity"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DRINKTYPE_ID")
    private DrinkType drinkType;
    @JsonIgnoreProperties({"tapped", "sizeMl", "drinkType"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CONTAINER_ID")
    private Container container;
    @JsonIgnoreProperties({"location", "manufacturer", "modell"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DEVICE_ID")
    private Device device;
    @JsonIgnoreProperties({"username", "mail"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;

    //endregion

    //region Getter and Setter

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(DrinkType drinkType) {
        this.drinkType = drinkType;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container containerId) {
        this.container = container;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //endregion

    //region haschCode and equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return drinkId == drink.drinkId && Objects.equals(amount, drink.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drinkId, amount);
    }
    //endregion

    //region Methods




    //endregion
}
