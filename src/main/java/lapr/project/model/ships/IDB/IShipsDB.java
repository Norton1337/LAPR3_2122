package lapr.project.model.ships.idb;

import java.util.List;

import lapr.project.model.ships.Ship;

public interface IShipsDB {

    List<Ship> getAllShips();

    Ship getShip(String id);

    boolean addShip(Ship ship);

    boolean updateShip(Ship ship);

    boolean removeShip(String id);

    Ship getShipByMMSI(String mmsi);


}
