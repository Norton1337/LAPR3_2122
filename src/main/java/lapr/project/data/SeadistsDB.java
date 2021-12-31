package lapr.project.data;

import lapr.project.data.db_scripts.DataHandler;
import lapr.project.model.seadists.Seadist;
import lapr.project.model.seadists.idb.ISeadist;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

public class SeadistsDB extends DataHandler implements ISeadist {
    @Override
    public List<Seadist> getAllSeadist() {
        return null;
    }

    @Override
    public Seadist getSeadistById(String id) {
        return null;
    }

    @Override
    public boolean addSeadist(Seadist elem) {
        if(elem == null){
            return false;
        }
        try (CallableStatement result = getConnection().prepareCall("{call insertSeadist(?,?,?,?)}")) {
            result.setString(1,elem.getId());
            result.setString(2,elem.getFromPortId());
            result.setString(3,elem.getToPortId());
            result.setFloat(4,elem.getDistance());
            result.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
