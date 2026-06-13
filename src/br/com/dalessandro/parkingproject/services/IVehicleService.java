package br.com.dalessandro.parkingproject.services;

import br.com.dalessandro.parkingproject.models.Vehicle;

public interface IVehicleService extends IService<Vehicle>{
    public double getPaymentValue(String key);
}
