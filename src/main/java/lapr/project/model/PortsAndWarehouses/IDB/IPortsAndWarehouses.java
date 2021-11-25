package lapr.project.model.PortsAndWarehouses.IDB;

import lapr.project.model.PortsAndWarehouses.PortsAndWarehouses;
import lapr.project.model.Ships.Ship;

import java.util.List;

public interface IPortsAndWarehouses {

    List<PortsAndWarehouses> getAllPortsAndWarehouses();

    PortsAndWarehouses getPortsAndWarehousesById(String id);

    boolean addPortsAndWarehouses(PortsAndWarehouses portsAndWarehouses);

    boolean updatePortsAndWarehouses(PortsAndWarehouses portsAndWarehouses);

    boolean removePortsAndWarehouses(String id);

}
