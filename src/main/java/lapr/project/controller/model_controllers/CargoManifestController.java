package lapr.project.controller.model_controllers;

import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.cargoManifest.idb.ICargoManifest;
import lapr.project.model.containers.Container;
import lapr.project.model.containers.idb.IContainerDB;

import java.util.List;

public class CargoManifestController {

    private final ICargoManifest cargoManifestDB;

    public CargoManifestController(ICargoManifest cargoManifestDB) {
        this.cargoManifestDB = cargoManifestDB;
    }

    /*public boolean addCargoManifest(CargoManifest newCargoManifest) {
        cargoManifestDB.addCargoManifest(newCargoManifest);
        return true;
    }*/

    public List<CargoManifest> getAllCargoManifest() {
        return cargoManifestDB.getAllCargoManifest();
    }


    public CargoManifest findCargoById(String id) {
        return cargoManifestDB.getCargoManifest(id);
    }

    public CargoManifest findCargoByRecon(String recon) {
        return cargoManifestDB.getCargoManifestByRecon(recon);
    }

}
