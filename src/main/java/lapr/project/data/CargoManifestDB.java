package lapr.project.data;

import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.cargoManifest.idb.ICargoManifest;

import java.util.List;

public class CargoManifestDB implements ICargoManifest {

    @Override
    public List<CargoManifest> getAllCargoManifest() {
        return null;
    }

    @Override
    public CargoManifest getCargoManifest(String id) {
        return null;
    }

    @Override
    public CargoManifest addCargoManifest(CargoManifest cargo, String localId, String vehicleId) {
        return null;
    }

    @Override
    public boolean removeCargoManifest(String id) {
        return false;
    }
}
