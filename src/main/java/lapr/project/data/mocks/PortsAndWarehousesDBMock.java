package lapr.project.data.mocks;

import lapr.project.model.PortsAndWarehouses.IDB.IPortsAndWarehouses;
import lapr.project.model.PortsAndWarehouses.PortsAndWarehouses;
import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.randomUUID;

public class PortsAndWarehousesDBMock implements IPortsAndWarehouses {

    private final List<PortsAndWarehouses> portsAndWarehouses = new LinkedList<>();

    @Override
    public List<PortsAndWarehouses> getAllPortsAndWarehouses() {
        return new LinkedList<>(portsAndWarehouses);
    }

    @Override
    public PortsAndWarehouses getPortsAndWarehousesById(String id) {
        return null;
    }

    @Override
    public boolean addPortsAndWarehouses(PortsAndWarehouses portsAndWarehouses) {
        portsAndWarehouses.setId(randomUUID());
        return this.portsAndWarehouses.add(portsAndWarehouses);
    }

    @Override
    public boolean updatePortsAndWarehouses(PortsAndWarehouses portsAndWarehouses) {
        //Ship shipToRemove = findShipById(id);
        //portsAndWarehouses.remove(shipToRemove);
        return false;
    }

    @Override
    public boolean removePortsAndWarehouses(String id) {
        return false;
    }
}
