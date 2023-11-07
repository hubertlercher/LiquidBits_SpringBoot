package com.example.liquidbits_springboot.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Drink {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DRINK_ID")
    private int drinkId;
    @Basic
    @Column(name = "AMOUNT")
    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "DRINK-TYPE_ID", referencedColumnName = "DRINK-TYPE_ID", nullable = false)
    private DrinkType drinkTypeByDrinkTypeId;
    @ManyToOne
    @JoinColumn(name = "CONTAINER_ID", referencedColumnName = "CONTAINER_ID", nullable = false)
    private Container containerByContainerId;
    @ManyToOne
    @JoinColumn(name = "DEVICE_ID", referencedColumnName = "DEVICE_ID", nullable = false)
    private Device deviceByDeviceId;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    private User userByUserId;

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

    public DrinkType getDrinkTypeByDrinkTypeId() {
        return drinkTypeByDrinkTypeId;
    }

    public void setDrinkTypeByDrinkTypeId(DrinkType drinkTypeByDrinkTypeId) {
        this.drinkTypeByDrinkTypeId = drinkTypeByDrinkTypeId;
    }

    public Container getContainerByContainerId() {
        return containerByContainerId;
    }

    public void setContainerByContainerId(Container containerByContainerId) {
        this.containerByContainerId = containerByContainerId;
    }

    public Device getDeviceByDeviceId() {
        return deviceByDeviceId;
    }

    public void setDeviceByDeviceId(Device deviceByDeviceId) {
        this.deviceByDeviceId = deviceByDeviceId;
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
