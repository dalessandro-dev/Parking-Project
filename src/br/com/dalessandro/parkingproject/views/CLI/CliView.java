package br.com.dalessandro.parkingproject.views.CLI;

import br.com.dalessandro.parkingproject.dtos.VehicleRequestAndResponseDTO;
import br.com.dalessandro.parkingproject.views.CLI.api.ParkingApi;
import java.util.Scanner;

public class CliView {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("""
            __| Welcome to the Parking System |___

            Enter the number of the operation you wish to perform:

            1. Add vehicle
            2. Remove vehicle
            3. Get vehicle payment amount
            4. List vehicles
            5. Exit
            """);

            String option =  input.nextLine();

            switch (option) {
                case "1" -> {
                    System.out.println("Enter the vehicle license plate:");
                    String licensePlate = input.nextLine();

                    System.out.println("Enter the vehicle model:");
                    String model = input.nextLine();

                    System.out.println("""
                            Enter the number of th vehicle type:
                            
                            1. Motorcycle
                            2. Car
                            """);
                    String type = input.nextLine();

                    if (!type.equals("1") && !type.equals("2")) {
                        System.out.println("Invalid input");
                        continue;
                    }

                    type = type.equalsIgnoreCase("1") ? "MOTORCYCLE" : "CAR";

                    Integer engineDisplacement = null;

                    if (type.equalsIgnoreCase("MOTORCYCLE")) {
                        System.out.println("Enter the vehicle engine displacement:");
                        engineDisplacement = input.nextInt();
                        input.nextLine();
                    }

                    ParkingApi.saveVehicle(licensePlate, model, engineDisplacement, type);
                }
                case "2" -> {
                    System.out.println("Enter the vehicle plate license:");

                    String plateLicense = input.nextLine();

                    ParkingApi.deleteVehicle(plateLicense);
                }
                case "3" -> {
                    System.out.println("Enter the vehicle plate license:");

                    String plateLicense = input.nextLine();

                    var paymentValueDTO = ParkingApi.getPaymentValue(plateLicense);

                    if (paymentValueDTO != null) {
                        System.out.println(paymentValueDTO);
                    }
                }
                case "4" -> {
                    var vehicles = ParkingApi.getVehicles();

                        if (vehicles != null && vehicles.length > 0) {
                            for (VehicleRequestAndResponseDTO vehicle : vehicles) {
                                System.out.println(vehicle);
                            }
                        } else if (vehicles.length == 0) {
                            System.out.println("No vehicles added yet");
                        }
                }
                case "5" -> {
                    System.out.println("Exiting...");
                    isRunning = false;
                }
                default -> System.out.println("Invalid input. Try again.");
            }
        }
    }
}
