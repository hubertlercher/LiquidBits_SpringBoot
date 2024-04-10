package com.example.liquidbits_springboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
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
    @NotNull
    private int logId;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DRINKTYPE_ID")
    @NotNull
    @JsonIgnoreProperties({"name", "alcvalue", "intensity", "drinkSizeS", "drinkSizeL", "lastMaintenance", "lastCleaning"})
    private DrinkType drinkType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEVICE_ID")
    @JsonIgnoreProperties({"location", "manufacturer", "modell"})
    @NotNull
    private Device device;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTACT_ID")
    @JsonIgnoreProperties({"contactId", "mail", "telephone"})
    @NotNull
    private Contact contact;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    @Column(name = "TIMESTAMP")
    @NotNull
    private LocalDateTime timestamp;


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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
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
                ", contact=" + contact +
                ", timestamp=" + timestamp +
                '}';
    }

    //endregion



}
