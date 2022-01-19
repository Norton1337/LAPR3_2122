package lapr.project.controller.model_controllers;

import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.cargoManifest.idb.ICargoManifest;
import lapr.project.model.containers.Container;
import lapr.project.model.containers.idb.IContainerDB;
import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;
import lapr.project.model.operation.Operation;
import lapr.project.model.operation.idb.IOperation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lapr.project.utils.Utils.toDate;
import static lapr.project.utils.Utils.toInt;

public class OperationController {

    private final IOperation operationDB;
    private final ILocals localsDB;
    private final ICargoManifest cargoManifestDB;
    private final IContainerDB containerDB;


    public OperationController(IOperation operationDB, ILocals localsDB, ICargoManifest cargoManifestDB, IContainerDB containerDB) {
        this.operationDB = operationDB;
        this.localsDB = localsDB;
        this.cargoManifestDB = cargoManifestDB;
        this.containerDB = containerDB;
    }

    public boolean addOperation(Operation operation,String containerNumber,String cargoManifestRecon, String warehouseCode) {

        //TODO implement "trigger" in this forLoop
        for (Locals elems : localsDB.getAllLocals()) {
            if (elems.getLocalCode() == toInt(warehouseCode)) {
                if((elems.getLocalCapacity() + 1) > elems.getLocalCapacity()){
                    return false;
                }
            }
        }

        for (Locals elems : localsDB.getAllLocals()) {
            if (elems.getLocalCode() == toInt(warehouseCode)) {
                operation.setOperation_warehouse(elems.getId());
            }
        }
        for (Container elems : containerDB.getAllContainers()) {
            if (elems.getContainerNumber() == toInt(containerNumber)) {
                operation.setContainerId(elems.getId());
            }
        }
        for (CargoManifest elems : cargoManifestDB.getAllCargoManifest()) {
            if (elems.getCargo_recon().equals(cargoManifestRecon)) {
                operation.setCargoManifestId(elems.getId());
            }
        }
        operationDB.addOperation(operation);
        return true;
    }

    public Map<Locals,List<String>> getOccupancyRate_and_ContainersLeavingNextMonth(int port_code){
        Locals port = null;
        List<CargoManifest> unloadCargos = new ArrayList<>();
        List<CargoManifest> loadCargos = new ArrayList<>();
        List<String> containersLeavingAndOccupancyRate = new ArrayList<>();
        List<Locals> warehousesList = new ArrayList<>();
        Map<Locals,List<String>> map = new HashMap<>();
        for(Locals elem : localsDB.getAllLocals()){
            if(elem.getLocalCode() == port_code){
                port = elem;
            }
        }
        for(Locals elem : localsDB.getAllLocals()){
            if(elem.getPortId().equals(port.getId())){
                warehousesList.add(elem);
            }
        }

        for(CargoManifest elem : cargoManifestDB.getAllCargoManifest()){
            if(elem.getCurrentLocalId().equals(port.getId())){
                if(toDate(elem.getDate()).compareTo(toDate(LocalDateTime.now().toString())) < 0){
                    if(elem.getOperationType().equals("Unload")){
                        unloadCargos.add(elem);
                    }
                }
            }else if(elem.getCurrentLocalId().equals(port.getId())){
                if(toDate(elem.getDate()).compareTo(toDate(LocalDateTime.now().toString())) < 0){
                    if(elem.getOperationType().equals("Load")){
                        loadCargos.add(elem);
                    }
                }
            }
        }


        return null;
    }

    public List<Operation> getAllOperations() {
        return operationDB.allOperations();
    }

    public Operation findById(String id) {
        return operationDB.getOperation(id);
    }

}
