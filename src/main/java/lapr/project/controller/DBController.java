package lapr.project.controller;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import javax.print.DocFlavor.STRING;

import lapr.project.data.db_scripts.DataHandler;

public class DBController {

    DataHandler data;
    Connection con;

    public DBController() {
        this.data = new DataHandler();
        con = data.getConnection();
    }

    public void createdb() throws IOException, SQLException {
        data.scriptRunner("Docs/Database/BootStrap.sql");
    }

    public float capacity_rate(String ship_id, String cm_id) throws SQLException {
        CallableStatement resultado = con.prepareCall("{?= call func_occupancy_rate (?)}");
        resultado.registerOutParameter(1, Types.FLOAT);
        resultado.setString("p_cargo_id", cm_id);
        resultado.setString("p_ship_id", ship_id);
        resultado.executeUpdate();
        float fatorial = resultado.getFloat(1);
        return fatorial;
    }
}
