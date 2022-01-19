package lapr.project.data.mocks;

import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.cargoManifest.idb.ICargoManifest;
import lapr.project.utils.Utils;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.randomUUID;
import static lapr.project.utils.Utils.toDate;

public class CargoManifestDBMock implements ICargoManifest {

    private final List<CargoManifest> cargoManifests = new LinkedList<>();

    @Override
    public List<CargoManifest> getAllCargoManifest() {
        return new LinkedList<>(cargoManifests);
    }

    @Override
    public CargoManifest getCargoManifest(String id) {
        for (CargoManifest elems : cargoManifests) {
            if (elems.getId().equals(id)) {
                return elems;
            }
        }

        return null;
    }

    @Override
    public CargoManifest addCargoManifest(CargoManifest cargo) {
        cargo.setId(randomUUID());
        cargoManifests.add(cargo);
        return cargo;
    }

    @Override
    public CargoManifest getCargoManifestByRecon(String recon) {
        for (CargoManifest elems : cargoManifests) {
            if (elems.getCargo_recon().equals(recon)) {
                return elems;
            }
        }

        return null;
    }

    @Override
    public CargoManifest getCargoManifestBeforeDate(String date, String operationType, String portID) {
        CargoManifest cargoManifest = null;

        for(CargoManifest elem : Utils.cargosOrderedByTime(cargoManifests)){
            if(elem.getNextLocal().equals(portID)){
                if(elem.getOperationType().equals(operationType)){
                    if(toDate(elem.getDate()).compareTo(Date.valueOf(date)) < 0){
                        cargoManifest = elem;
                    }
                }
            }
        }
        return cargoManifest;
    }

    @Override
    public boolean removeCargoManifest(String id) {
        CargoManifest cargoToBeDeleted = null;
        for (CargoManifest elems : cargoManifests) {
            if (elems.getId().equals(id)) {
                cargoToBeDeleted = elems;
            }
        }

        return cargoManifests.remove(cargoToBeDeleted);
    }
}
