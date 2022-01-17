package lapr.project.controller.model_controllers;

import lapr.project.model.containers.Container;
import lapr.project.model.containers.idb.IContainerDB;
import lapr.project.model.truck.Truck;
import lapr.project.model.truck.idb.ITruck;

import java.util.List;

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
