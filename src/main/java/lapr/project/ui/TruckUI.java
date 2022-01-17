package lapr.project.ui;

import lapr.project.controller.model_controllers.VehiclesController;
import lapr.project.model.truck.Truck;
import lapr.project.model.vehicle.Vehicles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TruckUI {

    private final VehiclesController vehiclesController;

    public TruckUI(VehiclesController vehiclesController) {
        this.vehiclesController = vehiclesController;
    }


    public void importTrucks(String filePath) {

        Truck newTruck = null;


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {


                List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));


                if (!list.get(0).contains("plate")) {


                    newTruck = new Truck(list.get(0));
                    vehiclesController.addTruck(new Vehicles("Truck",newTruck.getPlate()), newTruck);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
