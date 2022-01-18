package lapr.project.controller.model_controllers;

import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.cargoManifest.idb.ICargoManifest;
import lapr.project.model.operation.Operation;
import lapr.project.model.operation.idb.IOperation;
import lapr.project.model.vehicle.Vehicles;
import lapr.project.model.vehicle.idb.IVehicle;
import lapr.project.utils.Utils;

import java.text.SimpleDateFormat;
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

    public List<String> containers_to_offload(String mmsi) {
        String ship_id = null;
        for(Vehicles elem : vehicleDB.getAllShips()){
            if(elem.getVehicle_recon().equals(mmsi)){
                ship_id = elem.getId();
            }
        }
        List<String> containersOffload = new ArrayList<>();
        List<CargoManifest> shipCargos = new ArrayList<>();
        CargoManifest shipOffloadCargo = null;
        double date = (double) System.currentTimeMillis();
        for(CargoManifest elem : getAllCargoManifest()){

            if(elem.getVehicleId().equals(ship_id)){
                if(elem.getOperationType().equals("Unload")) {
                    Date dateElem = toDate(elem.getDate());
                    double dateElemMilli = (double)dateElem.getTime();
                    if(dateElemMilli > date) {
                        shipCargos.add(elem);
                    }
                }
            }
        }
        Utils.cargosOrderedByTime(shipCargos);
        shipOffloadCargo = shipCargos.get(0);
        for(Operation elem : operationDB.allOperations()){
            if(elem.getCargoManifestId().equals(shipOffloadCargo.getId())){
                containersOffload.add(elem.getContainerId());
            }
        }
        return containersOffload;
    }

    public List<String> containers_to_load(String mmsi) {
        String ship_id = null;
        for(Vehicles elem : vehicleDB.getAllShips()){
            if(elem.getVehicle_recon().equals(mmsi)){
                ship_id = elem.getId();
            }
        }
        List<String> containersToLoad = new ArrayList<>();
        List<CargoManifest> shipCargos = new ArrayList<>();
        CargoManifest shipToLoadCargo = null;
        double date = (double) System.currentTimeMillis();
        for(CargoManifest elem : getAllCargoManifest()){
            if(elem.getVehicleId().equals(ship_id)){
                if(elem.getOperationType().equals("Load")) {
                    Date dateElem = toDate(elem.getDate());
                    double dateElemMilli = (double)dateElem.getTime();
                    if(dateElemMilli > date) {
                        shipCargos.add(elem);
                    }
                }
            }
        }
        Utils.cargosOrderedByTime(shipCargos);
        shipToLoadCargo = shipCargos.get(0);
        for(Operation elem : operationDB.allOperations()){
            if(elem.getCargoManifestId().equals(shipToLoadCargo.getId())){
                containersToLoad.add(elem.getContainerId());
            }
        }
        return containersToLoad;
    }

    public List<String> a_cm(String ano,String ship_id) {
        List<String> cargosID = new ArrayList<>();
        List<String> returnList = new ArrayList<>();
        int contCargos = 0;
        int contOperations = 0;
        int i = 0;
        for(CargoManifest elem : getAllCargoManifest()){
            if(elem.getDate().contains(ano)){
                if(elem.getVehicleId().equals(ship_id)){
                    cargosID.add(elem.getId());
                    contCargos++;
                }
            }
        }
        returnList.add(String.valueOf(contCargos));

        for(Operation elem : this.operationDB.allOperations()){
            if(elem.getCargoManifestId().equals(cargosID.get(i))){
                contOperations++;
            }
        }
        returnList.add(String.valueOf(contOperations/contCargos));


        return returnList;
    }

}
