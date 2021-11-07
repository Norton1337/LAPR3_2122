package lapr.project.data;

import lapr.project.data.DBScripts.DataHandler;
import lapr.project.model.Ships.IDB.IShipsDB;
import lapr.project.model.Ships.Ship;

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
    public boolean addShip(Ship pharmacy) {
        return false;
    }

    @Override
    public boolean updateShip(Ship pharmacy) {
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

    @Override
    public Integer getAvailableShipId() {
        return null;
    }




}
