package lapr.project.data.Database;

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
    public Vehicles addVehicle(Vehicles vehicle) {
        return null;
    }


    @Override
    public boolean removeVehicle(String id) {
        return false;
    }

    @Override
    public List<Vehicles> getAllTrucks() {
        return null;
    }

    @Override
    public List<Vehicles> getAllShips() {
        return null;
    }


}