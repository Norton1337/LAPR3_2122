package lapr.project.model.vehicle.idb;

import lapr.project.model.ships.Ship;
import lapr.project.model.truck.Truck;
import lapr.project.model.vehicle.Vehicles;

import java.util.List;

public interface IVehicle {

    List<Vehicles> getAllVehicles();

    Vehicles getVehicle(String id);

    boolean addVehicle(Vehicles ship, String id);

    boolean removeVehicle(String id);

    List<Vehicles> getAllTrucks();

    List<Vehicles> getAllShips();
}
