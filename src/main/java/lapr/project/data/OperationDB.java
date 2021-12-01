package lapr.project.data;

import lapr.project.data.db_scripts.DataHandler;
import lapr.project.model.operation.Operation;
import lapr.project.model.operation.idb.IOperation;

import java.util.List;

public class OperationDB  extends DataHandler implements IOperation{

    @Override
    public List<Operation> allOperations() {
        return null;
    }

    @Override
    public Operation getOperation(String id) {
        return null;
    }

    @Override
    public Operation addOperation(Operation operation, String containerId, String cargoManifestId) {
        return null;
    }

    @Override
    public boolean removeOperation(String id) {
        return false;
    }
}
