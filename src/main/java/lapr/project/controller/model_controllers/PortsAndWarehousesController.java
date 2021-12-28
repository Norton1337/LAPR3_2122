package lapr.project.controller.model_controllers;

import lapr.project.model.country.idb.ICountryDB;
import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;

import java.util.LinkedList;
import java.util.List;

public class PortsAndWarehousesController {

    private final ICountryDB countryDB;
    private final ILocals localDB;

    public PortsAndWarehousesController(ICountryDB countryDB, ILocals localDB) {
        this.countryDB = countryDB;
        this.localDB = localDB;
    }

    public void addPort(Locals ports) {
        String countryId = countryDB.getCountryIdByName(ports.getCountryId());
        ports.setCountryId(countryId);
        ports.setType("Port");

        localDB.addPortsAndWarehouses(ports);
    }


    public void addWarehouse(Locals ports) {
        String countryId = countryDB.getCountryIdByName(ports.getCountryId());
        ports.setCountryId(countryId);
        ports.setType("Warehouse");

        localDB.addPortsAndWarehouses(ports);
    }

    public void addCountry(Locals ports) {
        String countryId = countryDB.getCountryIdByName(ports.getCountryId());
        ports.setCountryId(countryId);
        ports.setType("Country");

        localDB.addPortsAndWarehouses(ports);
    }

    public LinkedList<Locals> getAllPortsAndWharehouse(){
        return new LinkedList<>(localDB.getAllPortsAndWarehouses());
    }


    public LinkedList<Locals> getAllPorts(){
        List<Locals> localsList = new LinkedList<>();
        for(Locals elems: localDB.getAllPortsAndWarehouses()){
            if(elems.getType().contains("Port")){
                localsList.add(elems);
            }
        }
        return new LinkedList<>(localsList);
    }

}
