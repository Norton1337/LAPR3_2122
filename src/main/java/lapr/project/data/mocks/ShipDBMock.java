package lapr.project.data.mocks;

import lapr.project.model.Ships.IDB.IShipsDB;
import lapr.project.model.Ships.Ship;

import java.util.ArrayList;
import java.util.Collections;
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
    public Integer getAvailableShipId() {
        List<Integer> idList = new ArrayList<>();
        List<Ship> allShipsList = getAllShips();

        if(allShipsList.isEmpty()){
            return 0;
        }

        for(Ship elems : allShipsList){
            idList.add(elems.getId());
        }

        return Collections.max(idList)+1;
    }

    @Override
    public boolean addShip(Ship ship) {
        int availableIdShip = getAvailableShipId();
        ship.setId(availableIdShip);
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
            if(elems.getId() == Integer.parseInt(id)){
                return  elems;
            }
        }
        return null;
    }
}
