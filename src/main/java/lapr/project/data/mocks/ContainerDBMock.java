package lapr.project.data.mocks;

import java.util.LinkedList;
import java.util.List;

import lapr.project.model.containers.Container;
import lapr.project.model.containers.idb.IContainerDB;

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
        for(Container elems : allContainers){
            if(elems.getId().equals(id)){
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
}
