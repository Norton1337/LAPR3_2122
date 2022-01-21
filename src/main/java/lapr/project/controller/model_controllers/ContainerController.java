package lapr.project.controller.model_controllers;

import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.containers.Container;
import lapr.project.model.containers.idb.IContainerDB;
import lapr.project.model.locals.Locals;
import lapr.project.model.operation.Operation;
import lapr.project.model.vehicle.Vehicles;

import java.util.List;

import static lapr.project.utils.Utils.toDate;
import static lapr.project.utils.Utils.toInt;

public class ContainerController {

    private final IContainerDB containerDB;
    private final CargoManifestController cargoManifestController;
    private final OperationController operationController;
    private final VehiclesController vehiclesController;
    private final LocalsController localsController;

    public ContainerController(IContainerDB containerDB, CargoManifestController cargoManifestController,
                               OperationController operationController, VehiclesController vehiclesController, LocalsController localsController) {
        this.containerDB = containerDB;
        this.cargoManifestController = cargoManifestController;
        this.operationController = operationController;
        this.vehiclesController = vehiclesController;
        this.localsController = localsController;
    }

    public boolean addContainer(Container newContainer) {
        containerDB.addContainer(newContainer);
        return true;
    }

    public String whereIsContainer(String containerNumber, String userName, String leas){
        String whereIsContainer;
        CargoManifest cargoManifest = null;
        Container container = null;
        for(Container elem : containerDB.getAllContainers()){
            if(elem.getContainerNumber() == toInt(containerNumber)){
                container = elem;
            }
        }
        for(Operation elem : operationController.getAllOperations()){
            if(elem.getContainerId().equals(container.getId())){
                if(cargoManifest == null) {
                    cargoManifest = cargoManifestController.findCargoById(elem.getCargoManifestId());
                }else if(toDate(cargoManifest.getDate()).compareTo
                        (toDate(cargoManifestController.findCargoById(elem.getCargoManifestId()).getDate())) < 0){
                    cargoManifest = cargoManifestController.findCargoById(elem.getCargoManifestId());
                }
            }
        }

        if(cargoManifest.getOperationType().equals("Load")){
            Vehicles vehicles = vehiclesController.getVehicle(cargoManifest.getVehicleId());
            return "Container is on vehicle: " + vehicles.getVehicle_recon();
        }else if(cargoManifest.getOperationType().equals("Unload")){
            Locals local = localsController.getLocalWithPortId(cargoManifest.getCurrentLocalId());
            return "Container is on port: " + String.valueOf(local.getLocalCode());
        }
        return "Container not found.";
    }

    public List<Container> getAllContainers() {
        return containerDB.getAllContainers();
    }


    public Container findContainerByNumber(String number) {
        return containerDB.getContainerByNumber(number);
    }
}
