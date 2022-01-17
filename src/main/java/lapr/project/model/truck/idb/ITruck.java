package lapr.project.model.truck.idb;

import lapr.project.model.truck.Truck;

import java.util.List;


public interface ITruck {

    Truck getTruck(String id);

    List<Truck> getAllTrucks();

    boolean addTruck(Truck truck);

    boolean removeTruck(String id);

    Truck getTruckByPlate(String plate);
}
