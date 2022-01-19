package lapr.project.model.cargoManifest.idb;

import lapr.project.model.cargoManifest.CargoManifest;

import java.util.List;


public interface ICargoManifest {

    List<CargoManifest> getAllCargoManifest();

    CargoManifest getCargoManifest(String id);

    CargoManifest addCargoManifest(CargoManifest cargo);

    CargoManifest getCargoManifestByRecon(String recon);

    CargoManifest getCargoManifestBeforeDate(String date, String operationType, String portID);

    boolean removeCargoManifest(String id);

}
