package br.com.dalessandro.parkingproject.routes;

import br.com.dalessandro.parkingproject.controllers.VehicleController;
import br.com.dalessandro.parkingproject.services.IVehicleService;
import com.sun.net.httpserver.HttpServer;

public class VehiclesRouter {
    public static void configureRoutes(HttpServer server, IVehicleService vehicleService) {
        VehicleController vehicleController = new VehicleController(vehicleService);

        server.createContext("/api/vehicles", vehicleController);
    }
}
