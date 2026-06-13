import br.com.dalessandro.parkingproject.models.Vehicle;
import br.com.dalessandro.parkingproject.repositories.IRepository;
import br.com.dalessandro.parkingproject.repositories.LocalRepositoryVehicle;
import br.com.dalessandro.parkingproject.routes.VehiclesRouter;
import br.com.dalessandro.parkingproject.services.IVehicleService;
import br.com.dalessandro.parkingproject.services.VehicleService;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

void main() throws IOException {
    try {
        int port = 8080;

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        IRepository<Vehicle> repository = new LocalRepositoryVehicle();
        IVehicleService service = new VehicleService(repository);

        VehiclesRouter.configureRoutes(server, service);

        server.setExecutor(null);

        server.start();

        System.out.println("SERVER IS RUNNING IN PORT 8080");
    } catch (IOException e) {
        System.err.println("Error when starting server" + e.getMessage());
        e.printStackTrace();
    }
}
