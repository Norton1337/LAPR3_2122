package lapr.project.controller.model_controllers;

import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.containers.Container;
import lapr.project.model.containers.idb.IContainerDB;
import lapr.project.model.locals.Locals;
import lapr.project.model.operation.Operation;
import lapr.project.model.operation.idb.IOperation;
import lapr.project.model.ships.Ship;
import lapr.project.model.vehicle.Vehicles;
import lapr.project.utils.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        CargoManifest cm = cargoManifestController.findCargoByRecon(cargoManifestRecon);
        Locals warehouse = localsController.getWarehouseByCode(warehouseCode);
        Locals port = localsController.getLocalWithPortId(cm.getCurrentLocalId());
        boolean ship_problem = false;
        boolean capacity_problem = false;
        if (vehiclesController.getVehicle(cm.getVehicleId()).getType().equals("Ship")
                && cm.getOperationType().equals("Load")) {
            Ship ship = shipController.findShipByID(cm.getVehicleId());
            ship_problem = cargoManifestController.capacity_rate(ship.getMMSI(), cargoManifestRecon, shipController)
                    / 100
                    * ship.getLoadCapacity() + 1 > ship.getLoadCapacity();
        }
        if (cm.getOperationType().equals("Unload")) {
            capacity_problem = this.capacity_rate_warehouse(warehouse, port, cm) / 100
                    * warehouse.getLocalCapacity() + 1 > warehouse.getLocalCapacity();
        }
        if (ship_problem || capacity_problem) {
            System.out.println("Cargo Manifest Inválido\n Cargo manifest irá ser eliminado");
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

    public double capacity_rate_warehouse(Locals warehouse, Locals port, CargoManifest cm) {
        List<CargoManifest> lcargo = new ArrayList<>();
        int containers = 0;
        for (CargoManifest cargo : cargoManifestController.getAllCargoManifest()) {
            if (toDate(cargo.getDate()).compareTo(toDate(cm.getDate())) <= 0
                    && cargo.getCurrentLocalId().equals(String.valueOf(port.getLocalCode()))) {
                lcargo.add(cargo);
            }
        }

        lcargo = Utils.cargosOrderedByTime(lcargo);

        for (CargoManifest cargo : lcargo) {
            if (cm.getOperationType().equals("Load")) {
                containers -= this.getContainersNumberByCargo(cargo.getId()).size();
            } else {
                containers += this.getContainersNumberByCargo(cargo.getId()).size();
            }
        }

        return containers / warehouse.getLocalCapacity() * 100;
    }

    public Map<Locals, List<String>> getOccupancyRate_and_ContainersLeavingNextMonth(int port_code) {
        List<String> warehouseOccupancyAndContainers = new ArrayList<>();
        Map<Locals, List<String>> containersleaving = new HashMap<>();
        List<CargoManifest> cargos = new ArrayList<>();
        List<Locals> warehouses = new ArrayList<>();
        Locals port = localsController.getLocalWithPortId(String.valueOf(port_code));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        calendar.add(Calendar.MONTH, 1);
        Date dateTimeNextMonth = calendar.getTime();
        for (CargoManifest cm : cargoManifestController.getAllCargoManifest()) {
            if (toDate(cm.getDate()).compareTo(dateTimeNextMonth) < 0
                    && toDate(cm.getDate()).compareTo(
                            toDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))) >= 0
                    && cm.getCurrentLocalId().equals(String.valueOf(port.getLocalCode()))) {
                if (cm.getOperationType().equals("Load")) {
                    cargos.add(cm);
                }
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
                                        .add("Capacity Rate: " + this.capacity_rate_warehouse(warehouse, port, cm)
                                                + " Container: "
                                                + op.getContainerId() + "Date Leaving: " + cm.getDate());
                                containersleaving.put(warehouse, warehouseOccupancyAndContainers);
                            } else {
                                warehouseOccupancyAndContainers.clear();
                                warehouseOccupancyAndContainers
                                        .add("Capacity Rate: " + this.capacity_rate_warehouse(warehouse, port, cm)
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

    public List<String> port_map(int port_code, String mes) {
        Locals port = localsController.getLocalWithPortId(String.valueOf(port_code));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate(mes));
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date MonthFirstDay = calendar.getTime();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date MonthLastDay = calendar.getTime();
        List<CargoManifest> lcm = new ArrayList<>();
        List<String> ls = new ArrayList<>();
        for (CargoManifest cm : cargoManifestController.getAllCargoManifest()) {
            if (cm.getCurrentLocalId().equals(String.valueOf(port.getLocalCode()))
                    && toDate(cm.getDate()).compareTo(MonthFirstDay) >= 0
                    && toDate(cm.getDate()).compareTo(MonthLastDay) <= 0) {
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
        if (ls.size() == 0) {
            ls.add("There aren't any operations on the port in that month");
        }
        return ls;
    }
}
