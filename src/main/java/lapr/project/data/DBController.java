package lapr.project.data;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import lapr.project.data.db_scripts.DataHandler;

public class DBController extends DataHandler {

    public void createdb() throws IOException, SQLException {
        new DataHandler().scriptRunner("Docs/Database/BootStrap.sql");
    }

    public float capacity_rate(String ship_id, String cm_id) throws SQLException {
        float fatorial = 0;
        try (CallableStatement resultado = getConnection().prepareCall("{?= call func_occupancy_rate (?,?)}")) {
            resultado.registerOutParameter(1, Types.FLOAT);
            resultado.setString(2, ship_id);
            resultado.setString(3, cm_id);
            resultado.executeUpdate();
            fatorial = resultado.getFloat(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fatorial;
    }

    public float capacity_rate_now(String ship_id) throws SQLException {
        float fatorial = 0;
        try (CallableStatement resultado = getConnection().prepareCall("{?= call func_occupancy_rate_now (?)}")) {
            resultado.registerOutParameter(1, Types.FLOAT);
            resultado.setString(2, ship_id);
            resultado.executeUpdate();
            fatorial = resultado.getFloat(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fatorial;
    }
}
