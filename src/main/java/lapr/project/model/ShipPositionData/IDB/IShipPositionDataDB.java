package lapr.project.model.ShipPositionData.IDB;

import lapr.project.model.ShipPositionData.ShipPositonData;

import java.util.List;

public interface IShipPositionDataDB {

    List<ShipPositonData> getShipData();

    boolean addShipData(ShipPositonData shipPositonData);

    boolean removeDataFromShip(ShipPositonData shipPositonData);

    Integer getAvailableShipPositionId();

    List<ShipPositonData> getAllPositionDataFromShip(int shipID);

}
