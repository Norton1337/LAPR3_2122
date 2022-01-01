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

    // --------------------------------------------------------

    public List<String> containers_to_offload(String ship_id);

    public List<String> a_cm(Integer ano);

    public float capacity_rate_now(String ship_id);

    public float capacity_rate(String ship_id, String cm_id);

    public List<String> containers_to_load(String ship_id);
}
