package lapr.project.data;

import lapr.project.model.ShipPositionData.IDB.IShipPositionDataDB;
import lapr.project.model.ShipPositionData.ShipPositonData;

import java.util.List;

public class ShipPositionDataDB implements IShipPositionDataDB {
    // TODO implement DB

    @Override
    public List<ShipPositonData> getShipData() {
        return null;
    }

    @Override
    public boolean addShipData(ShipPositonData shipPositonData) {
        return false;
    }
}
