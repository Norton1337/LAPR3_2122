package lapr.project.data.database;

import lapr.project.model.vehicle.Vehicles;
import lapr.project.model.vehicle.idb.IVehicle;

import java.util.ArrayList;
import java.util.List;

public class VehiclesDB implements IVehicle {

    @Override
    public List<Vehicles> getAllVehicles() {
        return new ArrayList<>();
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
        return new ArrayList<>();
    }

    @Override
    public List<Vehicles> getAllShips() {
        return new ArrayList<>();
    }


}
