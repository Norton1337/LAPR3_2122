package lapr.project.controller.model_controllers;

import lapr.project.model.cargoManifest.idb.ICargoManifest;
import lapr.project.model.containers.idb.IContainerDB;
import lapr.project.model.locals.idb.ILocals;
import lapr.project.model.operation.Operation;
import lapr.project.model.operation.idb.IOperation;

import java.util.List;

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

        return false;
    }

    public List<Operation> getAllOperations() {
        return operationDB.allOperations();
    }

    public Operation findById(String id) {
        return operationDB.getOperation(id);
    }

}
