package lapr.project.controller.ModelControllers;

import lapr.project.model.ShipPositionData.IDB.IShipPositionDataDB;
import lapr.project.model.ShipPositionData.ShipPositonData;
import lapr.project.model.Ships.IDB.IShipsDB;
import lapr.project.model.Ships.Ship;

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
