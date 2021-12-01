package lapr.project.data;

import lapr.project.data.db_scripts.DataHandler;
import lapr.project.model.ships.Ship;
import lapr.project.model.ships.idb.IShipsDB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lapr.project.utils.Utils.*;

public class ShipDB extends DataHandler implements IShipsDB {

    // TODO implement DB
    private List<Ship> allShips = new ArrayList<>();

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
