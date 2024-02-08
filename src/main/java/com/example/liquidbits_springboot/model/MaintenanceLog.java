package com.example.liquidbits_springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "MAINTENANCE_LOG")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MaintenanceLog {

    //region Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOG_ID")
    private int logId;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DRINKTYPE_ID")
    @JsonIgnoreProperties({"name", "alcvalue", "intensity", "drinkSizeS", "drinkSizeL", "lastMaintenance", "lastCleaning"})
    private DrinkType drinkType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEVICE_ID")
    @JsonIgnoreProperties({"location", "manufacturer", "modell"})
    private Device device;
    //endregion

    //region Constructor
    public MaintenanceLog() {
    }

    public MaintenanceLog(int logId) {
        this.logId = logId;
    }

    //endregion

    //region Getter and Setter

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(DrinkType drinkType) {
        this.drinkType = drinkType;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
    //endregion

    //region equals hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaintenanceLog that = (MaintenanceLog) o;
        return logId == that.logId && Objects.equals(description, that.description) && Objects.equals(drinkType, that.drinkType) && Objects.equals(device, that.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, description, drinkType, device);
    }
    //endregion

    //region toString
    @Override
    public String toString() {
        return "MaintenanceLog{" +
                "logId=" + logId +
                ", description='" + description + '\'' +
                ", drinkType=" + drinkType +
                ", device=" + device +
                '}';
    }
    //endregion



}