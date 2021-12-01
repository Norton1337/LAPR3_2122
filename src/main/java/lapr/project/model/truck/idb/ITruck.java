package lapr.project.model.truck.idb;

import lapr.project.model.truck.Truck;


public interface ITruck {

    Truck getTruck(String id);

    boolean addTruck(Truck ship, String id);

    boolean removeTruck(String id);
}
