package lapr.project.controller;

import lapr.project.controller.ModelControllers.GeneratorController;
import lapr.project.controller.ModelControllers.ShipController;
import lapr.project.controller.ModelControllers.ShipPositionDataController;
import lapr.project.data.mocks.GeneratorDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.data.mocks.ShipPositionDataDBMock;
import lapr.project.ui.ShipUI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        //TODO ANA
    }

    @Test
    void getShipAndDataByIMO() {
        //TODO ANA
    }

    @Test
    void getShipDataByCallSign() {
        //TODO ANA
    }

    @Test
    void getShipDetails() {
        //TODO ANA
    }
}