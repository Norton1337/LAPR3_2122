package lapr.project.controller.ModelControllers;

import lapr.project.controller.DataToBstController;
import lapr.project.controller.DataToKDTreeController;
import lapr.project.controller.ListAllShipsInfoController;
import lapr.project.controller.model_controllers.*;
import lapr.project.data.mocks.*;
import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.locals.Locals;
import lapr.project.model.ships.Ship;
import lapr.project.ui.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static lapr.project.utils.Utils.printList;
import static lapr.project.utils.Utils.printMap;
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
    ContainerDBMock containerDBMock = new ContainerDBMock();
    ClientDBMock clientDBMock = new ClientDBMock();
    LeasingDBMock leasingDBMock = new LeasingDBMock();
    UsersDBMock usersDBMock = new UsersDBMock();

    VehiclesController vehiclesController = new VehiclesController(vehiclesDBMock, shipDBMock, trucksDBMock);
    ShipController shipController = new ShipController(shipDBMock, generatorDBMock);
    ShipPositionDataController shipPositionDataController = new ShipPositionDataController(shipDBMock,
            shipPositionDataDBMock);
    GeneratorController generatorController = new GeneratorController(shipDBMock, generatorDBMock);
    LocalsController localsController = new LocalsController(countryDBMock, localsDBMock);
    CargoManifestController cargoManifestController = new CargoManifestController(vehiclesDBMock, cargoManifestDBMock,
            operationDBMock, shipController);
    OperationController operationController = new OperationController(operationDBMock,containerDBMock,localsController,
            cargoManifestController,shipController,vehiclesController);
    ContainerController containerController = new ContainerController(containerDBMock,cargoManifestController,
            operationController,vehiclesController,localsController,clientDBMock,leasingDBMock,usersDBMock);
    TruckController truckController = new TruckController(trucksDBMock);
    DataToBstController dataToBstController = new DataToBstController();
    ListAllShipsInfoController listAllShipsInfoController = new ListAllShipsInfoController();
    DataToKDTreeController dataToKDTreeController = new DataToKDTreeController();

    ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController, vehiclesController);
    CargoManifestUI cargoManifestUI = new CargoManifestUI(cargoManifestController);
    TruckUI truckUI = new TruckUI(vehiclesController);
    PortsAndWarehousesUI localsUI = new PortsAndWarehousesUI(localsController);
    ContainerUI containerUI = new ContainerUI(containerController);
    OperationsUI operationsUI = new OperationsUI(operationController);
    WarehouseUI warehouseUI = new WarehouseUI(localsController);


    public CargoManifestControllerTest() {
        truckUI.importTrucks("Docs/Input/truck.csv");
        shipUI.importShips("Docs/Input/bships.csv");
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();
        localsUI.importPorts("Docs/Input/bports.csv");
        warehouseUI.importWarehouses("Docs/Input/warehouses.csv");
        containerUI.importContainers("Docs/Input/container.csv");
        cargoManifestUI.importCargoManifest("Docs/Input/cargoManifest.csv");
        operationsUI.importOperations("Docs/Input/operations.csv");
        LinkedList<Locals> portsAndWarehouses = localsController.getAllLocals();
        dataToKDTreeController.populateTree(portsAndWarehouses);



    }

    @Test
    void addCargoManifestTest(){
        CargoManifest cm = new CargoManifest("nextLocal", "currentLocalId", "date", "operationType", "cargo_recon");
        assertTrue(cargoManifestController.addCargoManifest(cm, "78-28-VR"));
        CargoManifest test = cargoManifestDBMock.addCargoManifest(cm);
        assertEquals(test,cm);
    }

    @Test
    void removeCargoTest(){
        boolean test = cargoManifestController.removeCargo(cargoManifestController.findCargoByRecon("90nGT").getId());
        assertFalse(test);
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
    void containers_to_offloadTest(){
        String mmsi = "212180000";
        List<String> testList;
        testList = cargoManifestController.containersToLoadAndOffload(mmsi,"Unload");
        assertEquals(5, testList.size());

    }

    @Test
    void containers_to_loadTest(){
        String mmsi = "212180000";
        List<String> testList;
        testList = cargoManifestController.containersToLoadAndOffload(mmsi,"Load");
        assertEquals(5, testList.size());

    }

    @Test
    void aCmTest(){
        List<String> testList = cargoManifestController.aCm("2022","212180000");
        assertEquals(2,testList.size());

        List<String> testList2 = cargoManifestController.aCm("2021","212180000");
        assertEquals(1,testList2.size());

    }


    @Test
    void freeShipsTest(){
        List<Ship> testList = cargoManifestController.freeShips();

        assertEquals(131,testList.size());

    }


    @Test
    void capacityRateNow (){
        double value = cargoManifestController.capacityRateNow("366906610");

        assertEquals(0,value);
    }

    @Test
    void occupancyBelowThreshold(){
        List<String> test= cargoManifestController.occupancyBelowThresHold();

        assertEquals(2,test.size());
    }

    @Test
    void weekInAdvanceMapTest(){
        List<String> testList = new ArrayList<>();
        //testList.add("Operation Type:Load Vehicle:92ffd290-5127-4efe-addf-818936e8507e Date:2022-01-24 21:12:20");
        testList.add("Operation Type:Unload Vehicle:0cd75de5-f6dc-4877-b27f-38337cb4ffd1 Date:2022-01-25 22:00:05");

        assertEquals(testList.size(),cargoManifestController.weekInAdvanceMap("10358").size());

    }

    @Test
    void idleTimeShipsTest(){
        Map<Ship, String> mapTest = cargoManifestController.idleTimeShips();
        assertEquals(133,mapTest.size());

    }

}

