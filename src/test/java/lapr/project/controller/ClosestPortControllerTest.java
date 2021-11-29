package lapr.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.controller.model_controllers.GeneratorController;
import lapr.project.controller.model_controllers.PortsAndWarehousesController;
import lapr.project.controller.model_controllers.ShipController;
import lapr.project.controller.model_controllers.ShipPositionDataController;
import lapr.project.data.mocks.GeneratorDBMock;
import lapr.project.data.mocks.PortsAndWarehousesDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.data.mocks.ShipPositionDataDBMock;
import lapr.project.model.locals.Locals;
import lapr.project.ui.PortsAndWarehousesUI;
import lapr.project.ui.ShipUI;

class ClosestPortControllerTest {
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
    DataToKDTreeController dataToKDTreeController = new DataToKDTreeController();


    //LEITURA DE FICHEIRO
    ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController);
    PortsAndWarehousesUI portsAndWarehousesUI = new PortsAndWarehousesUI(portsAndWarehousesController);
    

    
    @BeforeEach
    void setup(){
        shipUI.importShips("Docs/Input/bships.csv");
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();
        
        portsAndWarehousesUI.importPorts("Docs/Input/sports.csv");
        LinkedList<Locals> portsAndWarehouses = portsAndWarehousesController.getAllPortsAndWharehouse();
        dataToKDTreeController.populateTree(portsAndWarehouses);   
    }

    @Test
    void getPortTest(){
        ClosestPortController cpc = new ClosestPortController();
        Locals receivedPort = cpc.getPort(dataToBstController, dataToKDTreeController, "DHBN", "31/12/2020 05:36");
        Locals receivedPort2 = cpc.getPort(dataToBstController, dataToKDTreeController, "DHBN", "31/01/2020 05:36");

        assertEquals(dataToKDTreeController.getPortsTree().getAllElements().get(6), receivedPort);
        assertEquals(null, receivedPort2);
    }
}
