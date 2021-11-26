package lapr.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.controller.ModelControllers.GeneratorController;
import lapr.project.controller.ModelControllers.PortsAndWarehousesController;
import lapr.project.controller.ModelControllers.ShipController;
import lapr.project.controller.ModelControllers.ShipPositionDataController;
import lapr.project.data.mocks.GeneratorDBMock;
import lapr.project.data.mocks.PortsAndWarehousesDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.data.mocks.ShipPositionDataDBMock;
import lapr.project.model.PortsAndWarehouses.PortsAndWarehouses;
import lapr.project.ui.ShipUI;

public class ClosestPortControllerTest {
    //DB
    ShipDBMock shipDBMock = new ShipDBMock();
    GeneratorDBMock generatorDBMock = new GeneratorDBMock();
    ShipPositionDataDBMock shipPositionDataDBMock = new ShipPositionDataDBMock();
    PortsAndWarehousesDBMock portsAndWarehousesDBMock = new PortsAndWarehousesDBMock();

    //CONTROLLERS DO MODEL
    ShipController shipController = new ShipController(shipDBMock, generatorDBMock);
    ShipPositionDataController shipPositionDataController = new ShipPositionDataController(shipDBMock, shipPositionDataDBMock);
    GeneratorController generatorController = new GeneratorController(shipDBMock, generatorDBMock);
    PortsAndWarehousesController portsAndWarehousesController = new PortsAndWarehousesController(shipDBMock, portsAndWarehousesDBMock);

    //CONTROLLERS
    DataToBstController dataToBstController = new DataToBstController();
    ListAllShipsInfoController listAllShipsInfoController = new ListAllShipsInfoController();


    //LEITURA DE FICHEIRO
    ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController);
    
    @BeforeEach
    void setup(){
        shipUI.importShips("Docs/Input/bships.csv");
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();
        
        portsAndWarehousesController.addPortAndWarehouse(new PortsAndWarehouses("Europe","Cyprus",10136,
                "Larnaca","34.91666667,33.65"));
        portsAndWarehousesController.addPortAndWarehouse(new PortsAndWarehouses("Europe","Malta",10138,
                "Marsaxlokk","35.84194,14.54306"));
        portsAndWarehousesController.addPortAndWarehouse(new PortsAndWarehouses("Europe","Denmark",10358,
                "Aarhus","56.15,10.21666667"));
    }

    @Test
    void getPortTest(){
        ClosestPortController cpc = new ClosestPortController();
        PortsAndWarehouses receivedPort = cpc.getPort(dataToBstController, portsAndWarehousesController, "DHBN", "31/12/2020 05:36");
        PortsAndWarehouses receivedPort2 = cpc.getPort(dataToBstController, portsAndWarehousesController, "DHBN", "31/01/2020 05:36");

        assertEquals(portsAndWarehousesController.getAllPortsAndWharehouse().getLast(), receivedPort);
        assertEquals(null, receivedPort2);
    }
}
