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

import static lapr.project.utils.Utils.readFromProp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
        Ship newShip = new Ship("210950000", "VARAMO", "IMO9395044", "C4SQ2", 70,
                166, 25, 9.5, 0.0);
        newShip.getId();
        addShipController.addShip(newShip);
        if(readFromProp("debug","src/main/resources/application.properties").equals("1"))System.out.println(iShipsDB.getAllShips());


        assertTrue(iShipsDB.getAllShips().contains(newShip));


    }

    @Test
    void getAllGeneratorFromShipTest(){
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