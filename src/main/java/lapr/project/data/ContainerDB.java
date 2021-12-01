package lapr.project.data;

import lapr.project.model.containers.Container;
import lapr.project.model.containers.idb.IContainerDB;

import java.util.List;

public class ContainerDB implements IContainerDB {

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
}
