package lapr.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.controller.model_controllers.PortsAndWarehousesController;
import lapr.project.data.mocks.PortsAndWarehousesDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.model.locals.Locals;
import lapr.project.ui.PortsAndWarehousesUI;

class DataToKDTreeControllerTest {


    ShipDBMock shipDBMock = new ShipDBMock();
    PortsAndWarehousesDBMock portsAndWarehousesDBMock = new PortsAndWarehousesDBMock();

    PortsAndWarehousesController portsAndWarehousesController = new PortsAndWarehousesController(shipDBMock, portsAndWarehousesDBMock);

    DataToKDTreeController dataToKDTreeController = new DataToKDTreeController();

    PortsAndWarehousesUI portsAndWarehousesUI = new PortsAndWarehousesUI(portsAndWarehousesController);



    @BeforeEach
    void setup(){
        portsAndWarehousesUI.importPorts("Docs/Input/sports.csv");
        LinkedList<Locals> portsAndWarehouses = portsAndWarehousesController.getAllPortsAndWharehouse();
        dataToKDTreeController.populateTree(portsAndWarehouses); 
    }

    @Test
    void getPortsNodesTest(){
        assertEquals(22, dataToKDTreeController.getPortsNodes().size());
    }


}
