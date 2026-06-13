package br.com.dalessandro.parkingproject.models;

import java.time.LocalDateTime;

public class Motorcycle extends Vehicle {
    private int engineDisplacement;

    public Motorcycle(String licensePlate, LocalDateTime entryTime, String model, int engineDisplacement) {
        super(licensePlate, entryTime, "Motorcycle", model);
        this.engineDisplacement = engineDisplacement;
    }

    public int getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(int engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    public double calculateValue(LocalDateTime departureTime) {
        double durationInHours = this.calculateDuration(departureTime);

        double tax = 0;

        if (engineDisplacement > 500) {
            tax = 2;
        }

        return durationInHours * 5 + tax;
    }
}
