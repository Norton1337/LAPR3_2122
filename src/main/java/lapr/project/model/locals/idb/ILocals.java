
package lapr.project.model.locals.idb;


import lapr.project.model.locals.Locals;

import java.util.List;

public interface ILocals {

    List<Locals> getAllPortsAndWarehouses();

    Locals getPortsAndWarehousesById(String id);

    boolean addPortsAndWarehouses(Locals portsAndWarehouses);

    boolean updatePortsAndWarehouses(Locals portsAndWarehouses);

    boolean removePortsAndWarehouses(String id);

    Locals getPortByPortIdCode(String code);

}



