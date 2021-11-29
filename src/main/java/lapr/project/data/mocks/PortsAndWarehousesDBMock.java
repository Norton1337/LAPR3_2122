package lapr.project.data.mocks;

import java.util.LinkedList;
import java.util.List;

import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;

import static lapr.project.utils.Utils.randomUUID;

public class PortsAndWarehousesDBMock implements ILocals {

    private final List<Locals> portsAndWarehouses = new LinkedList<>();

    @Override
    public List<Locals> getAllPortsAndWarehouses() {
        return new LinkedList<>(portsAndWarehouses);
    }

    @Override
    public Locals getPortsAndWarehousesById(String id) {
        return null;
    }

    @Override
    public boolean addPortsAndWarehouses(Locals portsAndWarehouses) {
        portsAndWarehouses.setId(randomUUID());
        return this.portsAndWarehouses.add(portsAndWarehouses);
    }

    @Override
    public boolean updatePortsAndWarehouses(Locals portsAndWarehouses) {
        //Ship shipToRemove = findShipById(id);
        //portsAndWarehouses.remove(shipToRemove);
        return false;
    }

    @Override
    public boolean removePortsAndWarehouses(String id) {
        return false;
    }
}
