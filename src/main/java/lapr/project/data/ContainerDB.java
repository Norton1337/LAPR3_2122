package lapr.project.data;

import lapr.project.model.containers.Container;
import lapr.project.model.containers.idb.IContainerDB;
import lapr.project.data.db_scripts.DataHandler;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;



public class ContainerDB extends DataHandler implements IContainerDB {

    @Override
    public List<Container> getAllContainers() {
        return null;
    }

    @Override
    public boolean addContainer(Container containers) {
        return false;
    }

    @Override
    public Container getContainer(String id) {
        return null;
    }

    @Override
    public boolean removeContainer(String id) {
        return false;
    }

    @Override
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
