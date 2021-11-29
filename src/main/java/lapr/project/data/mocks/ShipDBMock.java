package lapr.project.data.mocks;

import lapr.project.model.Ships.IDB.IShipsDB;
import lapr.project.model.Ships.Ship;

import java.util.LinkedList;
import java.util.List;

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
        ship.setId(randomUUID());
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


    public Ship findShipById(String id){
        for(Ship elems : ships){
            if(elems.getId().equals(id)){
                return  elems;
            }
        }
        return null;
    }
}
