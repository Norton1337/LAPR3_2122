package lapr.project.model.containers.idb;

import java.util.List;

import lapr.project.model.containers.Container;

public interface IContainerDB {

    List<Container> getAllContainers();

    boolean addContainer(Container containers);

    Container getContainer(String id);

    boolean removeContainer(String id);
}
