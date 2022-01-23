package lapr.project.data.database;

import lapr.project.data.db_scripts.DataHandler;
import lapr.project.model.truck.Truck;
import lapr.project.model.truck.idb.ITruck;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TruckDB extends DataHandler implements ITruck {


    @Override
    public Truck getTruck(String id) {
        return null;
    }

    @Override
    public List<Truck> getAllTrucks() {
        return new ArrayList<>();
    }

    @Override
    public boolean addTruck(Truck truck) {
        if (truck == null) {
            return false;
        }
        try (CallableStatement result = getConnection().prepareCall("{call insertTruck()}")) {
            result.setString(1, truck.getTruckId());
            result.setString(2, truck.getPlate());
            result.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public boolean removeTruck(String id) {
        return false;
    }

    @Override
    public Truck getTruckByPlate(String plate) {
        return null;
    }
}