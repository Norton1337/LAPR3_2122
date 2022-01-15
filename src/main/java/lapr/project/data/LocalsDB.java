package lapr.project.data;

import lapr.project.data.db_scripts.DataHandler;
import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalsDB extends DataHandler implements ILocals {

    public List<Locals> getAllLocals() {
        return null;
    }


    public boolean addPortsAndWarehouses(Locals portsAndWarehouses) {
        if (portsAndWarehouses == null) {
            return false;
        }
        try (CallableStatement result = getConnection().prepareCall("{call insertLocal(?,?,?,?,?,?,?,?,?)}")) {
            String[] coordinates = portsAndWarehouses.getCoordinates().split(",");
            result.setString(1, portsAndWarehouses.getId());
            result.setInt(2, portsAndWarehouses.getLocalCode());/*tem de ser string em vez de Int*/
            result.setInt(3, portsAndWarehouses.getLocalCode());/*O code nao pode ser igual ao PortId*/
            result.setInt(4, portsAndWarehouses.getLocalCapacity());
            result.setString(5, portsAndWarehouses.getName());
            result.setString(6, portsAndWarehouses.getType());
            result.setString(7, portsAndWarehouses.getCountryId());
            result.setFloat(8, Float.parseFloat(coordinates[0]));
            result.setFloat(9, Float.parseFloat(coordinates[1]));
            result.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Locals getPortByPortIdCode(String code) {
        return null;
    }

    public boolean updateShip(Locals portsAndWarehouses) {
        return false;
    }

    public boolean removeShip(String id) {
        return false;
    }

    @Override
    public List<String> freeships() {
        List<String> ls = new ArrayList<>();
        try (CallableStatement resultado = getConnection().prepareCall("{?= call func_freeships()}")) {
            resultado.registerOutParameter(1, OracleTypes.CURSOR);
            resultado.executeUpdate();
            ResultSet ships = (ResultSet) resultado.getObject(1);
            ls.add(ships.getMetaData().getColumnLabel(1) + "|" + ships.getMetaData().getColumnLabel(2));
            while (ships.next()) {
                ls.add(ships.getDate(1) + "|" + ships.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }
}



