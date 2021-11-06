package lapr.project.model.Containers.IDB;

import lapr.project.model.Containers.Container;

import java.util.List;

public interface IContainerDB {

    List<Container> containers();

    boolean addShipContainers(Container containers, String id);
}
