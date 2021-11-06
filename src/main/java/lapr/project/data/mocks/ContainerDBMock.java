package lapr.project.data.mocks;

import lapr.project.model.Containers.Container;
import lapr.project.model.Containers.IDB.IContainerDB;

import java.util.LinkedList;
import java.util.List;

public class ContainerDBMock implements IContainerDB {

    List<Container> allContainers = new LinkedList<>();


    @Override
    public List<Container> containers() {
        return new LinkedList<>(allContainers);
    }

    @Override
    public boolean addShipContainers(Container containers, String id) {
        containers.setShipID(Integer.parseInt(id));
        return allContainers.add(containers);
    }
}
