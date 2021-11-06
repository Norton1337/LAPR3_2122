package lapr.project.controller;

import lapr.project.data.mocks.GeneratorDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.model.Ships.Generator;
import lapr.project.model.Ships.IDB.IGeneratorDB;
import lapr.project.model.Ships.IDB.IShipsDB;
import lapr.project.model.Ships.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddShipControllerTest {

    private IShipsDB iShipsDB;
    private IGeneratorDB iGeneratorDB;
    private AddShipController addShipController;

    @BeforeEach
    public void setUp() {
        iShipsDB = new ShipDBMock();
        iGeneratorDB = new GeneratorDBMock();
        addShipController = new AddShipController(iShipsDB, iGeneratorDB);
    }

    @Test
    void CreateShip(){
        Generator gen = new Generator(1,0,0);
        Ship newShip = new Ship(1,"210950000", "VARAMO", "IMO9395044", "C4SQ2", 70,
                166, 25, 9.5, "0", 0, gen);

        addShipController.addShip(newShip, gen);

        System.out.println(iShipsDB.getAllShips());


        assertTrue(iShipsDB.getAllShips().contains(newShip));


    }

}