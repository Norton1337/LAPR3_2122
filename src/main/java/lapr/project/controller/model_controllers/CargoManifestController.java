package lapr.project.controller.model_controllers;

import static lapr.project.utils.Utils.cargosOrderedByTime;
import static lapr.project.utils.Utils.printList;
import static lapr.project.utils.Utils.toDate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.cargoManifest.idb.ICargoManifest;
import lapr.project.model.operation.Operation;
import lapr.project.model.operation.idb.IOperation;
import lapr.project.model.ships.Ship;
import lapr.project.model.vehicle.Vehicles;
import lapr.project.model.vehicle.idb.IVehicle;
import lapr.project.utils.Utils;

public class CargoManifestController {

    private final IVehicle vehicleDB;
    private final ICargoManifest cargoManifestDB;
    private final IOperation operationDB;
    private final ShipController shipController;

    public CargoManifestController(IVehicle vehicleDB, ICargoManifest cargoManifestDB, IOperation operationDB,
            ShipController shipController) {
        this.vehicleDB = vehicleDB;
        this.cargoManifestDB = cargoManifestDB;
        this.operationDB = operationDB;
        this.shipController = shipController;
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

    public List<String> containersToOffload(String mmsi) {
        String shipId = null;
        for (Vehicles elem : vehicleDB.getAllShips()) {
            if (elem.getVehicle_recon().equals(mmsi)) {
                shipId = elem.getId();
            }
        }
        List<String> containersOffload = new ArrayList<>();
        List<CargoManifest> shipCargos = new ArrayList<>();
        CargoManifest shipOffloadCargo = null;
        double date = System.currentTimeMillis();

        for (CargoManifest elem : getAllCargoManifest()) {

            if (elem.getVehicleId().equals(shipId) && (elem.getOperationType().equals("Unload"))) {

                Date dateElem = toDate(elem.getDate());
                assert dateElem != null;
                double dateElemMilli = dateElem.getTime();
                if (dateElemMilli > date) {
                    shipCargos.add(elem);
                }
                
            }
        }
        if (shipCargos.isEmpty()) {
            return new ArrayList<>();
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

    public List<String> containersToLoad(String mmsi) {
        String shipId = null;
        for (Vehicles elem : vehicleDB.getAllShips()) {
            if (elem.getVehicle_recon().equals(mmsi)) {
                shipId = elem.getId();
            }
        }
        List<String> containersToLoad = new ArrayList<>();
        List<CargoManifest> shipCargos = new ArrayList<>();
        CargoManifest shipToLoadCargo = null;
        double date = System.currentTimeMillis();
        for (CargoManifest elem : getAllCargoManifest()) {
            if (elem.getVehicleId().equals(shipId) && (elem.getOperationType().equals("Load"))) {

                Date dateElem = toDate(elem.getDate());
                assert dateElem != null;
                double dateElemMilli = dateElem.getTime();
                if (dateElemMilli > date) {
                    shipCargos.add(elem);
                }
                
            }
        }
        if (shipCargos.isEmpty()) {
            return new ArrayList<>();
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

    public List<String> aCm(String ano, String shipId) {
        List<String> cargosID = new ArrayList<>();
        List<String> returnList = new ArrayList<>();
        int contCargos = 0;
        int contOperations = 0;
        int i = 0;
        for (CargoManifest elem : getAllCargoManifest()) {
            if (elem.getDate().contains(ano) && (elem.getVehicleId().equals(shipId))) {

                cargosID.add(elem.getId());
                contCargos++;

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

    public double capacityRate(String mmsi, String cargoRecon) {
        double result = 0;
        CargoManifest cargo = this.findCargoByRecon(cargoRecon);
        List<CargoManifest> lcargo = new ArrayList<>();
        List<String> lContainers = new ArrayList<>();
        Ship ship = shipController.findShipByMMSI(mmsi);
        for (CargoManifest cm : this.getAllCargoManifest()) {
            if (cm.getVehicleId().equals(ship.getId())
                    && Objects.requireNonNull(toDate(cm.getDate())).compareTo(toDate(cargo.getDate())) <= 0) {
                lcargo.add(cm);
            }
        }
        Utils.cargosOrderedByTime(lcargo);
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

    public List<Ship> freeShips() {
        List<Ship> freeShips = shipController.getAllShips();
        String cargo = null;
        Date nextMonday = toDate(LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        for (Ship ship : shipController.getAllShips()) {
            for (CargoManifest cargoManifest : this.getAllCargoManifest()) {
                if (Objects.requireNonNull(toDate(cargoManifest.getDate())).compareTo(nextMonday) <= 0
                        && cargoManifest.getVehicleId().equals(ship.getId())) {
                    if (cargo == null) {
                        cargo = cargoManifest.getCargo_recon();
                    } else if (Objects.requireNonNull(toDate(cargoManifest.getDate()))
                            .compareTo(toDate(this.findCargoByRecon(cargo).getDate())) > 0) {
                        cargo = cargoManifest.getCargo_recon();
                    }
                }
            }
            if (cargo != null && (this.findCargoByRecon(cargo).getOperationType().equals("Load"))) 
                freeShips.remove(ship);

            cargo = null;
        }
        return freeShips;
    }

    public double capacityRateNow(String mmsi) {
        String shipId = null;
        for (Vehicles elem : vehicleDB.getAllShips()) {
            if (elem.getVehicle_recon().equals(mmsi)) {
                shipId = elem.getId();
            }
        }
        String cargoRecon = null;
        for (CargoManifest cm : getAllCargoManifest()) {
            if (cm.getVehicleId().equals(shipId)
                    && Objects.requireNonNull(toDate(cm.getDate()))
                            .compareTo(toDate(LocalDateTime.now().toString())) < 0) {
                if (cargoRecon == null) {
                    cargoRecon = cm.getCargo_recon();
                } else if (Objects.requireNonNull(toDate(this.findCargoByRecon(cargoRecon).getDate())).compareTo(
                        toDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))) < 0) {
                            cargoRecon = cm.getCargo_recon();
                }
            }
        }
        if (cargoRecon == null) {
            return 0;
        } else {
            return this.capacityRate(mmsi, cargoRecon);
        }
    }

    public double capacityRateInPeriod(String mmsi, String startDate, String endDate) {
        List<CargoManifest> cargoManifests = new ArrayList<>();
        Ship ship = shipController.findShipByMMSI(mmsi);
        double capacityRate = 0;
        for (CargoManifest elem : getAllCargoManifest()) {
            if (elem.getVehicleId().equals(ship.getId())
                    && Objects.requireNonNull(toDate(elem.getDate())).compareTo(toDate(startDate)) > 0
                    && Objects.requireNonNull(toDate(elem.getDate())).compareTo(toDate(endDate)) < 0) {
                cargoManifests.add(elem);
            }
        }
        for (CargoManifest elem : cargoManifests) {
            capacityRate += capacityRate(mmsi, elem.getCargo_recon());
        }

        return (capacityRate / cargoManifests.size()) * 100;
    }

    public List<String> occupancyBelowThresHold() {
        List<String> returnList = new ArrayList<>();
        List<CargoManifest> cargosList = new ArrayList<>();

        for (CargoManifest elem : getAllCargoManifest()) {
            if (elem.getVehicleId().equals(shipController.findShipByID(elem.getVehicleId()).getId())) {
                cargosList.add(elem);
            }
        }
        cargosOrderedByTime(cargosList);

        for (CargoManifest elem : cargosList) {
            if (elem.getOperationType().equals("Load")) {
                break;
            }
            cargosList.remove(elem);
        }
        printList(cargosList);
        return returnList;
    }

    public Map<Ship, String> idleTimeShips() {
        Map<Ship, String> idleTime = new HashMap<>();
        int time = 0;
        List<CargoManifest> lcm = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Objects.requireNonNull(
                toDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))));
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        Date fristDay = calendar.getTime();
        for (Ship ship : shipController.getAllShips()) {
            time = 0;
            lcm.clear();
            for (CargoManifest cm : this.getAllCargoManifest()) {
                if (cm.getVehicleId().equals(ship.getId()) && toDate(cm.getDate()).compareTo(fristDay) >= 0
                        && toDate(cm.getDate()).compareTo(Objects.requireNonNull(
                                toDate(LocalDateTime.now()
                                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))) <= 0) {
                    lcm.add(cm);
                }
            }
            Utils.cargosOrderedByTime(lcm);
            if (lcm.isEmpty()) {
                for (CargoManifest cm : Utils.cargosOrderedByTime(this.getAllCargoManifest())) {
                    if (cm.getVehicleId().equals(ship.getId()) && toDate(cm.getDate()).compareTo(fristDay) < 0) {
                        lcm.add(cm);
                    }
                }
                if (!lcm.isEmpty()) {
                    if (lcm.get(lcm.size() - 1).getOperationType().equals("Unload")) {
                        time += ChronoUnit.DAYS.between(fristDay.toInstant(),
                                toDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                        .toInstant());
                    }
                } else {
                    time += ChronoUnit.DAYS.between(fristDay.toInstant(),
                            toDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                    .toInstant());
                }
            } else {
                if (lcm.get(0).getOperationType().equals("Load")) {
                    time += ChronoUnit.DAYS.between(toDate(lcm.get(0).getDate()).toInstant(), fristDay.toInstant());
                }
                if (lcm.get(lcm.size() - 1).getOperationType().equals("Unload")) {
                    time += ChronoUnit.DAYS.between(toDate(lcm.get(lcm.size() - 1).getDate()).toInstant(),
                            toDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                    .toInstant());
                }
                for (int i = 0; i < lcm.size(); i++) {
                    if (i + 1 < lcm.size() && lcm.get(i).getOperationType().equals("Unload")) {
                        time += ChronoUnit.DAYS.between(toDate(lcm.get(i).getDate()).toInstant(),
                                toDate(lcm.get(i + 1).getDate()).toInstant());
                    }
                }
            }
            idleTime.put(ship, "Idle Time: " + time);
        }
        return idleTime;
    }

}
