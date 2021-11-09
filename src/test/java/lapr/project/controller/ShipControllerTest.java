package lapr.project.controller;

import lapr.project.controller.ModelControllers.ShipController;
import lapr.project.data.mocks.GeneratorDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.model.Ships.Generator;
import lapr.project.model.Ships.IDB.IGeneratorDB;
import lapr.project.model.Ships.IDB.IShipsDB;
import lapr.project.model.Ships.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShipControllerTest {

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
    void CreateShip(){
        Generator gen = new Generator(1,0.0);

        Ship newShip = new Ship("210950000", "VARAMO", "IMO9395044", "C4SQ2", 70,
                166, 25, 9.5, 0.0);

        addShipController.addShip(newShip);

        System.out.println(iShipsDB.getAllShips());


        assertTrue(iShipsDB.getAllShips().contains(newShip));


    }

}