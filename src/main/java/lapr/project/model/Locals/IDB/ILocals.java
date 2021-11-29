package lapr.project.model.locals.idb;

import java.util.List;

import lapr.project.model.locals.Locals;

public interface ILocals {

    List<Locals> getAllPortsAndWarehouses();

    Locals getPortsAndWarehousesById(String id);

    boolean addPortsAndWarehouses(Locals portsAndWarehouses);

    boolean updatePortsAndWarehouses(Locals portsAndWarehouses);

    boolean removePortsAndWarehouses(String id);

}
