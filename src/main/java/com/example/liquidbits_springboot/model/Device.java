package com.example.liquidbits_springboot.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Device {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DEVICE_ID")
    private int deviceId;
    @Basic
    @Column(name = "LOCATION")
    private String location;
    @Basic
    @Column(name = "MANUFACTURER")
    private String manufacturer;
    @Basic
    @Column(name = "MODELL")
    private String modell;
    @OneToMany(mappedBy = "deviceByDeviceId")
    private Collection<Drink> drinksByDeviceId;

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

    public Collection<Drink> getDrinksByDeviceId() {
        return drinksByDeviceId;
    }

    public void setDrinksByDeviceId(Collection<Drink> drinksByDeviceId) {
        this.drinksByDeviceId = drinksByDeviceId;
    }
}
