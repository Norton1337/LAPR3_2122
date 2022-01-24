package lapr.project.controller.ModelControllers;

import lapr.project.cargoship_stories.CargoManifestController;
import lapr.project.cargoship_stories.ContainerController;
import lapr.project.cargoship_stories.OperationController;
import lapr.project.controller.DataToBstController;
import lapr.project.controller.DataToKDTreeController;
import lapr.project.controller.ListAllShipsInfoController;
import lapr.project.controller.ToMatrixController;
import lapr.project.controller.model_controllers.*;
import lapr.project.data.mocks.*;
import lapr.project.model.containers.Container;
import lapr.project.model.locals.Locals;
import lapr.project.ui.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static lapr.project.utils.Utils.printList;
import static lapr.project.utils.Utils.readFromProp;
import static org.junit.jupiter.api.Assertions.*;

public class ContainerControllerTest {

    // DB
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

    // CONTROLLERS DO MODEL
    VehiclesController vehiclesController = new VehiclesController(vehiclesDBMock, shipDBMock,
            trucksDBMock);
    ShipController shipController = new ShipController(shipDBMock, generatorDBMock);
    ShipPositionDataController shipPositionDataController = new ShipPositionDataController(shipDBMock,
            shipPositionDataDBMock);
    GeneratorController generatorController = new GeneratorController(shipDBMock, generatorDBMock);
    LocalsController localsController = new LocalsController(countryDBMock, localsDBMock);
    CargoManifestController cargoManifestController = new CargoManifestController(vehiclesDBMock,
            cargoManifestDBMock, operationDBMock, shipController);
    OperationController operationController = new OperationController(operationDBMock, containerDBMock,
            localsController, cargoManifestController, shipController, vehiclesController);
    ContainerController containerController = new ContainerController(containerDBMock,
            cargoManifestController,
            operationController, vehiclesController, localsController, clientDBMock, leasingDBMock,
            usersDBMock);
    TruckController truckController = new TruckController(trucksDBMock);
    UserController userController = new UserController(usersDBMock);
    ClientController clientController = new ClientController(clientDBMock, usersDBMock);
    LeasingController leasingController = new LeasingController(leasingDBMock, containerDBMock,
            clientDBMock,
            usersDBMock, clientController);

    // CONTROLLERS
    DataToBstController dataToBstController = new DataToBstController();
    ListAllShipsInfoController listAllShipsInfoController = new ListAllShipsInfoController();
    DataToKDTreeController dataToKDTreeController = new DataToKDTreeController();
    CountryController countryController = new CountryController(countryDBMock, bordersDBMock, localsDBMock);
    SeadistController seadistController = new SeadistController(localsDBMock, seadistDBMock);
    ToMatrixController matrixController = new ToMatrixController(localsDBMock, seadistDBMock, countryDBMock,
            bordersDBMock);

    // LEITURA DE FICHEIRO
    ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController,
            vehiclesController);
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


    public ContainerControllerTest() {
        // SETUP
        countryUI.importCountriesAndBorders("Docs/Input/countries.csv", "Docs/Input/borders.csv");

        truckUI.importTrucks("Docs/Input/truck.csv");
        shipUI.importShips("Docs/Input/bships.csv");
        dataToBstController.transformBeforeBST(shipController.getAllShips(),
                shipPositionDataController.getShipData());
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
    void addContainerTest(){
        Container newContainer = new Container(2223,5,"ASD",500,
                500,1000,300,"Certificates","Repair",
                "Refrigerated");
        boolean check = containerController.addContainer(newContainer);

        assertTrue(containerDBMock.getAllContainers().contains(newContainer));
    }

    @Test
    void getAllContainersTest(){
        Container newContainer = new Container(2223,5,"ASD",500,
                500,1000,300,"Certificates","Repair",
                "Refrigerated");
        containerController.addContainer(newContainer);
        assertEquals(containerDBMock.getAllContainers().size(),containerController.getAllContainers().size());
    }

    @Test
    void findContainerByNumberTest(){
        Container newContainer = new Container(2223,5,"ASD",500,
                500,1000,300,"Certificates","Repair",
                "Refrigerated");
        containerController.addContainer(newContainer);

        Container result = containerController.findContainerByNumber("2223");
        assertEquals(2223,result.getContainerNumber());
    }

    @Test
    void test() {

        if (Objects.equals(readFromProp("debug", "src/main/resources/application.properties"), "1")){
            printList(containerDBMock.getAllContainers());
        }

    }


    @Test
    void containerRouteTest(){

        /*
          For Valid User and Container Number
         */


        List<String> resultValid = containerController.containerRoute("client123", "2345");

        assertNotNull(resultValid);
        assertTrue(resultValid.size() > 0);



         /*
          For Invalid User
         */

        List<String> resultInValid = containerController.containerRoute("client1234", "2345");
        assertNull(resultInValid);



        /*
            For Invalid Container
         */

        List<String> resultConInv = containerController.containerRoute("client123", "23456");
        assertNull(resultConInv);


    }


    @Test
    void whereIsContainerTest(){

        /*
          For Valid port
         */
        String resultValid = containerController.whereIsContainer("2336");
        String resultCode = resultValid.split("localCode=")[1].replace("'","")
                .split(",")[0].trim();


        Locals localsRes = localsController.getLocalWithPortId(resultCode);
        assertNotNull(localsRes);

        assertEquals("Aarhus", localsRes.getName());
        assertEquals("56.15,10.21666667", localsRes.getCoordinates());


        /*
          For vehicle
         */

        //TODO
        //String resultVehicle = containerController.whereIsContainer("2336");
        //assertNotNull(resultVehicle);

        /*
          For Invalid port
         */
        String resultInvalid = containerController.whereIsContainer("666");
        assertNull(resultInvalid);

    }


}
