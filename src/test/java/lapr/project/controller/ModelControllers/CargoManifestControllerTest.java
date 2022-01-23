package lapr.project.controller.ModelControllers;

import lapr.project.controller.DataToBstController;
import lapr.project.controller.DataToKDTreeController;
import lapr.project.controller.ListAllShipsInfoController;
import lapr.project.controller.model_controllers.*;
import lapr.project.data.mocks.*;
import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.locals.Locals;
import lapr.project.ui.CargoManifestUI;
import lapr.project.ui.PortsAndWarehousesUI;
import lapr.project.ui.ShipUI;
import lapr.project.ui.TruckUI;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CargoManifestControllerTest {

    //DB
    VehiclesDBMock vehiclesDBMock = new VehiclesDBMock();
    TrucksDBMock trucksDBMock = new TrucksDBMock();
    ShipDBMock shipDBMock = new ShipDBMock();
    GeneratorDBMock generatorDBMock = new GeneratorDBMock();
    ShipPositionDataDBMock shipPositionDataDBMock = new ShipPositionDataDBMock();
    LocalsDBMock localsDBMock = new LocalsDBMock();
    CountryDBMock countryDBMock = new CountryDBMock();

    OperationDBMock operationDBMock = new OperationDBMock();
    CargoManifestDBMock cargoManifestDBMock = new CargoManifestDBMock();




    VehiclesController vehiclesController = new VehiclesController(vehiclesDBMock, shipDBMock, trucksDBMock);
    ShipController shipController = new ShipController(shipDBMock, generatorDBMock);
    ShipPositionDataController shipPositionDataController = new ShipPositionDataController(shipDBMock,
            shipPositionDataDBMock);
    GeneratorController generatorController = new GeneratorController(shipDBMock, generatorDBMock);
    LocalsController localsController = new LocalsController(countryDBMock, localsDBMock);
    CargoManifestController cargoManifestController = new CargoManifestController(vehiclesDBMock, cargoManifestDBMock,
            operationDBMock, shipController);

    TruckController truckController = new TruckController(trucksDBMock);
    DataToBstController dataToBstController = new DataToBstController();
    ListAllShipsInfoController listAllShipsInfoController = new ListAllShipsInfoController();
    DataToKDTreeController dataToKDTreeController = new DataToKDTreeController();

    ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController, vehiclesController);
    CargoManifestUI cargoManifestUI = new CargoManifestUI(cargoManifestController);
    TruckUI truckUI = new TruckUI(vehiclesController);
    PortsAndWarehousesUI localsUI = new PortsAndWarehousesUI(localsController);



    public CargoManifestControllerTest() {
        truckUI.importTrucks("Docs/Input/truck.csv");
        shipUI.importShips("Docs/Input/bships.csv");
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();
        localsUI.importPorts("Docs/Input/bports.csv");

        LinkedList<Locals> portsAndWarehouses = localsController.getAllLocals();
        dataToKDTreeController.populateTree(portsAndWarehouses);


        cargoManifestUI.importCargoManifest("Docs/Input/cargoManifest.csv");

    }

    @Test
    void addCargoManifestTest(){
        CargoManifest cm = new CargoManifest("nextLocal", "currentLocalId", "date", "operationType", "cargo_recon");
        assertTrue(cargoManifestController.addCargoManifest(cm, "78-28-VR"));
        CargoManifest test = cargoManifestDBMock.addCargoManifest(cm);
        assertEquals(test,cm);
    }

    @Test
    void getAllCargoManifestTest(){
        assertEquals(36, cargoManifestController.getAllCargoManifest().size());
    }

    @Test
    void findCargoByIdTest(){
        CargoManifest cm = new CargoManifest("nextLocal", "currentLocalId", "date", "operationType", "cargo_recon");
        cargoManifestController.addCargoManifest(cm, "vehicleID");
        assertEquals(cm,cargoManifestController.findCargoById(cm.getId()));
    }

    @Test
    void findCargoByReconTest(){
        CargoManifest cm = new CargoManifest("nextLocal", "currentLocalId", "date", "operationType", "cargo_recon");
        cargoManifestController.addCargoManifest(cm, "cargo_recon");
        assertEquals(cm,cargoManifestController.findCargoById(cm.getId()));
    }

    @Test
    void containers_to_offload(){
        String mmsi = "211331640";
        List<String> testList;
        testList = cargoManifestController.containersToOffload(mmsi);

        assertEquals(0, testList.size());
    }

    @Test
    void containers_to_load(){
        String mmsi = "212180000";
        List<String> testList;
        testList = cargoManifestController.containersToLoad(mmsi);

        assertEquals(0, testList.size());
    }

    @Test
    void weekInAdvanceMapTest(){
        List<String> testList = new ArrayList<>();
        testList.add("Operation Type:Load Vehicle:92ffd290-5127-4efe-addf-818936e8507e Date:2022-01-24 21:12:20");
        testList.add("Operation Type:Unload Vehicle:0cd75de5-f6dc-4877-b27f-38337cb4ffd1 Date:2022-01-25 22:00:05");

        //assertEquals(testList.size(),cargoManifestController.weekInAdvanceMap("10358").size());

    }



}

