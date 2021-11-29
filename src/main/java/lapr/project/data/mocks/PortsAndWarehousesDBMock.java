package lapr.project.data.mocks;

import lapr.project.model.Locals.Locals;
import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.randomUUID;
import lapr.project.model.Locals.IDB.ILocals;

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
