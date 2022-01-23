package lapr.project.data.database;

import lapr.project.data.db_scripts.DataHandler;
import lapr.project.model.ships.Ship;
import lapr.project.model.ships.idb.IShipsDB;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ShipDB extends DataHandler implements IShipsDB {

    // TODO implement DB
    private List<Ship> allShips = new ArrayList<>();

    @Override
    public List<Ship> getAllShips() {
        
        return new ArrayList<>();
    }

    @Override
    public Ship getShip(String id) {
        return null;
    }

    @Override
    public boolean addShip(Ship ship) {
        if (ship == null) {
            return false;
        }
        try (CallableStatement result = getConnection().prepareCall("{call insertShip(?,?,?,?,?,?,?,?,?,?,?,?)}")) {
            result.setString(1, ship.getId());
            result.setString(2, ship.getShipName());
            result.setString(3, ship.getMMSI());
            result.setString(4, ship.getIMO());
            result.setInt(5, ship.getGeneratorAmount());
            result.setFloat(6, ship.getGeneratorsPower());
            result.setString(7, ship.getCallSign());
            result.setInt(8, ship.getVesselType());
            result.setFloat(9, ship.getLength());
            result.setFloat(10, ship.getWidth());
            result.setFloat(11, (float) ship.getLoadCapacity());
            result.setFloat(12, (float) ship.getDraft());
            result.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateShip(Ship ship) {
        return false;
    }

    @Override
    public boolean removeShip(String id) {
        return false;
    }

    @Override
    public Ship getShipByMMSI(String id) {
        return null;
    }


    public List<String> containersToOffload(String shipId) {
        List<String> ls = new ArrayList<>();
        try (CallableStatement result = getConnection().prepareCall("{?= call containers_to_offload(?)}")) {
            result.registerOutParameter(1, OracleTypes.CURSOR);
            result.setString(2, shipId);
            result.executeUpdate();
            ResultSet containers = (ResultSet) result.getObject(1);
            ls.add(containers.getMetaData().getColumnLabel(1) + "|" + containers.getMetaData().getColumnLabel(2) + "|"
                    + containers.getMetaData().getColumnLabel(3) + "|" + containers.getMetaData().getColumnLabel(4) + "|" + containers.getMetaData().getColumnLabel(5)
                    + "|" + containers.getMetaData().getColumnLabel(6));
            while (containers.next()) {
                ls.add(containers.getString(1) + "|" + containers.getString(2) + "|" + containers.getString(3)
                        + containers.getString(4) + "|" + containers.getString(5) + "|" + containers.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }

    public List<String> aCm(Integer ano) {
        List<String> ls = new ArrayList<>();
        try (CallableStatement resultado = getConnection().prepareCall("{?= call a_cm (?)}")) {
            resultado.registerOutParameter(1, OracleTypes.CURSOR);
            resultado.setInt(2, ano);
            resultado.executeUpdate();
            ResultSet rs = (ResultSet) resultado.getObject(1);
            ls.add(rs.getMetaData().getColumnLabel(1) + "|" + rs.getMetaData().getColumnLabel(2));
            while (rs.next()) {
                ls.add(rs.getString(1) + "|" + rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }

    public float capacityRateNow(String shipId) {
        float fatorial = 0;
        try (CallableStatement resultado = getConnection().prepareCall("{?= call func_occupancy_rate_now (?)}")) {
            resultado.registerOutParameter(1, Types.FLOAT);
            resultado.setString(2, shipId);
            resultado.executeUpdate();
            fatorial = resultado.getFloat(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fatorial;
    }


    public float capacityRate(String shipId, String cmId) {
        float fatorial = 0;
        try (CallableStatement resultado = getConnection().prepareCall("{?= call func_occupancy_rate (?,?)}")) {
            resultado.registerOutParameter(1, Types.FLOAT);
            resultado.setString(2, shipId);
            resultado.setString(3, cmId);
            resultado.executeUpdate();
            fatorial = resultado.getFloat(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fatorial;
    }


    public List<String> containersToLoad(String shipId) {
        List<String> ls = new ArrayList<>();
        try (CallableStatement result = getConnection().prepareCall("{?= call containers_to_load(?)}")) {
            result.registerOutParameter(1, OracleTypes.CURSOR);
            result.setString(2, shipId);
            result.executeUpdate();
            ResultSet containers = (ResultSet) result.getObject(1);
            ls.add(containers.getMetaData().getColumnLabel(1) + "|" + containers.getMetaData().getColumnLabel(2) + "|"
                    + containers.getMetaData().getColumnLabel(3));
            while (containers.next()) {
                ls.add(containers.getString(1) + "|" + containers.getString(2) + "|" + containers.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }

}
