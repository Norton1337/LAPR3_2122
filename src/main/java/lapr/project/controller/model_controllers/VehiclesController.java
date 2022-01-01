package lapr.project.controller.model_controllers;

import lapr.project.model.ships.Ship;
import lapr.project.model.ships.idb.IShipsDB;
import lapr.project.model.truck.idb.ITruck;
import lapr.project.model.vehicle.Vehicles;
import lapr.project.model.vehicle.idb.IVehicle;

import java.util.ArrayList;
import java.util.List;

public class VehiclesController {

    private final IVehicle vehicleDB;
    private final IShipsDB shipDB;
    private final ITruck truckDB;


    public VehiclesController(IVehicle vehicleDB, IShipsDB shipDB, ITruck truckDB) {
        this.vehicleDB = vehicleDB;
        this.shipDB = shipDB;
        this.truckDB = truckDB;
    }

    public boolean addShip(Vehicles vehicles, Ship newShip) {
        Vehicles elem = vehicleDB.addVehicle(vehicles);

        newShip.setId(elem.getId());
        return shipDB.addShip(newShip);
    }

    public List<Ship> getAllShips() {

        return new ArrayList<>(shipDB.getAllShips());
    }

/*
    public boolean addTruck(Truck truck) {
        truckDB.addTruck(truck);
        String truckAddedId = truckDB.

        return vehicleDB.addVehicle(vehicles, shipAddedId);
    }

 */


}
