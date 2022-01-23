package lapr.project.controller.model_controllers;

import static lapr.project.utils.Utils.toDate;
import static lapr.project.utils.Utils.toInt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.containers.Container;
import lapr.project.model.containers.idb.IContainerDB;
import lapr.project.model.locals.Locals;
import lapr.project.model.operation.Operation;
import lapr.project.model.operation.idb.IOperation;
import lapr.project.model.ships.Ship;
import lapr.project.utils.Utils;

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
        CargoManifest cm = cargoManifestController.findCargoByRecon(cargoManifestRecon);
        Locals warehouse = localsController.getWarehouseByCode(warehouseCode);
        Locals port = localsController.getLocalWithPortId(cm.getCurrentLocalId());
        boolean shipProblem = false;
        boolean capacityProblem = false;
        if (vehiclesController.getVehicle(cm.getVehicleId()).getType().equals("Ship")
                && cm.getOperationType().equals("Load")) {
            Ship ship = shipController.findShipByID(cm.getVehicleId());
            shipProblem = cargoManifestController.capacityRate(ship.getMMSI(), cargoManifestRecon)
                    / 100
                    * ship.getLoadCapacity() + 1 > ship.getLoadCapacity();
        }
        if (cm.getOperationType().equals("Unload")) {
            capacityProblem = this.capacityRateWarehouse(warehouse, port, cm) / 100
                    * warehouse.getLocalCapacity() + 1 > warehouse.getLocalCapacity();
        }
        if (shipProblem || capacityProblem) {
            for (Operation op : this.getAllOperations()) {
                if (op.getCargoManifestId().equals(cm.getId())) {
                    operationDB.removeOperation(op.getId());
                }
            }
            cargoManifestController.removeCargo(cm.getId());
            return false;
        }
        for (Container elems : containerDB.getAllContainers()) {
            if (elems.getContainerNumber() == toInt(containerNumber)) {
                operation.setContainerId(elems.getId());
            }
        }

        operation.setOperation_warehouse(warehouse.getId());
        operation.setCargoManifestId(cm.getId());

        operationDB.addOperation(operation);
        return true;
    }

    public double capacityRateWarehouse(Locals warehouse, Locals port, CargoManifest cm) {
        List<CargoManifest> lcargo = new ArrayList<>();
        int containers = 0;
        for (CargoManifest cargo : cargoManifestController.getAllCargoManifest()) {
            if (Objects.requireNonNull(toDate(cargo.getDate())).compareTo(toDate(cm.getDate())) <= 0
                    && cargo.getCurrentLocalId().equals(String.valueOf(port.getLocalCode()))) {
                lcargo.add(cargo);
            }
        }

        Utils.cargosOrderedByTime(lcargo);

        for (CargoManifest cargo : lcargo) {
            if (cm.getOperationType().equals("Load")) {
                containers -= this.getContainersNumberByCargo(cargo.getId()).size();
            } else {
                containers += this.getContainersNumberByCargo(cargo.getId()).size();
            }
        }

        return containers / warehouse.getLocalCapacity() * 100;
    }

    public Map<Locals, List<String>> getOccupancyRateAndContainersLeavingNextMonth(int portCode) {
        List<String> warehouseOccupancyAndContainers = new ArrayList<>();
        Map<Locals, List<String>> containersleaving = new HashMap<>();
        List<CargoManifest> cargos = new ArrayList<>();
        List<Locals> warehouses = new ArrayList<>();
        Locals port = localsController.getLocalWithPortId(String.valueOf(portCode));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Objects.requireNonNull(toDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))));
        calendar.add(Calendar.MONTH, 1);
        Date dateTimeNextMonth = calendar.getTime();
        for (CargoManifest cm : cargoManifestController.getAllCargoManifest()) {
            if (toDate(cm.getDate()).compareTo(dateTimeNextMonth) < 0
                    && toDate(cm.getDate()).compareTo(
                            toDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))) >= 0
                    && cm.getCurrentLocalId().equals(String.valueOf(port.getLocalCode())) && cm.getOperationType().equals("Load")) {
                    cargos.add(cm);
            }
        }

        for (Locals local : localsController.getAllWarehouses()) {
            if (local.getPortId().equals(port.getId())) {
                warehouses.add(local);
            }
        }

        for (CargoManifest cm : cargos) {
            for (Operation op : this.getAllOperations()) {
                if (op.getCargoManifestId().equals(cm.getId())) {
                    for (Locals warehouse : warehouses) {
                        if (op.getOperation_warehouse().equals(warehouse.getId())) {
                            if (containersleaving.containsKey(warehouse)) {
                                warehouseOccupancyAndContainers = containersleaving.get(warehouse);
                                warehouseOccupancyAndContainers
                                        .add("Capacity Rate: " + this.capacityRateWarehouse(warehouse, port, cm)
                                                + " Container: "
                                                + op.getContainerId() + "Date Leaving: " + cm.getDate());
                                containersleaving.put(warehouse, warehouseOccupancyAndContainers);
                            } else {
                                warehouseOccupancyAndContainers.clear();
                                warehouseOccupancyAndContainers
                                        .add("Capacity Rate: " + this.capacityRateWarehouse(warehouse, port, cm)
                                                + " Container: "
                                                + op.getContainerId() + " Date Leaving: " + cm.getDate());
                                containersleaving.put(warehouse, warehouseOccupancyAndContainers);
                            }
                        }
                    }
                }
            }
        }
        return containersleaving;
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

    public List<String> portMap(int portCode, String mes) {
        Locals port = localsController.getLocalWithPortId(String.valueOf(portCode));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Objects.requireNonNull(toDate(mes)));
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date monthFirstDay = calendar.getTime();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date monthLastDay = calendar.getTime();
        List<CargoManifest> lcm = new ArrayList<>();
        List<String> ls = new ArrayList<>();
        for (CargoManifest cm : cargoManifestController.getAllCargoManifest()) {
            if (cm.getCurrentLocalId().equals(String.valueOf(port.getLocalCode()))
                    && Objects.requireNonNull(toDate(cm.getDate())).compareTo(monthFirstDay) >= 0
                    && toDate(cm.getDate()).compareTo(monthLastDay) <= 0) {
                lcm.add(cm);
            }
        }
        for (CargoManifest cm : lcm) {
            for (Operation op : this.getAllOperations()) {
                if (op.getCargoManifestId().equals(cm.getId())) {
                    if (cm.getOperationType().equals("Load")) {
                        ls.add("Warehouse: " + op.getOperation_warehouse() + " Container: " + op.getContainerId()
                                + " Date Leaving: " + cm.getDate());
                    } else {
                        ls.add("Warehouse: " + op.getOperation_warehouse() + " Container: " + op.getContainerId()
                                + " Date Entring: " + cm.getDate());
                    }
                }
            }
        }
        if (ls.isEmpty()) {
            ls.add("There aren't any operations on the port in that month");
        }
        return ls;
    }
}
