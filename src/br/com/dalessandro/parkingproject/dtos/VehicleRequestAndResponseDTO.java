package br.com.dalessandro.parkingproject.dtos;

public record VehicleRequestAndResponseDTO(String licensePlate,
                                           String model,
                                           Integer engineDisplacement,
                                           String typeVehicle) {

    @Override
    public String toString() {
        if (typeVehicle.equalsIgnoreCase("motorcycle")) {
            return String.format("""
                MOTORCYCLE
                
                License Plate: %s
                Model: %s
                Engine Displacement: %d
                """, licensePlate, model, engineDisplacement);
        }

        return String.format("""
                %s
                
                License Plate: %s
                Model: %s
                """, typeVehicle, licensePlate, model);
    }
}
