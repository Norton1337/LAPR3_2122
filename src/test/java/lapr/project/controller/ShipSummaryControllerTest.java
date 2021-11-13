/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.model.ShipPositionData.ShipPositonData;
import lapr.project.model.Ships.Ship;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author gonca
 */
public class ShipSummaryControllerTest {

    List<Ship> listOfShips = new ArrayList<>();
    List<ShipPositonData> listOfShipPositionData = new ArrayList<>();
    List<ShipPositonData> listOfShipPositionData2 = new ArrayList<>();
    List<ShipAndData> shipAndDataList = new ArrayList<>();
    Ship ship1 = new Ship("110950637", "firstShip", "IMO7510259", "D34R", 40, 459, 735, 425.6, 8);
    Ship ship2 = new Ship("210950637", "secondShip", "IMO7510260", "D34S", 50, 460, 785, 321.5, 10);
    //Ship ship3 = new Ship("310950637", "thirdShip", "IMO7510261","D34T", 60, 461,801, 567.2, 12);

    // Creates position data for ship1
    ShipPositonData shipPositonDataShip11 = new ShipPositonData("31/12/2020 00:05", "20.48627, -31.22163", 12.5, 17.4, 385, "p", "A");
    ShipPositonData shipPositonDataShip12 = new ShipPositonData("31/12/2020 00:03", "30.48630, -40.22140", 12.8, 17.5, 387, "a", "A");
    ShipPositonData shipPositonDataShip13 = new ShipPositonData("31/12/2020 00:01", "40.48633, -31.22150", 12.5, 17.6, 388, "b", "B");

    ShipAndData shipAndDataOBJ = new ShipAndData(ship1, listOfShipPositionData);

    @BeforeEach
    void startUp() {
        listOfShips.add(ship1);
        listOfShips.add(ship2);
        //listOfShips.add(ship3);
        // adds position data for ship1 to list
        listOfShipPositionData.add(shipPositonDataShip11);
        listOfShipPositionData.add(shipPositonDataShip12);
        listOfShipPositionData.add(shipPositonDataShip13);
        listOfShipPositionData2.add(shipPositonDataShip13);
        listOfShipPositionData2.add(shipPositonDataShip12);
        listOfShipPositionData2.add(shipPositonDataShip11);
        shipAndDataList.add(shipAndDataOBJ);
    }

//    @Test
//    void CheckStartUp() {
//        System.out.println(ship1.toString());
//        System.out.println(ship2.toString());
//        System.out.println(listOfShipPositionData);
//    }
    @Test
    void getShipPositionDataOrderedByTimeTest() {
        System.out.println("getShipPositionDataOrderedByTimeTest");
        ShipSummaryController summaryController = new ShipSummaryController(shipAndDataOBJ);
        assertEquals(listOfShipPositionData2, summaryController.getShipPositionDataOrderedByTime(listOfShipPositionData));
    }

    @Test
    void getStartDateTimeTest() {
        System.out.println("getStartDateTimeTest");
        assertEquals("31/12/2020 00:01", listOfShipPositionData2.get(0).getBaseDateTime());
    }

    @Test
    void getEndDateTimeTest() {
        System.out.println("getStartDateTimeTest");
        assertEquals("31/12/2020 00:05", listOfShipPositionData2.get(2).getBaseDateTime());
    }

    @Test
    void getTotalTimeTest() throws ParseException {
        System.out.println("getTotalTimeTest");
        ShipSummaryController summaryController = new ShipSummaryController(shipAndDataOBJ);

        assertEquals("Days:0\tHours:0\tMinutes:4", summaryController.getTotalTime());
    }

    @Test
    void getTotalMovementsTest() {
        System.out.println("getTotalMovementsTest");
        ShipSummaryController summaryController = new ShipSummaryController(shipAndDataOBJ);

        assertEquals(3, summaryController.getTotalMovements());
    }

    @Test
    void getMaxSOGTest() {
        System.out.println("getMaxSOGTest");
        ShipSummaryController summaryController = new ShipSummaryController(shipAndDataOBJ);

        assertEquals(12.8, summaryController.getMaxSOG());
    }

    @Test
    void getMeanSOGTest() {
        System.out.println("getMeanSOGTest");
        ShipSummaryController summaryController = new ShipSummaryController(shipAndDataOBJ);

        assertEquals(12.6, summaryController.getMeanSOG());
    }

    @Test
    void getMaxCOGTest() {
        System.out.println("getMaxCOGTest");
        ShipSummaryController summaryController = new ShipSummaryController(shipAndDataOBJ);

        assertEquals(17.6, summaryController.getMaxCOG());
    }

    @Test
    void getMeanCOGTest() {
        System.out.println("getMeanCOGTest");
        ShipSummaryController summaryController = new ShipSummaryController(shipAndDataOBJ);

        assertEquals(17.5, summaryController.getMeanCOG());
    }

    @Test
    void getDepartureTest() {
        System.out.println("getMeanCOGTest");
        ShipSummaryController summaryController = new ShipSummaryController(shipAndDataOBJ);
        List<ShipPositonData> list = summaryController.getShipPositionDataOrderedByTime(listOfShipPositionData);
        assertEquals("40.48633, -31.22150", summaryController.getDeparture());
    }

    @Test
    void getArrivalTest() {
        System.out.println("getMeanCOGTest");
        ShipSummaryController summaryController = new ShipSummaryController(shipAndDataOBJ);
        List<ShipPositonData> list = summaryController.getShipPositionDataOrderedByTime(listOfShipPositionData);
        assertEquals("20.48627, -31.22163", summaryController.getArrival());
    }

//    @Test
//    void getTravelledDistanceTest() {
//        MostTravelledShipsController mostTravelledShips = new MostTravelledShipsController();
//        ShipSummaryController summaryController = new ShipSummaryController(shipAndDataOBJ);
//        listOfShipPositionData = summaryController.getShipPositionDataOrderedByTime(shipAndDataOBJ.getShipPositonData());
//        
//        double wantedDistance = mostTravelledShips.getTotalPerShip(listOfShipPositionData);
//        double givenDistance = summaryController.getTravelledDistance();
//        assertEquals(wantedDistance, givenDistance);
//    }
    
    @Test
    void getDeltaDistanceTest(){
        MostTravelledShipsController mostTravelledShips = new MostTravelledShipsController();
        ShipSummaryController summaryController = new ShipSummaryController(shipAndDataOBJ);
        listOfShipPositionData = summaryController.getShipPositionDataOrderedByTime(shipAndDataOBJ.getShipPositonData());
        
        double wantedDistance = mostTravelledShips.getDeltaDistance(listOfShipPositionData);
        double givenDistance = summaryController.getDeltaDistance();
        assertEquals(wantedDistance, givenDistance);
    }
}
