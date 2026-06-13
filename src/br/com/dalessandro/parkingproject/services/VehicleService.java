package br.com.dalessandro.parkingproject.services;

import br.com.dalessandro.parkingproject.models.Vehicle;
import br.com.dalessandro.parkingproject.repositories.IRepository;

import java.time.LocalDateTime;
import java.util.List;

public class VehicleService implements IVehicleService {
    private final IRepository<Vehicle> repository;

    public VehicleService(IRepository<Vehicle> repository) {
        this.repository = repository;
    }

    public List<Vehicle> getAll() {
        return this.repository.getAll();
    }

    public Vehicle getById(String key) {
        return this.repository.getById(key);
    }

    public void save(Vehicle vehicle) {
        if (this.repository.getById(vehicle.getLicensePlate()) != null) {
            throw new IllegalArgumentException("This vehicle already exists");
        }

        this.repository.save(vehicle);
    }

    public void delete(String key) {
        if (this.repository.getById(key) == null) {
            throw new IllegalArgumentException("This vehicle does not exist");
        }

        this.repository.delete(key);
    }

    public double getPaymentValue(String key) {
        Vehicle vehicle = this.repository.getById(key);

        if (vehicle == null) {
            throw new IllegalArgumentException("This vehicle does not exist");
        }

        return vehicle.calculateValue(LocalDateTime.now());
    }
}
