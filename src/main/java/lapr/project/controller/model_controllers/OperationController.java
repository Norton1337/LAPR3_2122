package lapr.project.controller.model_controllers;

import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.cargoManifest.idb.ICargoManifest;
import lapr.project.model.containers.Container;
import lapr.project.model.containers.idb.IContainerDB;
import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;
import lapr.project.model.operation.Operation;
import lapr.project.model.operation.idb.IOperation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lapr.project.utils.Utils.*;

public class OperationController {

    private final IOperation operationDB;
    private final IContainerDB containerDB;
    private final LocalsController localsController;
    private final CargoManifestController cargoManifestController;


    public OperationController(IOperation operationDB,IContainerDB containerDB, LocalsController localsController, CargoManifestController cargoManifestController) {
        this.operationDB = operationDB;
        this.containerDB = containerDB;
        this.localsController = localsController;
        this.cargoManifestController = cargoManifestController;
    }

    public boolean addOperation(Operation operation,String containerNumber,String cargoManifestRecon, String warehouseCode) {
        CargoManifest cargoManifest = cargoManifestController.findCargoByRecon(cargoManifestRecon);
        Locals warehouse = localsController.getWarehouseByCode(warehouseCode);

        if(cargoManifest.getOperationType().equals("Unload")){
            warehouse.setUsedCapacity(warehouse.getUsedCapacity() + 1);
        } else if (cargoManifest.getOperationType().equals("Load")){
            warehouse.setUsedCapacity(warehouse.getUsedCapacity() - 1);
        }

        for (Container elems : containerDB.getAllContainers()) {
            if (elems.getContainerNumber() == toInt(containerNumber)) {
                operation.setContainerId(elems.getId());
            }
        }
        operation.setOperation_warehouse(warehouse.getId());
        operation.setCargoManifestId(warehouse.getId());

        operationDB.addOperation(operation);
        return true;
    }

    public List<String> getOccupancyRate_and_ContainersLeavingNextMonth(int port_code){
        String occupancyRate;
        List<String> warehouseOccupancyAndContainers = new ArrayList<>();
        List<String> list = new ArrayList<>();
        Locals port = localsController.getLocalWithPortId(String.valueOf(port_code));

        for(CargoManifest cargo : cargoManifestController.getAllCargoManifest()){
            if(cargo.getOperationType().equals("Load")){
                if(cargo.getCurrentLocalId().equals(port.getId())){
                    list.addAll(getContainersNumberByCargo(cargo.getId()));
                }
            }
        }

        if(list.isEmpty()){
            System.out.println("Here");
        }

        for(Locals warehouse : localsController.getAllWarehouses()){
            if(warehouse.getPortId().equals(port.getId())){
                warehouseOccupancyAndContainers.add(String.valueOf("Warehouse Code: " + warehouse.getLocalCode()));
                warehouseOccupancyAndContainers.add(String.valueOf("Warehouse capacity rate: " + (warehouse.getUsedCapacity()/warehouse.getLocalCapacity()) * 100) + "%");
                warehouseOccupancyAndContainers.addAll(list);
            }
        }


        return warehouseOccupancyAndContainers;
    }

    public List<Operation> getAllOperations() {
        return operationDB.allOperations();
    }

    public List<String> getContainersNumberByCargo(String cargoID){
        List<String> containersNumber = new ArrayList<>();
        for(Operation elem : getAllOperations()){
            if(elem.getCargoManifestId().equals(cargoID)){
                containersNumber.add(String.valueOf("Number:" + containerDB.getContainer(elem.getContainerId()).getContainerNumber()));
            }
        }
        return containersNumber;
    }

    public Operation findById(String id) {
        return operationDB.getOperation(id);
    }

}
