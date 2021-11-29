package lapr.project.controller.ModelControllers;

import lapr.project.model.Locals.Locals;
import lapr.project.model.Ships.IDB.IShipsDB;

import java.util.LinkedList;
import lapr.project.model.Locals.IDB.ILocals;

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
