package lapr.project.model.locals.idb;


import lapr.project.model.locals.Locals;

import java.util.List;

public interface ILocals {

    List<Locals> getAllLocals();

    boolean addPortsAndWarehouses(Locals portsAndWarehouses);

    Locals getPortByPortIdCode(String code);


    public List<String> freeships();

}



