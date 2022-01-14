package lapr.project.model.cargoManifest.idb;

import lapr.project.model.cargoManifest.CargoManifest;

import java.util.List;


public interface ICargoManifest {

    List<CargoManifest> getAllCargoManifest();

    CargoManifest getCargoManifest(String id);

    CargoManifest addCargoManifest(CargoManifest cargo, String localId, String vehicleId);

    CargoManifest getCargoManifestByRecon(String recon);

    boolean removeCargoManifest(String id);

}
