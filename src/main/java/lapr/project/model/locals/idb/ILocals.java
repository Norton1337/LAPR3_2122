
package lapr.project.model.locals.idb;


import lapr.project.model.locals.Locals;

import java.sql.SQLException;
import java.util.List;

public interface ILocals {

    List<Locals> getAllPortsAndWarehouses();

    List<Locals> getAllCapitals();

    Locals getLocalWithLocalName(String name);

    Locals getLocalWithCapital(String capital);

    Locals getPortsAndWarehousesById(String id);

    boolean addPortsAndWarehouses(Locals portsAndWarehouses);

    boolean updatePortsAndWarehouses(Locals portsAndWarehouses);

    boolean removePortsAndWarehouses(String id);

    Locals getPortByPortIdCode(String code);



    public List<String> freeships() throws SQLException;

}



