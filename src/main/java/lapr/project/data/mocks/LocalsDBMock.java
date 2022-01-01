package lapr.project.data.mocks;

import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;

import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.randomUUID;

public class LocalsDBMock implements ILocals {

    private final List<Locals> portsAndWarehouses = new LinkedList<>();

    @Override
    public List<Locals> getAllLocals() {
        return new LinkedList<>(portsAndWarehouses);
    }



    @Override
    public boolean addPortsAndWarehouses(Locals portsAndWarehouses) {
        portsAndWarehouses.setId(randomUUID());
        return this.portsAndWarehouses.add(portsAndWarehouses);
    }

    @Override
    public Locals getPortByPortIdCode(String code) {
        return null;
    }


    @Override
    public List<String> freeships(){
        return null;
    }



}
