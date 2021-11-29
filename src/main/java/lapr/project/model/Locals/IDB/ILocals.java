package lapr.project.model.Locals.IDB;

import lapr.project.model.Locals.Locals;
import lapr.project.model.Ships.Ship;

import java.util.List;

public interface ILocals {

    List<Locals> getAllPortsAndWarehouses();

    Locals getPortsAndWarehousesById(String id);

    boolean addPortsAndWarehouses(Locals portsAndWarehouses);

    boolean updatePortsAndWarehouses(Locals portsAndWarehouses);

    boolean removePortsAndWarehouses(String id);

}
