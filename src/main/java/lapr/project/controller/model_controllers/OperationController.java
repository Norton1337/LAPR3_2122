package lapr.project.controller.model_controllers;

import lapr.project.model.borders.Borders;
import lapr.project.model.country.Country;
import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;
import lapr.project.model.operation.Operation;
import lapr.project.model.operation.idb.IOperation;

import java.util.LinkedList;
import java.util.List;

public class OperationController {

    private final IOperation operationDB;
    private final ILocals localsDB;

    public OperationController(IOperation operationDB,ILocals localsDB) {
        this.operationDB = operationDB;
        this.localsDB = localsDB;
    }

    public boolean addOperation(Operation operation) {

        return false;
    }

    public List<Operation> getAllOperations() {
        return operationDB.allOperations();
    }

    public Operation findById(String id) {
        return operationDB.getOperation(id);
    }

}
