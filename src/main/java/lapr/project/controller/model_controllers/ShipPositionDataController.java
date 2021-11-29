package lapr.project.controller.model_controllers;

import lapr.project.model.ship_position_data.ShipPositonData;
import lapr.project.model.ship_position_data.idb.IShipPositionDataDB;
import lapr.project.model.ships.Ship;
import lapr.project.model.ships.idb.IShipsDB;

import java.util.LinkedList;
import java.util.List;

public class ShipPositionDataController {

    private final IShipsDB shipDB;
    private final IShipPositionDataDB shipPositionDataDB;


    public ShipPositionDataController(IShipsDB shipDB, IShipPositionDataDB shipPositionDataDB) {
        this.shipDB = shipDB;
        this.shipPositionDataDB = shipPositionDataDB;
    }

    public boolean addDataToShip(Ship ship, ShipPositonData shipData){
        shipData.setShipId(ship.getId());
        shipPositionDataDB.addShipData(shipData);

        return true;
    }

    public boolean removeDataFromShip(ShipPositonData shipData){
        shipPositionDataDB.removeDataFromShip(shipData);
        return true;
    }

    public List<ShipPositonData> getShipData (){
        return new LinkedList<>(shipPositionDataDB.getShipData());
    }

}
