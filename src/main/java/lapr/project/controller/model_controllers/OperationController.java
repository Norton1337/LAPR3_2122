package lapr.project.controller.model_controllers;

import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.containers.Container;
import lapr.project.model.containers.idb.IContainerDB;
import lapr.project.model.locals.Locals;
import lapr.project.model.operation.Operation;
import lapr.project.model.operation.idb.IOperation;
import lapr.project.model.ships.Ship;
import lapr.project.model.vehicle.Vehicles;

import java.time.LocalDateTime;
import java.util.*;

import static lapr.project.utils.Utils.*;

public class OperationController {

    private final IOperation operationDB;
    private final IContainerDB containerDB;
    private final LocalsController localsController;
    private final CargoManifestController cargoManifestController;
    private final ShipController shipController;
    private final VehiclesController vehiclesController;

    public OperationController(IOperation operationDB, IContainerDB containerDB, LocalsController localsController,
            CargoManifestController cargoManifestController, ShipController shipController,
            VehiclesController vehiclesController) {
        this.operationDB = operationDB;
        this.containerDB = containerDB;
        this.localsController = localsController;
        this.cargoManifestController = cargoManifestController;
        this.shipController = shipController;
        this.vehiclesController = vehiclesController;
    }

    public boolean addOperation(Operation operation, String containerNumber, String cargoManifestRecon,
            String warehouseCode) {
        CargoManifest cargoManifest = cargoManifestController.findCargoByRecon(cargoManifestRecon);
        Locals warehouse = localsController.getWarehouseByCode(warehouseCode);
        Vehicles vehicle = vehiclesController.getVehicle(cargoManifest.getVehicleId());

        for (Container elems : containerDB.getAllContainers()) {
            if (elems.getContainerNumber() == toInt(containerNumber)) {
                operation.setContainerId(elems.getId());
            }
        }

        operation.setOperation_warehouse(warehouse.getId());
        operation.setCargoManifestId(cargoManifest.getId());

        operationDB.addOperation(operation);
        return true;
    }

    public List<String> getOccupancyRate_and_ContainersLeavingNextMonth(int port_code) {
        String occupancyRate;
        List<String> warehouseOccupancyAndContainers = new ArrayList<>();
        List<String> list = new ArrayList<>();
        Locals port = localsController.getLocalWithPortId(String.valueOf(port_code));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Objects.requireNonNull(toDate(LocalDateTime.now().toString())));
        calendar.add(Calendar.MONTH, 1);
        Date dateTimeNextMonth = calendar.getTime();
        for (CargoManifest cargo : cargoManifestController.getAllCargoManifest()) {
            if (toDate(cargo.getDate()).compareTo(toDate(dateTimeNextMonth.toString())) < 0) {
                if (cargo.getOperationType().equals("Load")) {
                    if (cargo.getCurrentLocalId().equals(port.getId())) {
                        list.addAll(getContainersNumberByCargo(cargo.getId()));
                    }
                }
            }
        }
        list.add("Containers Leaving:");

        for (Locals warehouse : localsController.getAllWarehouses()) {
            if (warehouse.getPortId().equals(port.getId())) {
                warehouseOccupancyAndContainers.add(String.valueOf("Warehouse Code: " + warehouse.getLocalCode()));
                warehouseOccupancyAndContainers.add(String.valueOf("Warehouse capacity rate: "
                        + (warehouse.getUsedCapacity() / warehouse.getLocalCapacity()) * 100) + "%");
                warehouseOccupancyAndContainers.add(list.get(0));
            }
        }

        return warehouseOccupancyAndContainers;
    }

    public List<Operation> getAllOperations() {
        return operationDB.allOperations();
    }

    public List<String> getContainersNumberByCargo(String cargoID) {
        List<String> containersNumber = new ArrayList<>();
        for (Operation elem : getAllOperations()) {
            if (elem.getCargoManifestId().equals(cargoID)) {
                containersNumber.add(String
                        .valueOf("Number:" + containerDB.getContainer(elem.getContainerId()).getContainerNumber()));
            }
        }
        return containersNumber;
    }

    public Operation findById(String id) {
        return operationDB.getOperation(id);
    }

}
