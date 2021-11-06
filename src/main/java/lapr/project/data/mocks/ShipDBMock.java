package lapr.project.data.mocks;

import lapr.project.model.Ships.IDB.IShipsDB;
import lapr.project.model.Ships.Ship;

import java.util.LinkedList;
import java.util.List;

public class ShipDBMock implements IShipsDB {

    private List<Ship> ships = new LinkedList<>();

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


    public Ship findShipById(String id){
        for(Ship elems : ships){
            if(elems.getId() == Integer.parseInt(id)){
                return  elems;
            }
        }
        return null;
    }
}
