package lapr.project.controller.model_controllers;

import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.cargoManifest.idb.ICargoManifest;
import lapr.project.model.operation.Operation;
import lapr.project.model.operation.idb.IOperation;
import lapr.project.model.ships.Ship;
import lapr.project.model.vehicle.Vehicles;
import lapr.project.model.vehicle.idb.IVehicle;
import lapr.project.utils.Utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static lapr.project.utils.Utils.toDate;

public class CargoManifestController {

    private final IVehicle vehicleDB;
    private final ICargoManifest cargoManifestDB;
    private final IOperation operationDB;

    public CargoManifestController(IVehicle vehicleDB, ICargoManifest cargoManifestDB, IOperation operationDB) {
        this.vehicleDB = vehicleDB;
        this.cargoManifestDB = cargoManifestDB;
        this.operationDB = operationDB;
    }

    public boolean addCargoManifest(CargoManifest newCargoManifest, String vehicleID) {
        for (Vehicles elems : vehicleDB.getAllVehicles()) {
            if (elems.getVehicle_recon().equals(vehicleID)) {
                newCargoManifest.setVehicleId(elems.getId());
            }
        }

        cargoManifestDB.addCargoManifest(newCargoManifest);
        return true;
    }

    public List<CargoManifest> getAllCargoManifest() {
        return cargoManifestDB.getAllCargoManifest();
    }

    public CargoManifest findCargoById(String id) {
        return cargoManifestDB.getCargoManifest(id);
    }

    public CargoManifest findCargoByRecon(String recon) {
        return cargoManifestDB.getCargoManifestByRecon(recon);
    }

    public CargoManifest findLastCargoBeforeDate(String date, String operationType, String portID) {
        return cargoManifestDB.getCargoManifestBeforeDate(date, operationType, portID);
    }

    public List<String> containers_to_offload(String mmsi) {
        String ship_id = null;
        for (Vehicles elem : vehicleDB.getAllShips()) {
            if (elem.getVehicle_recon().equals(mmsi)) {
                ship_id = elem.getId();
            }
        }
        List<String> containersOffload = new ArrayList<>();
        List<CargoManifest> shipCargos = new ArrayList<>();
        CargoManifest shipOffloadCargo = null;
        double date = (double) System.currentTimeMillis();

        for (CargoManifest elem : getAllCargoManifest()) {

            if (elem.getVehicleId().equals(ship_id)) {
                if (elem.getOperationType().equals("Unload")) {
                    Date dateElem = toDate(elem.getDate());
                    double dateElemMilli = (double) dateElem.getTime();
                    if (dateElemMilli > date) {
                        shipCargos.add(elem);
                    }
                }
            }
        }
        if (shipCargos.size() == 0) {
            return null;
        }
        Utils.cargosOrderedByTime(shipCargos);
        shipOffloadCargo = shipCargos.get(0);
        for (Operation elem : operationDB.allOperations()) {
            if (elem.getCargoManifestId().equals(shipOffloadCargo.getId())) {
                containersOffload.add(elem.getContainerId());
            }
        }
        return containersOffload;
    }

    public List<String> containers_to_load(String mmsi) {
        String ship_id = null;
        for (Vehicles elem : vehicleDB.getAllShips()) {
            if (elem.getVehicle_recon().equals(mmsi)) {
                ship_id = elem.getId();
            }
        }
        List<String> containersToLoad = new ArrayList<>();
        List<CargoManifest> shipCargos = new ArrayList<>();
        CargoManifest shipToLoadCargo = null;
        double date = (double) System.currentTimeMillis();
        for (CargoManifest elem : getAllCargoManifest()) {
            if (elem.getVehicleId().equals(ship_id)) {
                if (elem.getOperationType().equals("Load")) {
                    Date dateElem = toDate(elem.getDate());
                    double dateElemMilli = (double) dateElem.getTime();
                    if (dateElemMilli > date) {
                        shipCargos.add(elem);
                    }
                }
            }
        }
        if (shipCargos.size() == 0) {
            return null;
        }
        Utils.cargosOrderedByTime(shipCargos);
        shipToLoadCargo = shipCargos.get(0);
        for (Operation elem : operationDB.allOperations()) {
            if (elem.getCargoManifestId().equals(shipToLoadCargo.getId())) {
                containersToLoad.add(elem.getContainerId());
            }
        }
        return containersToLoad;
    }

