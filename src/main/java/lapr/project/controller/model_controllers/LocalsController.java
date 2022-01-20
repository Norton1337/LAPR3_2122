package lapr.project.controller.model_controllers;

import lapr.project.model.country.idb.ICountryDB;
import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;

import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.toInt;

public class LocalsController {

    private final ICountryDB countryDB;
    private final ILocals localDB;

    public LocalsController(ICountryDB countryDB, ILocals localDB) {
        this.countryDB = countryDB;
        this.localDB = localDB;
    }

    public void addPort(Locals ports) {
        boolean flag = true;
        for (Locals elems : getAllPorts()) {
            if (elems.getName().equals(ports.getName())) {
                flag = false;
                break;
            }
        }

        if (flag) {
            String countryId = countryDB.getCountryIdByName(ports.getCountryId());
            ports.setCountryId(countryId);
            ports.setType("Port");

            localDB.addPortsAndWarehouses(ports);
        }
    }

    public Locals getLocalWithPortId(String portId) {
        Locals locals = null;

        for (Locals elems : getAllPorts()) {
            if (elems.getLocalCode() == Integer.parseInt(portId)) {
                locals = elems;
            }
        }
        return locals;
    }

    public Locals getLocalWithCapital(String capital) {
        Locals locals = null;

        for (Locals elems : getAllCapitals()) {
            if (elems.getName().equals(capital)) {
                locals = elems;
            }
        }
        return locals;
    }


    public Locals getLocalWithName(String capital) {
        Locals locals = null;

        for (Locals elems : getAllLocals()) {
            if (elems.getName().equals(capital)) {
                locals = elems;
            }
        }
        return locals;
    }

    public void addWarehouse(Locals warehouse, String port_code, String warehouse_capacity) {
        for (Locals elems : getAllPorts()) {
            if (elems.getLocalCode() == toInt(port_code)) {
                warehouse.setPortId(elems.getId());
            }
        }

        String countryId = countryDB.getCountryIdByName(warehouse.getCountryId());
        warehouse.setCountryId(countryId);
        warehouse.setType("Warehouse");
        warehouse.setLocalCapacity(toInt(warehouse_capacity));
        localDB.addPortsAndWarehouses(warehouse);
    }


    public LinkedList<Locals> getAllLocals() {
        return new LinkedList<>(localDB.getAllLocals());
    }


    public LinkedList<Locals> getAllPorts() {
        List<Locals> localsList = new LinkedList<>();
        for (Locals elems : localDB.getAllLocals()) {
            if (elems.getType().contains("Port")) {
                localsList.add(elems);
            }
        }
        return new LinkedList<>(localsList);
    }

    public LinkedList<Locals> getAllWarehouses() {
        List<Locals> localsList = new LinkedList<>();
        for (Locals elems : localDB.getAllLocals()) {
            if (elems.getType().contains("Warehouse")) {
                localsList.add(elems);
            }
        }
        return new LinkedList<>(localsList);
    }

    public LinkedList<Locals> getAllCapitals() {
        List<Locals> localsList = new LinkedList<>();
        for (Locals elems : localDB.getAllLocals()) {
            if (elems.getType().contains("Capital")) {
                localsList.add(elems);
            }
        }
        return new LinkedList<>(localsList);
    }

    public Locals getWarehouseByCode(String warehouse_Code){
        Locals locals = null;

        for (Locals elems : getAllLocals()) {
            if (elems.getLocalCode() == toInt(warehouse_Code)) {
                locals = elems;
            }
        }
        return locals;
    }

}
