package lapr.project.data;

import lapr.project.model.ShipPositionData.IDB.IShipPositionDataDB;
import lapr.project.model.ShipPositionData.ShipPositonData;

import java.util.List;

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
    public Integer getAvailableShipPositionId() {
        return null;
    }

    @Override
    public List<ShipPositonData> getAllPositionDataFromShip(int shipID) {
        return null;
    }
    // TODO implement DB

}
