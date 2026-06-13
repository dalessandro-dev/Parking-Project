package br.com.dalessandro.parkingproject.views.CLI.api;

import br.com.dalessandro.parkingproject.dtos.ErrorResponseDTO;
import br.com.dalessandro.parkingproject.dtos.PaymentValueResponseDTO;
import br.com.dalessandro.parkingproject.dtos.VehicleRequestAndResponseDTO;
import br.com.dalessandro.parkingproject.utils.JacksonJsonConversorUtils;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ParkingApi {
    private static final HttpClient client = HttpClient.newHttpClient();

    public static VehicleRequestAndResponseDTO[] getVehicles() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/vehicles"))
                    .GET()
                    .header("Accepted", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                ErrorResponseDTO error = JacksonJsonConversorUtils.convertJsonToObject(response.body(), ErrorResponseDTO.class);

                throw new RuntimeException(error.message());
            }

            return JacksonJsonConversorUtils.convertJsonToObject(response.body(), VehicleRequestAndResponseDTO[].class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void saveVehicle(String licensePlate,
                                   String model,
                                   Integer engineDisplacement,
                                   String typeVehicle) {
        try {
            var vehicleDTO = new VehicleRequestAndResponseDTO(licensePlate,
                    model,
                    engineDisplacement,
                    typeVehicle);

            String jsonBody = JacksonJsonConversorUtils.convertObjectToJson(vehicleDTO);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/vehicles"))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 201) {
                ErrorResponseDTO error = JacksonJsonConversorUtils.convertJsonToObject(response.body(), ErrorResponseDTO.class);

                throw new RuntimeException(error.message());
            }

            System.out.println("Vehicle saved successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteVehicle(String id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/vehicles/" + id))
                    .header("Accept", "application/json")
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 204) {
                ErrorResponseDTO error = JacksonJsonConversorUtils.convertJsonToObject(response.body(), ErrorResponseDTO.class);

                throw new RuntimeException(error.message());
            }

            System.out.println("Vehicle deleted successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static PaymentValueResponseDTO getPaymentValue(String id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/vehicles/" + id))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                ErrorResponseDTO error = JacksonJsonConversorUtils.convertJsonToObject(response.body(), ErrorResponseDTO.class);

                throw new RuntimeException(error.message());
            }

            return JacksonJsonConversorUtils.convertJsonToObject(response.body(), PaymentValueResponseDTO.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
