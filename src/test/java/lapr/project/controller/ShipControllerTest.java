package lapr.project.controller;

import lapr.project.controller.model_controllers.ShipController;
import lapr.project.data.mocks.GeneratorDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.model.ships.Generator;
import lapr.project.model.ships.Ship;
import lapr.project.model.ships.idb.IGeneratorDB;
import lapr.project.model.ships.idb.IShipsDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static lapr.project.utils.Utils.readFromProp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShipControllerTest {

    private IShipsDB iShipsDB;
    private IGeneratorDB iGeneratorDB;
    private ShipController addShipController;

    @BeforeEach
    public void setUp() {
        iShipsDB = new ShipDBMock();
        iGeneratorDB = new GeneratorDBMock();
        addShipController = new ShipController(iShipsDB, iGeneratorDB);
    }

    @Test
    void CreateShip() throws IOException {
        Ship newShip = new Ship("210950000", "VARAMO", "IMO9395044", "C4SQ2", 70,
                166, 25, 9.5, 0.0);
        addShipController.addShip(newShip);
        if (readFromProp("debug", "src/main/resources/application.properties").equals("1"))
            System.out.println(iShipsDB.getAllShips());


        assertTrue(iShipsDB.getAllShips().contains(newShip));


    }

    @Test
    void getAllGeneratorFromShipTest() {
        ShipController ship = new ShipController(iShipsDB, iGeneratorDB);

        Ship newShip = new Ship("210950000", "VARAMO", "IMO9395044", "C4SQ2", 70,
                166, 25, 9.5, 0.0);
        newShip.setId("test");
        List<Generator> allGeneratorFromShip = new ArrayList<>();

        assertEquals(allGeneratorFromShip, ship.getAllGeneratorFromShip("test"));
        assertEquals(allGeneratorFromShip, ship.getAllGeneratorFromShip(null));
        assertEquals(allGeneratorFromShip, ship.getAllGeneratorFromShip("wrongID"));
    }
}