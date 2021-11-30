package lapr.project.model.vehicle.idb;

import lapr.project.model.ships.Ship;
import lapr.project.model.vehicle.Vehicles;

import java.util.List;

public interface IVehicle {

    List<Vehicles> getAllVehicles();

    Vehicles getVehicle(String id);

    boolean addVehicle(Vehicles ship);

    boolean removeVehicle(String id);
}
