package lapr.project.data;

import java.util.List;

import lapr.project.model.ship_position_data.ShipPositonData;
import lapr.project.model.ship_position_data.idb.IShipPositionDataDB;

public class ShipPositionDataDB implements IShipPositionDataDB {
    @Override
    public List<ShipPositonData> getShipData() {
        return null;
    }

    @Override
    public boolean addShipData(ShipPositonData shipPositonData) {
        return false;
    }

    @Override
    public boolean removeDataFromShip(ShipPositonData shipPositonData) {
        return false;
    }

    @Override
    public List<ShipPositonData> getAllPositionDataFromShip(int shipID) {
        return null;
    }
    // TODO implement DB

}
