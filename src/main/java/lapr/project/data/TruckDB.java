package lapr.project.data;

import lapr.project.model.truck.idb.ITruck;

public class TruckDB implements ITruck {

    @Override
    public lapr.project.model.truck.Truck getTruck(String id) {
        return null;
    }

    @Override
    public boolean addTruck(lapr.project.model.truck.Truck ship, String id) {
        return false;
    }

    @Override
    public boolean removeTruck(String id) {
        return false;
    }
}
