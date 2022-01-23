package lapr.project.cargoship_stories;

import lapr.project.cargoship_stories.CargoManifestController;
import lapr.project.controller.model_controllers.*;
import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.client.Client;
import lapr.project.model.client.idb.IClient;
import lapr.project.model.containers.Container;
import lapr.project.model.containers.idb.IContainerDB;
import lapr.project.model.leasing.Leasing;
import lapr.project.model.leasing.idb.ILeasing;
import lapr.project.model.locals.Locals;
import lapr.project.model.operation.Operation;
import lapr.project.model.users.Users;
import lapr.project.model.users.idb.IUsers;
import lapr.project.model.vehicle.Vehicles;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static lapr.project.utils.Utils.*;

public class ContainerController {

    private final IContainerDB containerDB;
    private final CargoManifestController cargoManifestController;
    private final OperationController operationController;
    private final VehiclesController vehiclesController;
    private final LocalsController localsController;
    private final IClient clientDB;
    private final ILeasing leasingDB;
    private final IUsers usersDB;

    public ContainerController(IContainerDB containerDB, CargoManifestController cargoManifestController,
                               OperationController operationController, VehiclesController vehiclesController,
                               LocalsController localsController, IClient clientDB, ILeasing leasingDB, IUsers usersDB) {
        this.containerDB = containerDB;
        this.cargoManifestController = cargoManifestController;
        this.operationController = operationController;
        this.vehiclesController = vehiclesController;
        this.localsController = localsController;
        this.clientDB = clientDB;
        this.leasingDB = leasingDB;
        this.usersDB = usersDB;
    }

    public boolean addContainer(Container newContainer) {
        containerDB.addContainer(newContainer);
        return true;
    }

    public String whereIsContainer(String containerNumber){
        CargoManifest cargoManifest = null;
        Container container = null;
        for(Container elem : containerDB.getAllContainers()){
            if(elem.getContainerNumber() == toInt(containerNumber)){
                container = elem;
            }
        }
        for(Operation elem : operationController.getAllOperations()){
            if(container == null){
                return null;
            }
            if(elem.getContainerId().equals(container.getId())){
                if(cargoManifest == null) {
                    cargoManifest = cargoManifestController.findCargoById(elem.getCargoManifestId());
                }else if(Objects.requireNonNull(toDate(cargoManifest.getDate())).compareTo
                        (toDate(cargoManifestController.findCargoById(elem.getCargoManifestId()).getDate())) < 0){
                    cargoManifest = cargoManifestController.findCargoById(elem.getCargoManifestId());
                }
            }
        }

        assert cargoManifest != null;
        if(cargoManifest.getOperationType().equals("Load")){
            Vehicles vehicles = vehiclesController.getVehicle(cargoManifest.getVehicleId());
            return vehicles.getVehicle_recon();
        }else if(cargoManifest.getOperationType().equals("Unload")){
            Locals local = localsController.getLocalWithPortId(cargoManifest.getCurrentLocalId());
            return local.toStringPort();
        }

        return null;
    }



    public List<String> containerRoute(String username, String containerNumber){
        UserController userController = new UserController(usersDB);
        Container container = findContainerByNumber(containerNumber);
        Leasing leasing = null;
        List<String> containerRoute = new ArrayList<>();
        List<CargoManifest> cargoList = new ArrayList<>();
        ClientController clientController = new ClientController(clientDB,usersDB);
        Users user = userController.getUserByUsername(username);

        /**
         * User valido
         */

        if(user != null){
            Client client = clientController.getClientWithUserId(user.getId());


            for(Leasing elem : leasingDB.getAllLeasing()){
                if(elem.getClientId().equals(client.getId())){
                    if(container == null){
                        return null;
                    }
                    if(elem.getContainerId().equals(container.getId())){
                        leasing = elem;
                    }
                }
            }

            if(leasing == null){
                containerRoute.add("Container is not valid");
                return containerRoute;
            }

            for(Operation elem : operationController.getAllOperations()){
                if(elem.getContainerId().equals(container.getId())
                        && Objects.requireNonNull(toDate(cargoManifestController.findCargoById(elem.getCargoManifestId()).getDate()))
                        .compareTo(toDate(leasing.getStartDate())) >= 0
                        && Objects.requireNonNull(toDate(cargoManifestController.findCargoById(elem.getCargoManifestId()).getDate()))
                        .compareTo(toDate(leasing.getEndDate())) <= 0){
                    cargoList.add(cargoManifestController.findCargoById(elem.getCargoManifestId()));
                }
            }

            cargosOrderedByTime(cargoList);
            if(cargoList.get(0).getOperationType().equals("Unload")){
                cargoList.remove(0);
            }
            for(int i = 0 ; i < cargoList.size(); i+=2){
                if((i+1) < cargoList.size()){
                    containerRoute.add(" Departure Local : " + cargoList.get(i).getCurrentLocalId() + "\n Departure date : "
                            + cargoList.get(i).getDate()
                            + "\n Arrival Local : " + cargoList.get(i).getNextLocal() + "\n Arrival Date : "
                            + cargoList.get(i+1).getDate() +
                            "\n Transport Method : " + vehiclesController.getVehicle(cargoList.get(i).getVehicleId()).getType());
                }
            }

            if(containerRoute.size() == 0){
                containerRoute.add("Container has no voyages yet.");
            }
            return containerRoute;
        }

        return null;

    }

    public List<Container> getAllContainers() {
        return containerDB.getAllContainers();
    }


    public Container findContainerByNumber(String number) {
        return containerDB.getContainerByNumber(number);
    }

}
