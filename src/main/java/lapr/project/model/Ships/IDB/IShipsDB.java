package lapr.project.model.Ships.IDB;

import lapr.project.model.Ships.Ship;
import java.util.List;

public interface IShipsDB {

    List<Ship> getAllShips();

    Ship getShip(String id);

    boolean addShip(Ship ship);

    boolean updateShip(Ship ship);

    boolean removeShip(String id);

    Ship getShipByMMSI(String mmsi);


}
