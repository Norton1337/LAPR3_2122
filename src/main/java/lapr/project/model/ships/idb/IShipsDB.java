package lapr.project.model.ships.idb;

import lapr.project.model.ships.Ship;

import java.util.List;

public interface IShipsDB {

    List<Ship> getAllShips();

    Ship getShip(String id);

    boolean addShip(Ship ship);

    boolean updateShip(Ship ship);

    boolean removeShip(String id);

    Ship getShipByMMSI(String mmsi);
}
