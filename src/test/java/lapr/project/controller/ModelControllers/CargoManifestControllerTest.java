package lapr.project.controller.ModelControllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.controller.DataToBstController;
import lapr.project.controller.DataToKDTreeController;
import lapr.project.controller.ListAllShipsInfoController;
import lapr.project.controller.model_controllers.CargoManifestController;
import lapr.project.controller.model_controllers.GeneratorController;
import lapr.project.controller.model_controllers.LocalsController;
import lapr.project.controller.model_controllers.ShipController;
import lapr.project.controller.model_controllers.ShipPositionDataController;
import lapr.project.controller.model_controllers.TruckController;
import lapr.project.controller.model_controllers.VehiclesController;
import lapr.project.data.mocks.CargoManifestDBMock;
import lapr.project.data.mocks.CountryDBMock;
import lapr.project.data.mocks.GeneratorDBMock;
import lapr.project.data.mocks.LocalsDBMock;
import lapr.project.data.mocks.OperationDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.data.mocks.ShipPositionDataDBMock;
import lapr.project.data.mocks.TrucksDBMock;
import lapr.project.data.mocks.VehiclesDBMock;
import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.locals.Locals;
import lapr.project.ui.CargoManifestUI;
import lapr.project.ui.ShipUI;
import lapr.project.ui.TruckUI;

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



    @BeforeEach
    void setup(){
        //SETUP


        truckUI.importTrucks("Docs/Input/truck.csv");
        shipUI.importShips("Docs/Input/bships.csv");
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();


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
        testList = cargoManifestController.containers_to_offload(mmsi);

        assertNull(testList);
    }

    @Test
    void containers_to_load(){
        String mmsi = "212180000";
        List<String> testList;
        testList = cargoManifestController.containers_to_load(mmsi);

        assertNull(testList);
    }

}

