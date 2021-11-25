package lapr.project.controller.ModelControllers;

import lapr.project.model.PortsAndWarehouses.IDB.IPortsAndWarehouses;
import lapr.project.model.PortsAndWarehouses.PortsAndWarehouses;
import lapr.project.model.Ships.IDB.IShipsDB;

import java.util.LinkedList;

public class PortsAndWarehousesController {

    private final IShipsDB shipDB;
    private final IPortsAndWarehouses portsAndWarehousesDB;

    public PortsAndWarehousesController(IShipsDB shipDB, IPortsAndWarehouses portsAndWarehousesDB) {
        this.shipDB = shipDB;
        this.portsAndWarehousesDB = portsAndWarehousesDB;
    }

    public boolean addPortAndWarehouse(PortsAndWarehouses ports) {
        portsAndWarehousesDB.addPortsAndWarehouses(ports);
        return true;
    }

    public LinkedList<PortsAndWarehouses> getAllPortsAndWharehouse(){
        return new LinkedList<>(portsAndWarehousesDB.getAllPortsAndWarehouses());
    }

}
