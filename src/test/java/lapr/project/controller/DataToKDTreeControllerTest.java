package lapr.project.controller;

import lapr.project.controller.model_controllers.LocalsController;
import lapr.project.data.mocks.CountryDBMock;
import lapr.project.data.mocks.LocalsDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.model.locals.Locals;
import lapr.project.ui.PortsAndWarehousesUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataToKDTreeControllerTest {


    CountryDBMock countryDBMock = new CountryDBMock();
    ShipDBMock shipDBMock = new ShipDBMock();
    LocalsDBMock portsAndWarehousesDBMock = new LocalsDBMock();

    LocalsController portsAndWarehousesController = new LocalsController(countryDBMock, portsAndWarehousesDBMock);

    DataToKDTreeController dataToKDTreeController = new DataToKDTreeController();

    PortsAndWarehousesUI portsAndWarehousesUI = new PortsAndWarehousesUI(portsAndWarehousesController);


    @BeforeEach
    void setup() {
        portsAndWarehousesUI.importPorts("Docs/Input/sports.csv");
        LinkedList<Locals> portsAndWarehouses = portsAndWarehousesController.getAllLocals();
        dataToKDTreeController.populateTree(portsAndWarehouses);
    }

    @Test
    void getPortsNodesTest() {
        assertEquals(22, dataToKDTreeController.getPortsNodes().size());
    }


}
