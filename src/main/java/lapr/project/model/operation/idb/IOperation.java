package lapr.project.model.operation.idb;

import lapr.project.model.operation.Operation;

import java.util.List;


public interface IOperation {

    List<Operation> allOperations();

    Operation getOperation(String id);

    Operation addOperation(Operation operation, String containerId, String cargoManifestId);

    boolean removeOperation(String id);

}
