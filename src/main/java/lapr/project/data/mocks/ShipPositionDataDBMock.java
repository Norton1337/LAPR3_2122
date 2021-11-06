package lapr.project.data.mocks;

import lapr.project.model.ShipPositionData.IDB.IShipPositionDataDB;
import lapr.project.model.ShipPositionData.ShipPositonData;

import java.util.LinkedList;
import java.util.List;

public class ShipPositionDataDBMock implements IShipPositionDataDB {

    List<ShipPositonData> shipPositonData = new LinkedList<>();

    @Override
    public List<ShipPositonData> getShipData() {
        return new LinkedList<>(shipPositonData);
    }

    @Override
    public boolean addShipData(ShipPositonData shipData) {
        return shipPositonData.add(shipData);
    }


}
