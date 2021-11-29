package lapr.project.model.containers.idb;

import java.util.List;

import lapr.project.model.containers.Container;

public interface IContainerDB {

    List<Container> containers();

    boolean addShipContainers(Container containers, String id);
}
