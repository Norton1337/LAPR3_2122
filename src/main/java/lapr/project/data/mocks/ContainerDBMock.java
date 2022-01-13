package lapr.project.data.mocks;

import lapr.project.model.containers.Container;
import lapr.project.model.containers.idb.IContainerDB;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.randomUUID;

public class ContainerDBMock implements IContainerDB {

    List<Container> allContainers = new LinkedList<>();

    @Override
    public List<Container> getAllContainers() {
        return new LinkedList<>(allContainers);
    }

    @Override
    public boolean addContainer(Container container) {
        container.setId(randomUUID());
        return allContainers.add(container);
    }

    @Override
    public Container getContainer(String id) {
        for (Container elems : allContainers) {
            if (elems.getId().equals(id)) {
                return elems;
            }
        }
        return null;
    }

    @Override
    public Container getContainerByNumber(String number) {
        for(Container elems : allContainers) {
            if(elems.getContainerNumber() == Integer.parseInt(number)){
                return elems;
            }
        }
        return null;
    }

    @Override
    public boolean removeContainer(String id) {
        Container containerToBeRemoved = getContainer(id);
        return allContainers.remove(containerToBeRemoved);
    }

    @Override
    public String cont_where(String cont) throws SQLException {
        return null;
    }
}
