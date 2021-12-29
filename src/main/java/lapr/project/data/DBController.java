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





}
