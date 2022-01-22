package lapr.project.controller.ModelControllers;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.controller.DataToBstController;
import lapr.project.controller.DataToKDTreeController;
import lapr.project.controller.ListAllShipsInfoController;
import lapr.project.controller.ToMatrixController;
import lapr.project.controller.model_controllers.CargoManifestController;
import lapr.project.controller.model_controllers.ClientController;
import lapr.project.controller.model_controllers.ContainerController;
import lapr.project.controller.model_controllers.CountryController;
import lapr.project.controller.model_controllers.GeneratorController;
import lapr.project.controller.model_controllers.LeasingController;
import lapr.project.controller.model_controllers.LocalsController;
import lapr.project.controller.model_controllers.OperationController;
import lapr.project.controller.model_controllers.SeadistController;
import lapr.project.controller.model_controllers.ShipController;
import lapr.project.controller.model_controllers.ShipPositionDataController;
import lapr.project.controller.model_controllers.TruckController;
import lapr.project.controller.model_controllers.UserController;
import lapr.project.controller.model_controllers.VehiclesController;
import lapr.project.data.mocks.BordersDBMock;
import lapr.project.data.mocks.CargoManifestDBMock;
import lapr.project.data.mocks.ClientDBMock;
import lapr.project.data.mocks.ContainerDBMock;
import lapr.project.data.mocks.CountryDBMock;
import lapr.project.data.mocks.GeneratorDBMock;
import lapr.project.data.mocks.LeasingDBMock;
import lapr.project.data.mocks.LocalsDBMock;
import lapr.project.data.mocks.OperationDBMock;
import lapr.project.data.mocks.SeadistDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.data.mocks.ShipPositionDataDBMock;
import lapr.project.data.mocks.TrucksDBMock;
import lapr.project.data.mocks.UsersDBMock;
import lapr.project.data.mocks.VehiclesDBMock;
import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.locals.Locals;
import lapr.project.ui.CargoManifestUI;
import lapr.project.ui.ClientUI;
import lapr.project.ui.ContainerUI;
import lapr.project.ui.CountryUI;
import lapr.project.ui.LeasingUI;
import lapr.project.ui.OperationsUI;
import lapr.project.ui.PortsAndWarehousesUI;
import lapr.project.ui.SeadistUI;
import lapr.project.ui.ShipUI;
import lapr.project.ui.TruckUI;
import lapr.project.ui.UsersUI;
import lapr.project.ui.WarehouseUI;

class CargoManifestControllerTest {

    //DB
    VehiclesDBMock vehiclesDBMock = new VehiclesDBMock();
    TrucksDBMock trucksDBMock = new TrucksDBMock();
    ShipDBMock shipDBMock = new ShipDBMock();
    GeneratorDBMock generatorDBMock = new GeneratorDBMock();
    ShipPositionDataDBMock shipPositionDataDBMock = new ShipPositionDataDBMock();
    LocalsDBMock localsDBMock = new LocalsDBMock();
    CountryDBMock countryDBMock = new CountryDBMock();
    BordersDBMock bordersDBMock = new BordersDBMock();
    SeadistDBMock seadistDBMock = new SeadistDBMock();
    OperationDBMock operationDBMock = new OperationDBMock();
    CargoManifestDBMock cargoManifestDBMock = new CargoManifestDBMock();
    ContainerDBMock containerDBMock = new ContainerDBMock();
    UsersDBMock usersDBMock = new UsersDBMock();
    ClientDBMock clientDBMock = new ClientDBMock();
    LeasingDBMock leasingDBMock = new LeasingDBMock();


    //CONTROLLERS DO MODEL
    VehiclesController vehiclesController = new VehiclesController(vehiclesDBMock, shipDBMock, trucksDBMock);
    ShipController shipController = new ShipController(shipDBMock, generatorDBMock);
    ShipPositionDataController shipPositionDataController = new ShipPositionDataController(shipDBMock,
            shipPositionDataDBMock);
    GeneratorController generatorController = new GeneratorController(shipDBMock, generatorDBMock);
    LocalsController localsController = new LocalsController(countryDBMock, localsDBMock);
    CargoManifestController cargoManifestController = new CargoManifestController(vehiclesDBMock, cargoManifestDBMock,
            operationDBMock);
    OperationController operationController = new OperationController(operationDBMock,containerDBMock, localsController,
            cargoManifestController, shipController, vehiclesController);
    ContainerController containerController = new ContainerController(containerDBMock, cargoManifestController,
            operationController, vehiclesController, localsController, leasingDBMock);
    TruckController truckController = new TruckController(trucksDBMock);
    UserController userController = new UserController(usersDBMock);
    ClientController clientController = new ClientController(clientDBMock, usersDBMock);
    LeasingController leasingController = new LeasingController(leasingDBMock, containerDBMock, clientDBMock,
            usersDBMock, clientController);

    //CONTROLLERS
    DataToBstController dataToBstController = new DataToBstController();
    ListAllShipsInfoController listAllShipsInfoController = new ListAllShipsInfoController();
    DataToKDTreeController dataToKDTreeController = new DataToKDTreeController();
    CountryController countryController = new CountryController(countryDBMock, bordersDBMock, localsDBMock);
    SeadistController seadistController = new SeadistController(localsDBMock, seadistDBMock);
    ToMatrixController matrixController = new ToMatrixController(localsDBMock, seadistDBMock, countryDBMock, bordersDBMock);


    //LEITURA DE FICHEIRO
    ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController, vehiclesController);
    PortsAndWarehousesUI portsAndWarehousesUI = new PortsAndWarehousesUI(localsController);
    CountryUI countryUI = new CountryUI(countryController);
    SeadistUI seadistUI = new SeadistUI(seadistController);
    ContainerUI containerUI = new ContainerUI(containerController);
    WarehouseUI warehouseUI = new WarehouseUI(localsController);
    CargoManifestUI cargoManifestUI = new CargoManifestUI(cargoManifestController);
    OperationsUI operationsUI = new OperationsUI(operationController);
    TruckUI truckUI = new TruckUI(vehiclesController);
    UsersUI usersUI = new UsersUI(userController);
    ClientUI clientUI = new ClientUI(clientController);
    LeasingUI leasingUI = new LeasingUI(leasingController);


    @BeforeEach
    void setup(){
        //SETUP

        countryUI.importCountriesAndBorders("Docs/Input/countries.csv", "Docs/Input/borders.csv");

        truckUI.importTrucks("Docs/Input/truck.csv");
        shipUI.importShips("Docs/Input/bships.csv");
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        portsAndWarehousesUI.importPorts("Docs/Input/bports.csv");

        LinkedList<Locals> portsAndWarehouses = localsController.getAllLocals();
        dataToKDTreeController.populateTree(portsAndWarehouses);

        seadistUI.importSeadist("Docs/Input/seadists.csv");

        containerUI.importContainers("Docs/Input/container.csv");
        warehouseUI.importWarehouses("Docs/Input/warehouses.csv");
        cargoManifestUI.importCargoManifest("Docs/Input/cargoManifest.csv");
        operationsUI.importOperations("Docs/Input/operations.csv");

        usersUI.importUsers("Docs/Input/users.csv");
        clientUI.importClients("Docs/Input/clients.csv");
        leasingUI.importLeasingCon("Docs/Input/leasing.csv");
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

