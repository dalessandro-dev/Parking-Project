package br.com.dalessandro.parkingproject.dtos;

public record PaymentValueResponseDTO(String idVehicle,
                                      Double totalToPay) {
    @Override
    public String toString() {
        return String.format(
                """
                License Plate: %s
                Payment Value: R$ %.2f
                """, idVehicle, totalToPay);
    }
}
