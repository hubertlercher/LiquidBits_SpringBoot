package com.example.liquidbits_springboot.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.mapping.Map;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Entity
@Table(name = "DRINK")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Drink implements Serializable {

    //region static properties

    //endregion

    //region Properties
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DRINK_ID")
    private int drinkId;

    @Column(name = "AMOUNT")
    private double amount;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DRINKTYPE_ID")
    @JsonProperty("drinktype")
    @JsonIgnoreProperties({"name", "alcvalue", "intensity", "drinkSizeS", "drinkSizeL", "lastMaintenance", "lastCleaning", "containers", "drinks"})
    private DrinkType drinkType;
    @NotNull
    @JsonProperty("container")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CONTAINER_ID")
    @JsonIgnoreProperties({"tapped", "sizeMl", "drinkTypeId", "untapped", "status", "drinkType"})
    private Container container;
    @NotNull
    @JsonProperty("device")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DEVICE_ID")
    @JsonIgnoreProperties({"location", "manufacturer", "modell"})
    private Device device;
    @NotNull
    @JsonProperty("user")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID")
    @JsonIgnoreProperties({"surname", "firstname", "age", "sex", "mail", "image"})
    private User user;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    @Column(name = "TIMESTAMP")
    private Timestamp timestamp;

    //endregion

    //region Constructor

    public Drink(double amount, DrinkType drinkType, Container container, Device device, User user, Timestamp timestamp) {
        this.amount = amount;
        this.drinkType = drinkType;
        this.container = container;
        this.device = device;
        this.user = user;
        this.timestamp = timestamp;
    }

    public Drink() {

    }


    //endregion Constructor



    //region Getter and Setter

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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

    public void setContainer(Container container) {
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
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

    @Override
    public String toString() {
        return "Drink{" +
                "drinkId=" + drinkId +
                ", amount=" + amount +
                ", drinkType=" + drinkType +
                ", container=" + container +
                ", device=" + device +
                ", user=" + user +
                ", timestamp=" + timestamp +
                '}';
    }

    //endregion

    //region Methods

    public char isDrinkSizeSOrL() {
        char size = 'Z';

        switch (drinkType.getDrinkTypeId()) {

            case 1, 3: if(this.getAmount() <= 499) {
                size = 'S';
            } else if (this.getAmount() >= 500) {
                size = 'L';
            } break;

            case 2: if(this.getAmount() <= 249) {
                size = 'S';
            } else if (this.getAmount() >= 250) {
                size = 'L';
            } break;
        }

        return size;
    }

    //endregion


}
