package lapr.project.data;

import lapr.project.data.db_scripts.DataHandler;
import lapr.project.data.mocks.OperationDBMock;
import lapr.project.model.operation.Operation;
import lapr.project.model.operation.idb.IOperation;

import java.sql.CallableStatement;
import java.sql.SQLException;
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
        try (CallableStatement result = getConnection().prepareCall("{call insertOperation(?,?,?,?,?,?,?)}")) {
              result.setString(1,operation.getId());
              result.setString(2,containerId);
              result.setString(3,cargoManifestId);
              result.setString(4,operation.getOperation_warehouse());
              result.setInt(5,operation.getX());
              result.setInt(6,operation.getY());
              result.setInt(7,operation.getZ());
              result.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return new Operation(operation.getX(), operation.getY(), operation.getZ());
    }

    @Override
    public boolean removeOperation(String id) {
        return false;
    }
}
