package lapr.project.model.ships.idb;

import java.sql.SQLException;
import java.util.List;

import lapr.project.model.ships.Ship;

public interface IShipsDB {

    List<Ship> getAllShips();

    Ship getShip(String id);

    boolean addShip(Ship ship);

    boolean updateShip(Ship ship);

    boolean removeShip(String id);

    Ship getShipByMMSI(String mmsi);

    public List<String> containers_to_offload(String ship_id) throws SQLException;

    public List<String> a_cm(Integer ano) throws SQLException;

    public float capacity_rate_now(String ship_id) throws SQLException;

    public float capacity_rate(String ship_id, String cm_id) throws SQLException;
}
