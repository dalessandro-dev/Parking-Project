package br.com.dalessandro.parkingproject.models;

import java.time.Duration;
import java.time.LocalDateTime;

abstract public class Vehicle {
    private String licensePlate;
    private LocalDateTime entryTime;
    private String typeVehicle;
    private String model;

    public Vehicle(String licensePlate, LocalDateTime entryTime, String typeVehicle, String model) {
        this.licensePlate = licensePlate;
        this.entryTime = entryTime;
        this.typeVehicle = typeVehicle;
        this.model = model;
    }

    public String getTypeVehicle() {
        return typeVehicle;
    }

    public void setTypeVehicle(String typeVehicle) {
        this.typeVehicle = typeVehicle;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    protected long calculateDuration(LocalDateTime departureTime) {
        return Duration.between(entryTime, departureTime).toHours();
    }

    public abstract double calculateValue(LocalDateTime departureTime);

    @Override
    public String toString() {
        return this.typeVehicle.substring(0, 1).toUpperCase() + this.typeVehicle.substring(1).toLowerCase() + " with license plate " + this.getModel() + ".";
    }
}
