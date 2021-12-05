package lapr.project.data;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.db_scripts.DataHandler;
import oracle.jdbc.OracleTypes;

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

    public List<String> freeships() throws SQLException {
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

    public List<String> containers_to_offload(String ship_id) throws SQLException {
        List<String> ls = new ArrayList<>();
        try (CallableStatement resultado = getConnection().prepareCall("{?= call containers_to_offload(?)}")) {
            resultado.registerOutParameter(1, OracleTypes.CURSOR);
            resultado.setString(2, ship_id);
            resultado.executeUpdate();
            ResultSet containers = (ResultSet) resultado.getObject(1);
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

    public List<String> a_cm(Integer ano) throws SQLException {
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

    public String cont_where(String cont) throws SQLException {
        String whereCont = null;
        try (CallableStatement resultado = getConnection().prepareCall("{?= call cont_where (?)}")) {
            resultado.registerOutParameter(1, Types.VARCHAR);
            resultado.setString(2, cont);
            resultado.executeUpdate();
            whereCont = resultado.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return whereCont;
    }

}
