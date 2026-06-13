package br.com.dalessandro.parkingproject.models;

import java.time.LocalDateTime;

public class Car extends Vehicle {
    public Car(String licensePlate, LocalDateTime entryTime, String model) {
        super(licensePlate, entryTime, "Car", model);
    }

    public double calculateValue(LocalDateTime departureTime) {
        long durationInHours = this.calculateDuration(departureTime);

        return durationInHours * 10;
    }
}
