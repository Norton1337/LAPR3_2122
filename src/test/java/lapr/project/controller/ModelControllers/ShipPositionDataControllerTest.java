package lapr.project.controller.ModelControllers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import lapr.project.controller.model_controllers.VehiclesController;
import lapr.project.data.mocks.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.controller.model_controllers.GeneratorController;
import lapr.project.controller.model_controllers.ShipController;
import lapr.project.controller.model_controllers.ShipPositionDataController;
import lapr.project.ui.ShipUI;

class ShipPositionDataControllerTest {

    //DB
    VehiclesDBMock vehiclesDBMock = new VehiclesDBMock();
    TrucksDBMock trucksDBMock = new TrucksDBMock();
    ShipDBMock shipDBMock;
    GeneratorDBMock generatorDBMock;
    ShipPositionDataDBMock shipPositionDataDBMock;



    //CONTROLLERS
    VehiclesController vehiclesController;
    ShipController shipController;
    ShipPositionDataController shipPositionDataController;
    GeneratorController generatorController;
    ShipUI shipUI;

    @BeforeEach
    void setup(){
        this.shipDBMock = new ShipDBMock();
        this.generatorDBMock = new GeneratorDBMock();
        this.shipPositionDataDBMock = new ShipPositionDataDBMock();
        this.vehiclesController = new VehiclesController(vehiclesDBMock, shipDBMock, trucksDBMock);
        this.shipController = new ShipController(shipDBMock, generatorDBMock);
        this.shipPositionDataController = new ShipPositionDataController(shipDBMock, shipPositionDataDBMock);
        this.generatorController = new GeneratorController(shipDBMock, generatorDBMock);
        this.shipUI = new ShipUI(shipController, shipPositionDataController, generatorController, vehiclesController);

        this.shipUI.importShips("Docs/Input/sships.csv");
    }
    @Test
    void removeDataFromShipTest(){
        assertTrue(this.shipPositionDataController.removeDataFromShip(this.shipPositionDataController.getShipData().get(0)));

    }
}
