package br.com.dalessandro.parkingproject.controllers;

import br.com.dalessandro.parkingproject.dtos.PaymentValueResponseDTO;
import br.com.dalessandro.parkingproject.dtos.VehicleRequestAndResponseDTO;
import br.com.dalessandro.parkingproject.models.Car;
import br.com.dalessandro.parkingproject.models.Motorcycle;
import br.com.dalessandro.parkingproject.models.Vehicle;
import br.com.dalessandro.parkingproject.services.IVehicleService;
import br.com.dalessandro.parkingproject.utils.JacksonJsonConversorUtils;
import br.com.dalessandro.parkingproject.utils.ReadBodyUtils;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

public class VehicleController implements IController {
    private final IVehicleService service;

    public VehicleController(IVehicleService service) {
        this.service = service;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (conferExactUrlAndMethod(exchange, "GET", "/api/vehicles")) {
            this.getAll(exchange);
            return;
        }

        if (conferExactUrlAndMethod(exchange, "POST", "/api/vehicles")) {
            this.save(exchange);
            return;
        }

        if (conferUrlWithIdAndMethod(exchange, "GET", "/api/vehicles/")) {
            this.getPaymentValue(exchange);
            return;
        }

        if (conferUrlWithIdAndMethod(exchange, "DELETE", "/api/vehicles/")) {
            this.delete(exchange);
            return;
        }

        exchange.sendResponseHeaders(405, -1);
        exchange.close();
    }

    private void save(HttpExchange exchange) throws IOException {
        try {
            String jsonBody = ReadBodyUtils.readBody(exchange);
            VehicleRequestAndResponseDTO vehicleDTO = JacksonJsonConversorUtils.convertJsonToObject(jsonBody, VehicleRequestAndResponseDTO.class);

            Vehicle vehicle;
            LocalDateTime now = LocalDateTime.now();

            if ("CAR".equalsIgnoreCase(vehicleDTO.typeVehicle())) {
                vehicle = new Car(vehicleDTO.licensePlate(), now, vehicleDTO.model());
            } else {
                vehicle = new Motorcycle(vehicleDTO.licensePlate(), now, vehicleDTO.model(), vehicleDTO.engineDisplacement());
            }

            this.service.save(vehicle);

            exchange.sendResponseHeaders(201, -1);
        } catch (IllegalArgumentException e) {
            this.sendErrorResponse(exchange, e.getMessage(), 400);
        } catch (Exception e) {
            this.sendErrorResponse(exchange, e.getMessage(), 500);
        } finally {
            exchange.close();
        }
    }

    private void delete(HttpExchange exchange) throws IOException {
        try {
            String id = this.extractUrlId(exchange, "/api/vehicles/");

            this.service.delete(id);

            exchange.sendResponseHeaders(204, -1);
        } catch (IllegalArgumentException e) {
            this.sendErrorResponse(exchange, e.getMessage(), 400);
        } catch (Exception e) {
            this.sendErrorResponse(exchange, e.getMessage(), 500);
        } finally {
            exchange.close();
        }
    }

    private void getAll(HttpExchange exchange) throws IOException {
        try {
            List<Vehicle> vehicles = this.service.getAll();

            List<VehicleRequestAndResponseDTO> responseDTOs = vehicles.stream()
                    .map(v -> new VehicleRequestAndResponseDTO(
                            v.getLicensePlate(),
                            v.getModel(),
                            (v instanceof Motorcycle motorcycle) ? (Integer) motorcycle.getEngineDisplacement() : null,
                            v.getTypeVehicle()
                    )).toList();

            String jsonResponse = JacksonJsonConversorUtils.convertObjectToJson(responseDTOs);

            byte[] responseBytes = jsonResponse.getBytes(java.nio.charset.StandardCharsets.UTF_8);

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, responseBytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(responseBytes);
            }
        } catch (Exception e) {
            this.sendErrorResponse(exchange, e.getMessage(), 500);
        } finally {
            exchange.close();
        }
    }

    private void getPaymentValue(HttpExchange exchange) throws IOException {
        try {
            String id = this.extractUrlId(exchange, "/api/vehicles/");

            Double value = this.service.getPaymentValue(id);

            var paymentValueResponseDTO = new PaymentValueResponseDTO(id, value);

            String jsonResponse = JacksonJsonConversorUtils.convertObjectToJson(paymentValueResponseDTO);

            byte[] responseBytes = jsonResponse.getBytes(java.nio.charset.StandardCharsets.UTF_8);

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, responseBytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(responseBytes);
            }
        } catch (IllegalArgumentException e) {
            this.sendErrorResponse(exchange, e.getMessage(), 400);
        } catch (Exception e) {
            this.sendErrorResponse(exchange, e.getMessage(), 500);
        } finally {
            exchange.close();
        }
    }
}