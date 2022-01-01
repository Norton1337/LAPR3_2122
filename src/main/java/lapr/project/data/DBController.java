package lapr.project.data;

import lapr.project.data.db_scripts.DataHandler;

import java.io.IOException;
import java.sql.SQLException;

public class DBController extends DataHandler {

    public void createdb() throws IOException, SQLException {
        new DataHandler().scriptRunner("Docs/Database/BootStrap.sql");
    }


}
