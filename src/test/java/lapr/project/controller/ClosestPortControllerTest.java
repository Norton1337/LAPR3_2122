package lapr.project.controller;

import lapr.project.controller.model_controllers.*;
import lapr.project.data.mocks.*;
import lapr.project.model.locals.Locals;
import lapr.project.ui.CountryUI;
import lapr.project.ui.PortsAndWarehousesUI;
import lapr.project.ui.ShipUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static lapr.project.utils.Utils.printList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClosestPortControllerTest {
    //DB
    VehiclesDBMock vehiclesDBMock = new VehiclesDBMock();
    TrucksDBMock trucksDBMock = new TrucksDBMock();
    ShipDBMock shipDBMock = new ShipDBMock();
    GeneratorDBMock generatorDBMock = new GeneratorDBMock();
    ShipPositionDataDBMock shipPositionDataDBMock = new ShipPositionDataDBMock();
    LocalsDBMock portsAndWarehousesDBMock = new LocalsDBMock();
    CountryDBMock countryDBMock = new CountryDBMock();
    BordersDBMock bordersDBMock = new BordersDBMock();

    //CONTROLLERS DO MODEL
    VehiclesController vehiclesController = new VehiclesController(vehiclesDBMock, shipDBMock, trucksDBMock);
    ShipController shipController = new ShipController(shipDBMock, generatorDBMock);
    ShipPositionDataController shipPositionDataController = new ShipPositionDataController(shipDBMock, shipPositionDataDBMock);
    GeneratorController generatorController = new GeneratorController(shipDBMock, generatorDBMock);
    LocalsController portsAndWarehousesController = new LocalsController(countryDBMock, portsAndWarehousesDBMock);

    //CONTROLLERS
    DataToBstController dataToBstController = new DataToBstController();
    ListAllShipsInfoController listAllShipsInfoController = new ListAllShipsInfoController();
    DataToKDTreeController dataToKDTreeController = new DataToKDTreeController();
    CountryController countryController = new CountryController(countryDBMock, bordersDBMock, portsAndWarehousesDBMock);


    //LEITURA DE FICHEIRO
    ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController, vehiclesController);
    PortsAndWarehousesUI portsAndWarehousesUI = new PortsAndWarehousesUI(portsAndWarehousesController);

    CountryUI countryUI = new CountryUI(countryController);


    @BeforeEach
    void setup() {
        countryUI.importCountriesAndBorders("Docs/Input/countries.csv", "Docs/Input/borders.csv");

        shipUI.importShips("Docs/Input/bships.csv");
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        portsAndWarehousesUI.importPorts("Docs/Input/sports.csv");
        LinkedList<Locals> portsAndWarehouses = portsAndWarehousesController.getAllPorts();
        portsAndWarehouses.add(portsAndWarehouses.get(6));
        dataToKDTreeController.populateTree(portsAndWarehouses);

    }

    @Test
    void getPortTest() {
        ClosestPortController cpc = new ClosestPortController();
        Locals receivedPort = cpc.getPort(dataToBstController, dataToKDTreeController, "DHBN", "31/12/2020 05:36");
        Locals receivedPort2 = cpc.getPort(dataToBstController, dataToKDTreeController, "DHBN", "31/01/2020 05:36");
        assertEquals(dataToKDTreeController.getPortsTree().getAllElements().get(5), receivedPort);
        assertNull(receivedPort2);
    }


    @Test
    void test() {
        printList(portsAndWarehousesDBMock.getAllLocals());
    }


}
