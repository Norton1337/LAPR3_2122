package lapr.project.controller.model_controllers;

import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.cargoManifest.idb.ICargoManifest;
import lapr.project.model.operation.Operation;
import lapr.project.model.operation.idb.IOperation;
import lapr.project.model.vehicle.Vehicles;
import lapr.project.model.vehicle.idb.IVehicle;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> containers_to_offload(String ship_id) {
        List<String> containersOffload = new ArrayList<>();
        List<CargoManifest> shipCargos = new ArrayList<>();
        CargoManifest shipOffloadCargo = null;
        for(CargoManifest elem : getAllCargoManifest()){
            if(elem.getVehicleId().equals(ship_id)){
                if(elem.getOperationType().equals("Unload")) {
                    shipCargos.add(elem);
                }
            }
        }

        for(CargoManifest elem : shipCargos){


        }

        return null;
    }

    public List<String> containers_to_load(String ship_id) {
        List<String> containersToLoad = new ArrayList<>();
        List<CargoManifest> shipCargos = new ArrayList<>();
        CargoManifest shipLoadCargo = null;
        for(CargoManifest elem : getAllCargoManifest()){
            if(elem.getVehicleId().equals(ship_id)){
                if(elem.getOperationType().equals("Load")) {
                    shipCargos.add(elem);
                }
            }
        }

        for(CargoManifest elem : shipCargos){


        }

        return null;
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
