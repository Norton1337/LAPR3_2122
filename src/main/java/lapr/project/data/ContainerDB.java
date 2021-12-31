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
        try (CallableStatement result = getConnection().prepareCall("{call insertContainer()}")) {
            result.setString(1,containers.getId());
            result.setInt(2,containers.getContainerNumber());
            result.setInt(3,containers.getCheckDigit());
            result.setFloat(4,(float)containers.getContainerPayload());
            result.setFloat(5,(float)containers.getContainerTare());
            result.setFloat(6,(float)containers.getContainerGross());
            result.setFloat(7,(float)containers.getContainerVolume());
            result.setString(8,containers.getISOCODE());
            result.setString(9,containers.getCertificates());
            result.setString(10,containers.getRepairRecommendations());
            result.setString(11,containers.getContainerType());
            result.setString(12,"Default");
            result.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
