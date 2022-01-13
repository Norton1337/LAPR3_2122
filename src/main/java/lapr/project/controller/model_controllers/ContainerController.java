package lapr.project.controller.model_controllers;

import lapr.project.model.containers.Container;
import lapr.project.model.containers.idb.IContainerDB;
import lapr.project.model.ships.Generator;
import lapr.project.model.ships.Ship;

import java.util.ArrayList;
import java.util.List;

public class ContainerController {

    private final IContainerDB containerDB;

    public ContainerController(IContainerDB containerDB) {
        this.containerDB = containerDB;
    }

    public boolean addContainer(Container newContainer) {
        containerDB.addContainer(newContainer);
        return true;
    }

    public List<Container> getAllContainers() {
        return containerDB.getAllContainers();
    }


    public Container findContainerByNumber(String number) {
        return containerDB.getContainerByNumber(number);
    }
}
