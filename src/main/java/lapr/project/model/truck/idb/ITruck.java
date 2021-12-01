package lapr.project.model.truck.idb;

import lapr.project.model.ships.Ship;
import lapr.project.model.truck.Truck;


public interface ITruck {

    Truck getTruck(String id);

    boolean addTruck(Truck truck, String id);

    boolean removeTruck(String id);

    Truck getTruckByPlate(String plate);
}
