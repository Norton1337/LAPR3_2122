package lapr.project.controller.model_controllers;

import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.cargoManifest.idb.ICargoManifest;
import lapr.project.model.containers.Container;
import lapr.project.model.containers.idb.IContainerDB;
import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;
import lapr.project.model.operation.Operation;
import lapr.project.model.operation.idb.IOperation;

import java.util.List;

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
        if(operation == null || containerNumber == null || cargoManifestRecon == null || warehouseCode == null){
            return false;
        }

        for (Locals elems : localsDB.getAllLocals()) {
            if (elems.getLocalCode() == toInt(cargoManifestRecon)) {
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

    public List<Operation> getAllOperations() {
        return operationDB.allOperations();
    }

    public Operation findById(String id) {
        return operationDB.getOperation(id);
    }

}
