package lapr.project.data.mocks;

import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.cargoManifest.idb.ICargoManifest;

import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.randomUUID;

public class CargoManifestDBMock implements ICargoManifest {

    private final List<CargoManifest> cargoManifests = new LinkedList<>();

    @Override
    public List<CargoManifest> getAllCargoManifest() {
        return new LinkedList<>(cargoManifests);
    }

    @Override
    public CargoManifest getCargoManifest(String id) {
        for(CargoManifest elems : cargoManifests){
            if(elems.getId().equals(id)){
                return  elems;
            }
        }

        return null;
    }

    @Override
    public CargoManifest addCargoManifest(CargoManifest cargo, String localId, String vehicleId) {
        cargo.setId(randomUUID());
        cargo.setCurrentLocalId(localId);
        cargo.setVehicleId(vehicleId);
        cargoManifests.add(cargo);
        return cargo;
    }

    @Override
    public boolean removeCargoManifest(String id) {
        CargoManifest cargoToBeDeleted = null;
        for(CargoManifest elems : cargoManifests){
            if(elems.getId().equals(id)){
                cargoToBeDeleted = elems;
            }
        }

        return cargoManifests.remove(cargoToBeDeleted);
    }
}
