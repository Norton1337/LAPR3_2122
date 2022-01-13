package lapr.project.model.containers.idb;

import lapr.project.model.containers.Container;

import java.sql.SQLException;
import java.util.List;

public interface IContainerDB {

    List<Container> getAllContainers();

    boolean addContainer(Container containers);

    Container getContainer(String id);

    Container getContainerByNumber(String number);

    boolean removeContainer(String id);

    public String cont_where(String cont) throws SQLException;
}
