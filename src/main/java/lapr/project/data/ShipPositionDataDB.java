package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import lapr.project.data.db_scripts.DataHandler;
import lapr.project.model.ship_position_data.ShipPositonData;
import lapr.project.model.ship_position_data.idb.IShipPositionDataDB;

public class ShipPositionDataDB extends DataHandler implements IShipPositionDataDB {
    @Override
    public List<ShipPositonData> getShipData() {
        return null;
    }

    @Override
    public boolean addShipData(ShipPositonData shipPositonData) {
        try (CallableStatement result = getConnection().prepareCall("{call insertShip()}")) {
            String[] coordinates = shipPositonData.getCoordinates().split(",");
            result.setString(1,shipPositonData.getId());
            result.setString(2,shipPositonData.getShipId());
            result.setTimestamp(3, Timestamp.valueOf(shipPositonData.getBaseDateTime()));
            result.setFloat(4,Float.parseFloat(coordinates[0]));
            result.setFloat(5,Float.parseFloat(coordinates[1]));
            result.setFloat(6,(float)shipPositonData.getSog());
            result.setFloat(7,(float)shipPositonData.getCog());
            result.setFloat(8,(float)shipPositonData.getHeading());
            result.setFloat(9,Float.parseFloat(shipPositonData.getPositon()));
            result.setString(10,shipPositonData.getTranscieverClass());
            result.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
