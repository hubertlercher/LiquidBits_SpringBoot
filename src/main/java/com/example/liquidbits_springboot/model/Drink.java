package com.example.liquidbits_springboot.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.mapping.Map;

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
    private Integer amount;
    @JsonProperty("drinkType")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DRINKTYPE_ID")
    private DrinkType drinkType;
    @JsonProperty("container")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTAINER_ID")
    private Container container;
    @JsonProperty("device")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DEVICE_ID")
    private Device device;
    @JsonProperty("user")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;
    @Column(name = "TIMESTAMP")
    private Timestamp timestamp;

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

    //endregion
}
