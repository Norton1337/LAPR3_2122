package lapr.project.data;

import lapr.project.model.Ships.IDB.IShipsDB;
import lapr.project.model.Ships.Ship;

import java.util.List;

public class ShipDB extends DataHandler implements IShipsDB {
    // TODO implement DB

    @Override
    public List<Ship> getAllPharmacies() {
        return null;
    }

    @Override
    public Ship getPharmacy(String id) {
        return null;
    }

    @Override
    public boolean addPharmacy(Ship pharmacy) {
        return false;
    }

    @Override
    public boolean updatePharmacy(Ship pharmacy) {
        return false;
    }

    @Override
    public boolean removePharmacy(String id) {
        return false;
    }


}
