package lapr.project.data;

import lapr.project.data.db_scripts.DataHandler;
import lapr.project.model.ships.Ship;
import lapr.project.model.ships.idb.IShipsDB;

import java.util.List;

public class ShipDB extends DataHandler implements IShipsDB {

    // TODO implement DB


    @Override
    public List<Ship> getAllShips() {
        return null;
    }

    @Override
    public Ship getShip(String id) {
        return null;
    }
    
    
    
    @Override
    public boolean addShip(Ship ship) {
        return false;
    }

    @Override
    public boolean updateShip(Ship ship) {
        return false;
    }

    @Override
    public boolean removeShip(String id) {
        return false;
    }

    @Override
    public Ship getShipByMMSI(String id) {
        return null;
    }


}
