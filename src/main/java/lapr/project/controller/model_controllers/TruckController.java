package lapr.project.controller.model_controllers;

import java.util.List;

import lapr.project.model.truck.Truck;
import lapr.project.model.truck.idb.ITruck;

public class TruckController {

    private final ITruck truckDB;

    public TruckController(ITruck truckDB) {
        this.truckDB = truckDB;
    }

    public boolean addTruck(Truck newTruck) {
        truckDB.addTruck(newTruck);
        return true;
    }

    public List<Truck> getAllTrucks() {
        return truckDB.getAllTrucks();
    }

    public Truck findTruckByPlate(String plate) {
        return truckDB.getTruckByPlate(plate);
    }
}
