package lapr.project.controller.ModelControllers;

import lapr.project.controller.model_controllers.ShipController;
import lapr.project.data.mocks.GeneratorDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.model.ships.Generator;
import lapr.project.model.ships.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShipControllerTest {

    //DB
    ShipDBMock shipDBMock = new ShipDBMock();
    GeneratorDBMock generatorDBMock = new GeneratorDBMock();

    //Controller
    ShipController shipController = new ShipController(shipDBMock, generatorDBMock);

    @Test
    void addShip() {
        Ship newShip = new Ship("210950000", "VARAMO", "IMO9395044", "C4SQ2", 70,
                166, 25, 9.5, 0.0);
        shipController.addShip(newShip);
        assertTrue(shipDBMock.getAllShips().contains(newShip));
    }

    @Test
    void getAllShips() {
        Ship newShip = new Ship("210950000", "VARAMO", "IMO9395044", "C4SQ2", 70,
                166, 25, 9.5, 0.0);

        shipController.addShip(newShip);
        shipController.getAllShips();
        assertEquals(shipDBMock.getAllShips().size(), shipController.getAllShips().size());
    }

    @Test
    void getAllGeneratorFromShip() {
        Ship newShip = new Ship("210950000", "VARAMO", "IMO9395044", "C4SQ2", 70,
                166, 25, 9.5, 0.0);
        generatorDBMock.addGenerator(newShip.getId(), new Generator(2, 2, newShip.getId()));
        assertTrue(generatorDBMock.getAllGenerator().size() > 0);
    }

    @Test
    void findShipByMMSI() {
        Ship newShip = new Ship("210956699", "VARAMO", "IMO9395044", "C4SQ2", 70,
                166, 25, 9.5, 0.0);
        shipController.addShip(newShip);

        Ship result = shipController.findShipByMMSI("210956699");
        assertEquals("C4SQ2", result.getCallSign());
    }
}