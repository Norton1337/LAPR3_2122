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
    public Vehicles addVehicle(Vehicles vehicle) {
        if (vehicle == null) {
            return null;
        }
        if (vehicle.getType() == "Truck") {
        } else if (vehicle.getType() == "Ship") {

        }
        return new Vehicles(vehicle.getType());
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
