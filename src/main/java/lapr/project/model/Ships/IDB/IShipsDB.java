package lapr.project.model.Ships.IDB;

import lapr.project.model.Ships.Ship;
import java.util.List;

public interface IShipsDB {

    List<Ship> getAllPharmacies();

    Ship getPharmacy(String id);

    boolean addPharmacy(Ship pharmacy);

    boolean updatePharmacy(Ship pharmacy);

    boolean removePharmacy(String id);
}
