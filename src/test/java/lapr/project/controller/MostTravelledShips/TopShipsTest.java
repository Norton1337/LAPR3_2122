package lapr.project.controller.MostTravelledShips;

import lapr.project.controller.helper_classes.TopShips;
import lapr.project.model.ships.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TopShipsTest {
    private List<Ship> listOfShips = new ArrayList<>();
    private List<Double> listOfDistances = new ArrayList<>();
    private List<Double> listOfSOG = new ArrayList<>();

    private TopShips topShips;

    @BeforeEach
    void startUp() {
        listOfShips.add(new Ship("211331640", "shipName1", "IMO1", "callSign1", 11, 5, 5, 60.5, 70.8));
        listOfShips.add(new Ship("211331641", "shipName2", "IMO2", "callSign2", 12, 5, 5, 61.5, 80.6));
        listOfShips.add(new Ship("211331642", "shipName3", "IMO3", "callSign3", 13, 5, 5, 62.5, 90.5));

        listOfDistances.add(100.5);
        listOfDistances.add(200.5);
        listOfDistances.add(300.5);

        listOfSOG.add(10.8);
        listOfSOG.add(20.8);
        listOfSOG.add(30.8);

        topShips = new TopShips(listOfShips, listOfDistances, listOfSOG);
    }

    @Test
    void getListOfShipTest() {
        assertEquals(listOfShips, topShips.getListOfShip());
    }

    @Test
    void getListOfDistancesTest() {
        assertEquals(listOfDistances, topShips.getListOfDistances());
    }

    @Test
    void getListOfSOGTest() {
        assertEquals(listOfSOG, topShips.getListOfSOG());
    }


}
