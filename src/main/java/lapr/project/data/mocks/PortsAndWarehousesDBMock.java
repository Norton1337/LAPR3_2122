package lapr.project.data.mocks;

import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;
import oracle.jdbc.OracleTypes;
import lapr.project.data.db_scripts.DataHandler;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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


    public boolean updatePortsAndWarehouses(Locals portsAndWarehouses) {
        //Ship shipToRemove = findShipById(id);
        //portsAndWarehouses.remove(shipToRemove);
        return false;
    }


    public boolean removePortsAndWarehouses(String id) {
        return false;
    }

    @Override
    public Locals getPortByPortIdCode(String code) {
        for(Locals elems : portsAndWarehouses){
            if(elems.getPortId() == Integer.parseInt(code)){
                return elems;
            }
        }

        return null;
    }

    @Override
    public List<String> freeships() throws SQLException {
        return null;
    }


    @Override
    public List<Locals> getAllCapitals() {
        List<Locals> capitalList = new LinkedList<>();
        for(Locals elems : portsAndWarehouses){
            if(elems.getType().equals("Capital")){
                capitalList.add(elems);
            }
        }

        return capitalList;
    }

    @Override
    public Locals getLocalWithLocalName(String name) {
        Locals locals = null;

        for(Locals elems : portsAndWarehouses){
            if(elems.getName().equals(name)){
                locals = elems;
            }
        }
        return locals;
    }

    @Override
    public Locals getLocalWithCapital(String capital) {
        Locals locals = null;

        for(Locals elems : getAllCapitals()){
            if(elems.getName().equals(capital)){
                locals = elems;
            }
        }
        return locals;
    }

}
