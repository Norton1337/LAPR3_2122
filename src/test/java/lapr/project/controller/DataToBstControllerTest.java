package lapr.project.controller;

import lapr.project.controller.model_controllers.GeneratorController;
import lapr.project.controller.model_controllers.ShipController;
import lapr.project.controller.model_controllers.ShipPositionDataController;
import lapr.project.controller.model_controllers.VehiclesController;
import lapr.project.data.VehiclesDB;
import lapr.project.data.mocks.*;
import lapr.project.model.helper_classes.ShipAndData;
import lapr.project.ui.ShipUI;
import org.junit.jupiter.api.Test;

import static lapr.project.utils.Utils.printList;
import static lapr.project.utils.Utils.readFromProp;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Objects;

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
        this.vehiclesDBMock =  new VehiclesDBMock();
        this.trucksDBMock =  new TrucksDBMock();
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
    }



    @Test
    void transformBeforeBST() {
        dataToBstController.transformBeforeBST(vehiclesController.getAllShips(), shipPositionDataController.getShipData());
        assertTrue(dataToBstController.getAllData().size() > 10);
    }

    @Test
    void populateBST() {
        dataToBstController.transformBeforeBST(vehiclesController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        assertTrue(dataToBstController.getShipBst().size() > 10);
    }

    @Test
    void getShipBst() {
        dataToBstController.transformBeforeBST(vehiclesController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        assertNotNull(dataToBstController.getShipBst());
    }

    @Test
    void getShipAndDataByMMSI() {
        dataToBstController.transformBeforeBST(vehiclesController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        ShipAndData dataByMMSI = dataToBstController.getShipDetails("636015178");

        if(Objects.equals(readFromProp("debug", "src/main/resources/application.properties"), "1"))System.out.println(dataByMMSI);

        assertEquals("636015178", dataByMMSI.getShip().getMMSI());
        assertEquals("AQUALEGACY", dataByMMSI.getShip().getShipName());

    }

    @Test
    void getShipAndDataByIMO() {
        dataToBstController.transformBeforeBST(vehiclesController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        ShipAndData dataByIMO = dataToBstController.getShipDetails("IMO9601833");

        assertEquals("IMO9601833", dataByIMO.getShip().getIMO());

    }

    @Test
    void getShipDataByCallSign() {
        dataToBstController.transformBeforeBST(vehiclesController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        ShipAndData dataByCallSign = dataToBstController.getShipDetails("A8ZC7");

        assertEquals("A8ZC7", dataByCallSign.getShip().getCallSign());

    }

    @Test
    void getShipDetails() {
        dataToBstController.transformBeforeBST(vehiclesController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        ShipAndData shipMMSI = dataToBstController.getShipDetails("211331640");
        assertEquals("SEOUL EXPRESS", shipMMSI.getShip().getShipName());

        ShipAndData shipIMO = dataToBstController.getShipDetails("IMO9193305");
        assertEquals("IMO9193305",shipIMO.getShip().getIMO() );

        ShipAndData shipCallSign = dataToBstController.getShipDetails("DHBN");
        assertEquals("DHBN",shipCallSign.getShip().getCallSign() );


    }
}