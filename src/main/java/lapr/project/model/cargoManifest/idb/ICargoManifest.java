package lapr.project.model.cargoManifest.idb;

import lapr.project.model.cargoManifest.CargoManifest;

import java.util.List;


public interface ICargoManifest {

    List<CargoManifest> getAllCargoManifest();

    CargoManifest getCargoManifest(String id);

    CargoManifest addCargoManifest(CargoManifest cargo, String localId, String vehicleId);

    boolean removeCargoManifest(String id);

}