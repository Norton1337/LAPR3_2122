package lapr.project.controller.model_controllers;

import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;
import lapr.project.model.ships.idb.IShipsDB;

import java.util.LinkedList;

public class PortsAndWarehousesController {

    private final IShipsDB shipDB;
    private final ILocals localDB;

    public PortsAndWarehousesController(IShipsDB shipDB, ILocals localDB) {
        this.shipDB = shipDB;
        this.localDB = localDB;
    }

    public boolean addPortAndWarehouse(Locals ports) {
        localDB.addPortsAndWarehouses(ports);
        return true;
    }

    public LinkedList<Locals> getAllPortsAndWharehouse(){
        return new LinkedList<>(localDB.getAllPortsAndWarehouses());
    }

}
