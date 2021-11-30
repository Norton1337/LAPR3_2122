package lapr.project.data;

import lapr.project.model.vehicle.Vehicles;
import lapr.project.model.vehicle.idb.IVehicle;

import java.util.List;

public class VehiclesDB implements IVehicle {

    @Override
    public List<Vehicles> getAllVehicles() {
        return null;
    }

    @Override
    public Vehicles getVehicle(String id) {
        return null;
    }

    @Override
    public boolean addVehicle(Vehicles ship) {
        return false;
    }

    @Override
    public boolean removeVehicle(String id) {
        return false;
    }
}
