package lapr.project.controller;

import lapr.project.controller.model_controllers.GeneratorController;
import lapr.project.controller.model_controllers.ShipController;
import lapr.project.controller.model_controllers.ShipPositionDataController;
import lapr.project.controller.model_controllers.VehiclesController;
import lapr.project.data.mocks.*;
import lapr.project.model.helper_classes.ShipAndData;
import lapr.project.ui.ShipUI;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static lapr.project.utils.Utils.readFromProp;
import static org.junit.jupiter.api.Assertions.*;

class DataToBstControllerTest {

    //DB
    VehiclesDBMock vehiclesDBMock;
    TrucksDBMock trucksDBMock;
    ShipDBMock shipDBMock;
    GeneratorDBMock generatorDBMock;
    ShipPositionDataDBMock shipPositionDataDBMock;

    //CONTROLLERS
    VehiclesController vehiclesController;
    ShipController shipController;
    ShipPositionDataController shipPositionDataController;
    GeneratorController generatorController;
    DataToBstController dataToBstController;
    ShipUI shipUI;


    public DataToBstControllerTest() {
        this.vehiclesDBMock = new VehiclesDBMock();
        this.trucksDBMock = new TrucksDBMock();
        this.shipDBMock = new ShipDBMock();
        this.generatorDBMock = new GeneratorDBMock();
        this.shipPositionDataDBMock = new ShipPositionDataDBMock();
        this.vehiclesController = new VehiclesController(vehiclesDBMock, shipDBMock, trucksDBMock);
        this.shipController = new ShipController(shipDBMock, generatorDBMock);
        this.shipPositionDataController = new ShipPositionDataController(shipDBMock, shipPositionDataDBMock);
        this.generatorController = new GeneratorController(shipDBMock, generatorDBMock);
        this.dataToBstController = new DataToBstController();
        this.shipUI = new ShipUI(shipController, shipPositionDataController, generatorController, vehiclesController);

        this.shipUI.importShips("Docs/Input/bships.csv");

        this.dataToBstController.transformBeforeBST(vehiclesController.getAllShips(), shipPositionDataController.getShipData());
        this.dataToBstController.populateBST();
    }


    @Test
    void transformBeforeBST() {
        assertTrue(dataToBstController.getAllData().size() > 10);
    }

    @Test
    void populateBST() {
        assertTrue(dataToBstController.getShipBst().size() > 10);
    }

    @Test
    void getShipBst() {
        assertNotNull(dataToBstController.getShipBst());
    }

    @Test
    void getShipAndDataByMMSI() {
        ShipAndData dataByMMSI = dataToBstController.getShipDetails("636015178");

        if (Objects.equals(readFromProp("debug", "src/main/resources/application.properties"), "1"))
            System.out.println(dataByMMSI);

        assertEquals("636015178", dataByMMSI.getShip().getMMSI());
        assertEquals("AQUALEGACY", dataByMMSI.getShip().getShipName());

    }

    @Test
    void getShipAndDataByIMO() {
        ;

        ShipAndData dataByIMO = dataToBstController.getShipDetails("IMO9601833");

        assertEquals("IMO9601833", dataByIMO.getShip().getIMO());

    }

    @Test
    void getShipDataByCallSign() {
        ShipAndData dataByCallSign = dataToBstController.getShipDetails("A8ZC7");

        assertEquals("A8ZC7", dataByCallSign.getShip().getCallSign());

    }

    @Test
    void getShipDetails() {
        ShipAndData shipMMSI = dataToBstController.getShipDetails("211331640");
        assertEquals("SEOUL EXPRESS", shipMMSI.getShip().getShipName());

        ShipAndData shipIMO = dataToBstController.getShipDetails("IMO9193305");
        assertEquals("IMO9193305", shipIMO.getShip().getIMO());

        ShipAndData shipCallSign = dataToBstController.getShipDetails("DHBN");
        assertEquals("DHBN", shipCallSign.getShip().getCallSign());


    }
}