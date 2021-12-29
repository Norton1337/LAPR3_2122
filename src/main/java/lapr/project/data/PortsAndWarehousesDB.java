
package lapr.project.data;

import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;
import oracle.jdbc.OracleTypes;
import lapr.project.data.db_scripts.DataHandler;
import java.sql.Types;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PortsAndWarehousesDB implements ILocals {

    public List<Locals> getAllPortsAndWarehouses() {
        return null;
    }

    @Override
    public List<Locals> getAllCapitals() {
        return null;
    }

    @Override
    public Locals getLocalWithLocalName(String name) {
        return null;
    }

    @Override
    public Locals getLocalWithCapital(String capital) {
        return null;
    }

    public Locals getPortsAndWarehousesById(String id) {
        return null;
    }


    public boolean addPortsAndWarehouses(Locals portsAndWarehouses) {
        return false;
    }

    @Override
    public boolean updatePortsAndWarehouses(Locals portsAndWarehouses) {
        return false;
    }

    @Override
    public boolean removePortsAndWarehouses(String id) {
        return false;
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
    public List<String> freeships() throws SQLException {
        /*List<String> ls = new ArrayList<>();
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

         */
        return null;
    }
}



