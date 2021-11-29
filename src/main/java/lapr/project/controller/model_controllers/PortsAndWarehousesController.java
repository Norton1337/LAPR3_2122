package lapr.project.controller.model_controllers;

import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;
import lapr.project.model.ships.idb.IShipsDB;

import java.util.LinkedList;

public class PortsAndWarehousesController {

    private final IShipsDB shipDB;
    private final ILocals portsAndWarehousesDB;

    public PortsAndWarehousesController(IShipsDB shipDB, ILocals portsAndWarehousesDB) {
        this.shipDB = shipDB;
        this.portsAndWarehousesDB = portsAndWarehousesDB;
    }

    public boolean addPortAndWarehouse(Locals ports) {
        portsAndWarehousesDB.addPortsAndWarehouses(ports);
        return true;
    }

    public LinkedList<Locals> getAllPortsAndWharehouse(){
        return new LinkedList<>(portsAndWarehousesDB.getAllPortsAndWarehouses());
    }

}
