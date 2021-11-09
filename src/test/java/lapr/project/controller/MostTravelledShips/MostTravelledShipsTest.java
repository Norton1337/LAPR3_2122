package lapr.project.controller.MostTravelledShips;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import lapr.project.model.Ships.Ship;
import lapr.project.model.ShipPositionData.ShipPositonData;

class MostTravelledShipsTest {
    private List<Ship> listOfShips = new ArrayList<>();
    private List<Ship> listOfShips2 = new ArrayList<>();
    private List<Double> listOfDistances = new ArrayList<>();
    private List<Double> listOfDistances2 = new ArrayList<>();
    private List<Double> listOfSOG = new ArrayList<>();
    private List<Double> listOfSOG2 = new ArrayList<>();
    
    private TopShips topShipsOrdered;
    private TopShips topShipsUnordered;

    @BeforeEach
    void startUp(){
        listOfShips.add(new Ship("211331642", "shipName3", "IMO3", "callSign3", 13, 5, 5, 62.5, 90.5));
        listOfShips.add(new Ship("211331641", "shipName2", "IMO2", "callSign2", 12, 5, 5, 61.5, 80.6));
        listOfShips.add(new Ship("211331640", "shipName1", "IMO1", "callSign1", 11, 5, 5, 60.5, 70.8));
        
        listOfShips2.add(new Ship("211331642", "shipName3", "IMO3", "callSign3", 13, 5, 5, 62.5, 90.5));
        listOfShips2.add(new Ship("211331640", "shipName1", "IMO1", "callSign1", 11, 5, 5, 60.5, 70.8));
        listOfShips2.add(new Ship("211331641", "shipName2", "IMO2", "callSign2", 12, 5, 5, 61.5, 80.6));

        listOfDistances.add(300.5);
        listOfDistances.add(200.5);
        listOfDistances.add(100.5);

        listOfDistances2.add(300.5);
        listOfDistances2.add(100.5);
        listOfDistances2.add(200.5);

        listOfSOG.add(30.8);
        listOfSOG.add(20.8);
        listOfSOG.add(10.8);
        
        listOfSOG2.add(30.8);
        listOfSOG2.add(10.8);
        listOfSOG2.add(20.8);
        

        topShipsOrdered = new TopShips(listOfShips, listOfDistances, listOfSOG);
        topShipsUnordered = new TopShips(listOfShips2, listOfDistances2, listOfSOG2);
    }


    @Test
    void getTopNShipsVoid(){
        
    }

    @Test
    void orderLists(){
        MostTravelledShips mts = new MostTravelledShips();

        assertEquals(topShipsOrdered.getListOfDistances(), mts.orderLists(topShipsUnordered, 0, 3, 0, 0).getListOfDistances());
        assertEquals(topShipsOrdered.getListOfSOG(), mts.orderLists(topShipsUnordered, 0, 3, 0, 0).getListOfSOG());
        
        assertEquals(topShipsOrdered.getListOfShip().get(0).getMMSI(), mts.orderLists(topShipsUnordered, 0, 3, 0, 0).getListOfShip().get(0).getMMSI());
        assertEquals(topShipsOrdered.getListOfShip().get(1).getMMSI(), mts.orderLists(topShipsUnordered, 0, 3, 0, 0).getListOfShip().get(1).getMMSI());
        assertEquals(topShipsOrdered.getListOfShip().get(2).getMMSI(), mts.orderLists(topShipsUnordered, 0, 3, 0, 0).getListOfShip().get(2).getMMSI());
    }

    @Test
    void getTotalPerShipTest(){
        List<ShipPositonData> posList = new ArrayList<>();
        
    }

    
}
