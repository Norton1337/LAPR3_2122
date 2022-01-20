package lapr.project.data.Database;

import lapr.project.data.db_scripts.DataHandler;
import lapr.project.model.borders.Borders;
import lapr.project.model.borders.idb.IBordersDB;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

public class BordersDB extends DataHandler implements IBordersDB {
    @Override
    public List<Borders> getAllBorders() {
        return null;
    }

    @Override
    public Borders getBorderById(String id) {
        return null;
    }

    @Override
    public boolean addBorder(Borders elem) {
        if (elem == null) {
            return false;
        }
        try (CallableStatement result = getConnection().prepareCall("{call insertBorders(?,?,?)}")) {
            result.setString(1, elem.getId());
            result.setString(2, elem.getCountry1Id());
            result.setString(3, elem.getCountry2Id());
            result.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
