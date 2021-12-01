package lapr.project.data;

import lapr.project.model.truck.Truck;
import lapr.project.model.truck.idb.ITruck;

public class TruckDB implements ITruck {


    @Override
    public Truck getTruck(String id) {
        return null;
    }

    @Override
    public boolean addTruck(Truck truck, String id) {
        return false;
    }


    @Override
    public boolean removeTruck(String id) {
        return false;
    }

    @Override
    public Truck getTruckByPlate(String plate) {
        return null;
    }
}
