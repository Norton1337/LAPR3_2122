package lapr.project.controller.ModelControllers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.controller.model_controllers.GeneratorController;
import lapr.project.controller.model_controllers.ShipController;
import lapr.project.controller.model_controllers.ShipPositionDataController;
import lapr.project.data.mocks.GeneratorDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.data.mocks.ShipPositionDataDBMock;
import lapr.project.ui.ShipUI;

class ShipPositionDataControllerTest {

    //DB
    ShipDBMock shipDBMock;
    GeneratorDBMock generatorDBMock;
    ShipPositionDataDBMock shipPositionDataDBMock;

    //CONTROLLERS
    ShipController shipController;
    ShipPositionDataController shipPositionDataController;
    GeneratorController generatorController;
    ShipUI shipUI;

    @BeforeEach
    void setup(){
        this.shipDBMock = new ShipDBMock();
        this.generatorDBMock = new GeneratorDBMock();
        this.shipPositionDataDBMock = new ShipPositionDataDBMock();
        this.shipController = new ShipController(shipDBMock, generatorDBMock);
        this.shipPositionDataController = new ShipPositionDataController(shipDBMock, shipPositionDataDBMock);
        this.generatorController = new GeneratorController(shipDBMock, generatorDBMock);
        this.shipUI = new ShipUI(shipController, shipPositionDataController, generatorController);

        this.shipUI.importShips("Docs/Input/sships.csv");
    }
    @Test
    void removeDataFromShipTest(){
        assertTrue(this.shipPositionDataController.removeDataFromShip(this.shipPositionDataController.getShipData().get(0)));

    }
}
