package lapr.project.controller;

import lapr.project.controller.ModelControllers.GeneratorController;
import lapr.project.controller.ModelControllers.ShipController;
import lapr.project.controller.ModelControllers.ShipPositionDataController;
import lapr.project.data.mocks.GeneratorDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.data.mocks.ShipPositionDataDBMock;
import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.ui.ShipUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static lapr.project.utils.Utils.readFromProp;
import static org.junit.jupiter.api.Assertions.*;

class DataToBstControllerTest {


    //DB
    ShipDBMock shipDBMock = new ShipDBMock();
    GeneratorDBMock generatorDBMock = new GeneratorDBMock();
    ShipPositionDataDBMock shipPositionDataDBMock = new ShipPositionDataDBMock();

    //CONTROLLERS
    ShipController shipController = new ShipController(shipDBMock, generatorDBMock);
    ShipPositionDataController shipPositionDataController = new ShipPositionDataController(shipDBMock, shipPositionDataDBMock);
    GeneratorController generatorController = new GeneratorController(shipDBMock, generatorDBMock);
    DataToBstController dataToBstController = new DataToBstController();
    ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController);


    @BeforeEach
    void beforeAll() {
        shipUI.importShips("Docs/Input/bships.csv");
    }



    @Test
    void transformBeforeBST() {
        //ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController);

        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        assertTrue(dataToBstController.getAllData().size() > 10);
    }

    @Test
    void populateBST() {
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        assertTrue(dataToBstController.getShipBst().size() > 10);
    }

    @Test
    void getShipBst() {
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        assertNotNull(dataToBstController.getShipBst());
    }

    @Test
    void getShipAndDataByMMSI() {
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        ShipAndData dataByMMSI = dataToBstController.getShipDetails("636015178");

        if(readFromProp("debug").equals("1"))System.out.println(dataByMMSI);

        assertEquals(dataByMMSI.getShip().getMMSI(), "636015178");
        assertEquals(dataByMMSI.getShip().getShipName(), "AQUALEGACY");

    }

    @Test
    void getShipAndDataByIMO() {
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        ShipAndData dataByIMO = dataToBstController.getShipDetails("IMO9601833");

        assertEquals(dataByIMO.getShip().getIMO(), "IMO9601833");

    }

    @Test
    void getShipDataByCallSign() {
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        ShipAndData dataByCallSign = dataToBstController.getShipDetails("A8ZC7");

        assertEquals(dataByCallSign.getShip().getCallSign(), "A8ZC7");

    }

    @Test
    void getShipDetails() {
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        ShipAndData shipMMSI = dataToBstController.getShipDetails("211331640");
        assertEquals(shipMMSI.getShip().getShipName(), "SEOUL EXPRESS");

        ShipAndData shipIMO = dataToBstController.getShipDetails("IMO9193305");
        assertEquals(shipIMO.getShip().getIMO(),"IMO9193305" );

        ShipAndData shipCallSign = dataToBstController.getShipDetails("DHBN");
        assertEquals(shipCallSign.getShip().getCallSign(),"DHBN" );


    }
}