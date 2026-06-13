package br.com.dalessandro.parkingproject.repositories;

import br.com.dalessandro.parkingproject.models.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalRepositoryVehicle implements IRepository<Vehicle> {
    private final Map<String, Vehicle> vehicles = new HashMap<>();

    @Override
    public List<Vehicle> getAll() {
        return new ArrayList<>(vehicles.values());
    }

    @Override
    public Vehicle getById(String key) {
        Vehicle vehicle = vehicles.get(key);

        return vehicle;
    }

    @Override
    public void save(Vehicle vehicle) {
        vehicles.put(vehicle.getLicensePlate(), vehicle);
    }

    @Override
    public void delete(String key) {
        vehicles.remove(key);
    }
}