    public List<String> a_cm(String ano, String ship_id) {
        List<String> cargosID = new ArrayList<>();
        List<String> returnList = new ArrayList<>();
        int contCargos = 0;
        int contOperations = 0;
        int i = 0;
        for (CargoManifest elem : getAllCargoManifest()) {
            if (elem.getDate().contains(ano)) {
                if (elem.getVehicleId().equals(ship_id)) {
                    cargosID.add(elem.getId());
                    contCargos++;
                }
            }
        }
        returnList.add(String.valueOf(contCargos));

        for (Operation elem : this.operationDB.allOperations()) {
            if (elem.getCargoManifestId().equals(cargosID.get(i))) {
                contOperations++;
            }
        }
        returnList.add(String.valueOf(contOperations / contCargos));

        return returnList;
    }

    public boolean removeCargo(String id) {
        return operationDB.removeOperation(id);
    }

    public double capacity_rate(String mmsi, String cargo_recon, ShipController shipController) {
        double result = 0;
        CargoManifest cargo = this.findCargoByRecon(cargo_recon);
        List<CargoManifest> lcargo = new ArrayList<>();
        List<String> lContainers = new ArrayList<>();
        Ship ship = shipController.findShipByMMSI(mmsi);
        for (CargoManifest cm : this.getAllCargoManifest()) {
            if (cm.getVehicleId().equals(ship.getId())
                    && toDate(cm.getDate()).compareTo(toDate(cargo.getDate())) <= 0) {
                lcargo.add(cm);
            }
        }
        lcargo = Utils.cargosOrderedByTime(lcargo);
        for (CargoManifest cm : lcargo) {
            for (Operation op : operationDB.allOperations()) {
                if (op.getCargoManifestId().equals(cm.getId())) {
                    if (cm.getOperationType().equals("Load")) {
                        if (!lContainers.contains(op.getContainerId())) {
                            lContainers.add(op.getContainerId());
                        }
                    } else {
                        if (lContainers.contains(op.getContainerId())) {
                            lContainers.remove(op.getContainerId());
                        }
                    }
                }
            }
        }
        result = lContainers.size() / ship.getLoadCapacity() * 100;
        return result;
    }

    public List<Ship> free_ships(ShipController shipController) {
        List<Ship> freeShips = shipController.getAllShips();
        String cargo = null;
        Date nextMonday = toDate(LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        for (Ship ship : shipController.getAllShips()) {
            for (CargoManifest cargoManifest : this.getAllCargoManifest()) {
                if (toDate(cargoManifest.getDate()).compareTo(nextMonday) <= 0
                        && cargoManifest.getVehicleId().equals(ship.getId())) {
                    if (cargo == null) {
                        cargo = cargoManifest.getCargo_recon();
                    } else if (toDate(cargoManifest.getDate())
                            .compareTo(toDate(this.findCargoByRecon(cargo).getDate())) > 0) {
                        cargo = cargoManifest.getCargo_recon();
                    }
                }
            }
            if (cargo != null) {
                if (this.findCargoByRecon(cargo).getOperationType().equals("Load")) {
                    freeShips.remove(ship);
                }
            }
            cargo = null;
        }
        return freeShips;
    }

    public double capacity_rate_now(String mmsi, ShipController shipController) {
        String ship_id = null;
        for (Vehicles elem : vehicleDB.getAllShips()) {
            if (elem.getVehicle_recon().equals(mmsi)) {
                ship_id = elem.getId();
            }
        }
        String cargo_recon = null;
        for (CargoManifest cm : getAllCargoManifest()) {
            if (cm.getVehicleId().equals(ship_id)
                    && toDate(cm.getDate()).compareTo(toDate(LocalDateTime.now().toString())) < 0) {
                if (cargo_recon == null) {
                    cargo_recon = cm.getCargo_recon();
                } else if (toDate(this.findCargoByRecon(cargo_recon).getDate()).compareTo(
                        toDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))) < 0) {
                    cargo_recon = cm.getCargo_recon();
                }
            }
        }
        if (cargo_recon == null) {
            return 0;
        } else {
            return this.capacity_rate(mmsi, cargo_recon, shipController);
        }
    }

}
