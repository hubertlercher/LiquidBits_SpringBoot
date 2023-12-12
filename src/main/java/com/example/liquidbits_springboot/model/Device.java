package com.example.liquidbits_springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "DEVICE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Device implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DEVICE_ID")
    private int deviceId;
    @JsonIgnore
    @Column(name = "LOCATION")
    private String location;
    @JsonIgnore
    @Column(name = "MANUFACTURER")
    private String manufacturer;
    @JsonIgnore
    @Column(name = "MODELL")
    private String modell;
    @JsonIgnore
    @OneToMany(mappedBy = "device",
            cascade = CascadeType.MERGE,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<Drink> drinks = new HashSet<>();

    //region Constructors

    public Device() {
    }

    public Device(int deviceId) {
        this.deviceId = deviceId;
    }

    //endregion




    //region Getter and Setter


    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public Collection<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(Set<Drink> drinks) {
        this.drinks = drinks;
    }

    //endregion

    //region haschCode equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return deviceId == device.deviceId && Objects.equals(location, device.location) && Objects.equals(manufacturer, device.manufacturer) && Objects.equals(modell, device.modell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, location, manufacturer, modell);
    }

    //endregion
}

