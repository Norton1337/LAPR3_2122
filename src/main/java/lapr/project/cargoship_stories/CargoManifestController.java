package lapr.project.cargoship_stories;

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

import lapr.project.controller.model_controllers.ShipController;
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

    public List<String> containersToLoadAndOffload(String mmsi, String type) {
        Ship ship = shipController.findShipByMMSI(mmsi);
        List<String> returnList = new ArrayList<>();
        List<String> containersToLoadOrUnload = new ArrayList<>();
        List<CargoManifest> shipCargos = new ArrayList<>();
        CargoManifest shipToLoadCargo = null;
        Date date = toDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        if (type.equals("Load")) {
            return containersToLoad(mmsi, ship, containersToLoadOrUnload, shipCargos, shipToLoadCargo, date);
        } else if (type.equals("Unload")) {
            return containersToOffload(mmsi, ship, containersToLoadOrUnload, shipCargos, shipToLoadCargo, date);
        }
        containersToLoadOrUnload.add("No containers were found for this task.");
        return containersToLoadOrUnload;
    }

    private List<String> containersToOffload(String mmsi, Ship ship, List<String> containersToUnload,
            List<CargoManifest> shipCargos, CargoManifest shipToLoadCargo, Date date) {

        for (CargoManifest elem : getAllCargoManifest()) {
            if (elem.getVehicleId().equals(ship.getId()) && (elem.getOperationType().equals("Unload"))) {
                Date dateElem = toDate(elem.getDate());
                assert dateElem != null;
                if (dateElem.compareTo(date) > 0) {
                    shipCargos.add(elem);
                }
            }
        }
        if (shipCargos.isEmpty()) {
            return null;
        }
        Utils.cargosOrderedByTime(shipCargos);
        shipToLoadCargo = shipCargos.get(0);
        for (Operation elem : operationDB.allOperations()) {
            if (elem.getCargoManifestId().equals(shipToLoadCargo.getId())) {
                containersToUnload.add("Container id to unload: " + elem.getContainerId()
                        + " On date:" + shipToLoadCargo.getDate());
            }
        }
        return containersToUnload;
    }

    private List<String> containersToLoad(String mmsi, Ship ship, List<String> containersToLoad,
            List<CargoManifest> shipCargos, CargoManifest shipToLoadCargo, Date date) {

        for (CargoManifest elem : getAllCargoManifest()) {
            if (elem.getVehicleId().equals(ship.getId()) && (elem.getOperationType().equals("Load"))) {
                Date dateElem = toDate(elem.getDate());
                assert dateElem != null;
                if (dateElem.compareTo(date) > 0) {
                    shipCargos.add(elem);
                }
            }
        }
        if (shipCargos.isEmpty()) {
            return null;
        }
        Utils.cargosOrderedByTime(shipCargos);
        shipToLoadCargo = shipCargos.get(0);
        for (Operation elem : operationDB.allOperations()) {
            if (elem.getCargoManifestId().equals(shipToLoadCargo.getId())) {
                containersToLoad.add("Container id to load: " + elem.getContainerId()
                        + " On date:" + shipToLoadCargo.getDate());
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
            if (elem.getDate().contains(ano)
                    && (elem.getVehicleId().equals(shipController.findShipByMMSI(shipId).getId()))) {
                contCargos++;
                for (Operation elemOperation : this.operationDB.allOperations()) {
                    if (elemOperation.getCargoManifestId().equals(elem.getId())) {
                        contOperations++;
                    }
                }
            }

        }
        if (contCargos == 0 && contOperations == 0) {
            returnList.add("No cargo manifests available for this year.\n");
            return returnList;
        }
        returnList.add("Number of cargos this year: " + String.valueOf(contCargos));
        returnList.add("Average number of containers per manitfest: " + String.valueOf(contOperations / contCargos));
        return returnList;
    }

    public boolean removeCargo(String id) {
        return operationDB.removeOperation(id);
    }

    public double capacityRate(String mmsi, String cargoRecon) {
        double result = 0;
        int i = 0;
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

        if (ship.getLoadCapacity() != 0) {
            result = lContainers.size() / ship.getLoadCapacity() * 100;
        }
        return result;
    }

    public List<String> weekInAdvanceMap(String portCode) {

        List<String> returnList = new ArrayList<>();

        Date nextMonday = toDate(LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        Date nextFriday = toDate(LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        for (CargoManifest elem : getAllCargoManifest()) {
            if (elem.getCurrentLocalId().equals(portCode)) {
                if (Objects.requireNonNull(toDate(elem.getDate())).compareTo(nextMonday) >= 0
                        && Objects.requireNonNull(toDate(elem.getDate())).compareTo(nextFriday) <= 0) {
                    returnList.add("Operation Type:" + elem.getOperationType() + " Vehicle:" + elem.getVehicleId() +
                            " Date:" + elem.getDate());
                }
            }
        }

        return returnList;
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
        for (Ship elem : shipController.getAllShips()) {
            if (elem.getMMSI().equals(mmsi)) {
                shipId = elem.getId();
            }
        }
        String cargoRecon = null;
        for (CargoManifest cm : getAllCargoManifest()) {
            if (cm.getVehicleId().equals(shipId)
                    && Objects.requireNonNull(toDate(cm.getDate()))
                            .compareTo(Objects.requireNonNull(
                                    toDate(LocalDateTime.now()
                                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))) <= 0) {
                if (cargoRecon == null) {
                    cargoRecon = cm.getCargo_recon();
                } else if (Objects.requireNonNull(toDate(this.findCargoByRecon(cargoRecon).getDate())).compareTo(
                        Objects.requireNonNull(toDate(cm.getDate()))) < 0) {
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

        for (Ship elem : shipController.getAllShips()) {
            cargosList.clear();
            for (CargoManifest elemCargo : getAllCargoManifest()) {
                if (elemCargo.getVehicleId().equals(elem.getId())) {
                    cargosList.add(elemCargo);
                }

            }
            if (cargosList.size() != 0) {
                Utils.cargosOrderedByTime(cargosList);
                if (cargosList.get(0).getOperationType().equals("Unload")) {
                    cargosList.remove(0);
                }
                for (int i = 0; i < cargosList.size(); i += 2) {
                    if (capacityRate(elem.getMMSI(), cargosList.get(i).getCargo_recon()) < 66.00) {
                        if ((i + 1) < cargosList.size()) {
                            returnList.add(
                                    "Departure Local: " + cargosList.get(i).getCurrentLocalId() + " Departure date: "
                                            + cargosList.get(i).getDate()
                                            + " Arrival Local :" + cargosList.get(i).getNextLocal() + " Arrival Date: "
                                            + cargosList.get(i + 1).getDate());
                        }

                    }
                }
            }
        }

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
