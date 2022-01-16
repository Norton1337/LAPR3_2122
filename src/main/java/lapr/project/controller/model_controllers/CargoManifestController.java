package lapr.project.controller.model_controllers;

import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.cargoManifest.idb.ICargoManifest;
import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;
import lapr.project.model.vehicle.Vehicles;
import lapr.project.model.vehicle.idb.IVehicle;

import java.util.List;

public class CargoManifestController {

    private final IVehicle vehicleDB;
    private final ICargoManifest cargoManifestDB;
    public CargoManifestController(IVehicle vehicleDB, ICargoManifest cargoManifestDB) {
        this.vehicleDB = vehicleDB;
        this.cargoManifestDB = cargoManifestDB;
    }

    public boolean addCargoManifest(CargoManifest newCargoManifest, String vehicleID) {
        for (Vehicles elems : vehicleDB.getAllVehicles()) {
            if (elems.getVehicle_recon().equals(vehicleID)) {
                newCargoManifest.setVehicleId(elems.getId());
            }
        }

        cargoManifestDB.addCargoManifest(newCargoManifest);
        return true;
    }

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
