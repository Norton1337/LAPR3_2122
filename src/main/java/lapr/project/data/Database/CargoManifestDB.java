package lapr.project.data.Database;

import lapr.project.data.db_scripts.DataHandler;
import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.cargoManifest.idb.ICargoManifest;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class CargoManifestDB extends DataHandler implements ICargoManifest {

    @Override
    public List<CargoManifest> getAllCargoManifest() {
        return null;
    }

    @Override
    public CargoManifest getCargoManifest(String id) {
        return null;
    }

    @Override
    public CargoManifest addCargoManifest(CargoManifest cargo) {
        if (cargo == null) {
            return null;
        }
        try (CallableStatement result = getConnection().prepareCall("{call insertCargoManifest(?,?,?,?,?,?)}")) {
            result.setString(1, cargo.getCurrentLocalId());
            result.setString(2, cargo.getVehicleId());
            result.setString(3, cargo.getCurrentLocalId());
            result.setString(4, cargo.getNextLocal());
            result.setTimestamp(5, (Timestamp.valueOf(cargo.getDate())));
            result.setString(6, cargo.getOperationType());
            result.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return new CargoManifest(cargo.getNextLocal(), cargo.getDate(), cargo.getOperationType());

    }

    @Override
    public CargoManifest getCargoManifestByRecon(String recon) {
        return null;
    }

    @Override
    public CargoManifest getCargoManifestBeforeDate(String date, String operationType, String portID) {
        return null;
    }

    @Override
    public boolean removeCargoManifest(String id) {
        return false;
    }
}
