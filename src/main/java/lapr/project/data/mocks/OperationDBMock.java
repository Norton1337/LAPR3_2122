package lapr.project.data.mocks;

import lapr.project.model.operation.Operation;
import lapr.project.model.operation.idb.IOperation;

import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.randomUUID;

public class OperationDBMock implements IOperation {

    private final List<Operation> operations = new LinkedList<>();


    @Override
    public List<Operation> allOperations() {
        return new LinkedList<>(operations);
    }

    @Override
    public Operation getOperation(String id) {
        for(Operation elems : operations){
            if(elems.getId().equals(id)){
                return elems;
            }
        }

        return null;
    }

    @Override
    public Operation addOperation(Operation operation, String containerId, String cargoManifestId) {
        operation.setId(randomUUID());
        operation.setContainerId(containerId);
        operation.setCargoManifestId(cargoManifestId);
        return null;
    }

    @Override
    public boolean removeOperation(String id) {
        Operation toBeDeleted = null;

        for(Operation elems : operations){
            if(elems.getId().equals(id)){
                toBeDeleted = elems;
            }
        }

        return operations.remove(toBeDeleted);
    }
}
