package lapr.project.model.ship_position_data.idb;

import lapr.project.model.ship_position_data.ShipPositonData;

import java.util.List;

public interface IShipPositionDataDB {

    List<ShipPositonData> getShipData();

    boolean addShipData(ShipPositonData shipPositonData);

    boolean removeDataFromShip(ShipPositonData shipPositonData);

    List<ShipPositonData> getAllPositionDataFromShip(int shipID);

}
