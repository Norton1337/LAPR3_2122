package lapr.project.data.mocks;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import lapr.project.model.ships.Ship;
import lapr.project.model.ships.idb.IShipsDB;

import static lapr.project.utils.Utils.randomUUID;

public class ShipDBMock implements IShipsDB {

    private final List<Ship> ships = new LinkedList<>();

    @Override
    public List<Ship> getAllShips() {
        return new LinkedList<>(ships);
    }

    @Override
    public Ship getShip(String id) {
        return findShipById(id);
    }


    @Override
    public boolean addShip(Ship ship) {
        return ships.add(ship);
    }

    @Override
    public boolean updateShip(Ship ship) {
        // TODO
        return false;
    }

    @Override
    public boolean removeShip(String id) {
        Ship shipToRemove = findShipById(id);
        ships.remove(shipToRemove);
        return false;
    }

    @Override
    public Ship getShipByMMSI(String mmsi) {
        for(Ship elems : getAllShips()){
            if(elems.getMMSI().equals(mmsi)){
                return elems;
            }
        }

        return null;
    }

    @Override
    public List<String> containers_to_offload(String ship_id){
        return null;
    }

    @Override
    public List<String> a_cm(Integer ano){
        return null;
    }

    @Override
    public float capacity_rate_now(String ship_id){
        return 0;
    }

    @Override
    public float capacity_rate(String ship_id, String cm_id) {
        return 0;
    }

    // TODO
    @Override
    public List<String> containers_to_load(String ship_id) {
        return null;
    }


    public Ship findShipById(String id){
        for(Ship elems : ships){
            if(elems.getId().equals(id)){
                return  elems;
            }
        }
        return null;
    }
}
