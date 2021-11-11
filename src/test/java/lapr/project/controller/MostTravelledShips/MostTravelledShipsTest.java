package lapr.project.controller.MostTravelledShips;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.model.ShipPositionData.ShipPositonData;
import lapr.project.model.Ships.Ship;

class MostTravelledShipsTest {
    private List<Ship> listOfShips = new ArrayList<>();
    private List<Ship> listOfShips2 = new ArrayList<>();
    private List<Double> listOfDistances = new ArrayList<>();
    private List<Double> listOfDistances2 = new ArrayList<>();
    private List<Double> listOfSOG = new ArrayList<>();
    private List<Double> listOfSOG2 = new ArrayList<>();
    
    private TopShips topShipsOrdered;
    private TopShips topShipsUnordered;
    

    Ship ship = new Ship("211331640", "shipName", "IMO1234567", "callSign", 70, 295, 32, 13.6, 79.0);
    
    List<ShipPositonData> shipPositionDataList = new ArrayList<>();
    ShipAndData shipAndData = new ShipAndData(ship, shipPositionDataList);
    ShipPositonData spd = new ShipPositonData("31/12/2020 21:02", "33.0/-118.4523", 8.6, 42.6, 44, "?", "B");
    ShipPositonData spd1 = new ShipPositonData("31/12/2020 21:00", "33.5/-118.1295", 8.6, 42.6, 44, "?", "B");
    ShipPositonData spd2 = new ShipPositonData("31/12/2020 22:30", "34.7856/-118.4597", 8.6, 42.6, 44, "?", "B");

    Ship ship2 = new Ship("311331640", "shipName", "IMO1234567", "callSign", 60, 295, 32, 13.6, 79.0);
    List<ShipPositonData> shipPositionDataList2 = new ArrayList<>();
    ShipAndData shipAndData2 = new ShipAndData(ship2, shipPositionDataList2);
    ShipPositonData spd11 = new ShipPositonData("31/12/2020 21:02", "33.1/-118.4", 8.6, 42.6, 44, "?", "B");
    ShipPositonData spd12 = new ShipPositonData("31/12/2020 21:00", "33.50/-118.12", 8.6, 42.6, 44, "?", "B");
    ShipPositonData spd13 = new ShipPositonData("31/12/2020 22:30", "33.78/-118.45", 8.6, 42.6, 44, "?", "B");

    Ship ship3 = new Ship("411331640", "shipName", "IMO1234567", "callSign", 70, 295, 32, 13.6, 79.0);
    List<ShipPositonData> shipPositionDataList3 = new ArrayList<>();
    ShipAndData shipAndData3 = new ShipAndData(ship3, shipPositionDataList3);
    ShipPositonData spd21 = new ShipPositonData("31/12/2020 21:02", "33.68384/-118.4523", 8.6, 42.6, 44, "?", "B");
    ShipPositonData spd22 = new ShipPositonData("31/12/2020 21:00", "33.50465/-118.1295", 8.6, 42.6, 44, "?", "B");
    ShipPositonData spd23 = new ShipPositonData("31/12/2020 22:30", "35.785623/-118.4597", 8.6, 42.6, 44, "?", "B");
    
    List<ShipAndData> listShipAndData = new ArrayList<>();
    


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

        this.shipPositionDataList.add(spd);
        this.shipPositionDataList.add(spd1);
        this.shipPositionDataList.add(spd2);
        ship.setId("123");
        this.shipAndData.setShip(ship);
        this.shipAndData.setShipPositonData(shipPositionDataList);


        this.shipPositionDataList2.add(spd11);
        this.shipPositionDataList2.add(spd12);
        this.shipPositionDataList2.add(spd13);
        ship2.setId("456");
        this.shipAndData2.setShip(ship2);
        this.shipAndData2.setShipPositonData(shipPositionDataList2);


        this.shipPositionDataList3.add(spd21);
        this.shipPositionDataList3.add(spd22);
        this.shipPositionDataList3.add(spd23);
        ship3.setId("789");
        this.shipAndData3.setShip(ship3);
        this.shipAndData3.setShipPositonData(shipPositionDataList3);

        listShipAndData.add(shipAndData);
        listShipAndData.add(shipAndData2);
        listShipAndData.add(shipAndData3);
        

    }


    @Test
    void getTopNShipsTest1(){
        MostTravelledShips mts = new MostTravelledShips();

        List <ShipAndData> shipList2 = new ArrayList<>();
        shipList2.add(listShipAndData.get(2));
        shipList2.add(listShipAndData.get(0));
        shipList2.add(listShipAndData.get(1));

        TopShips ts = mts.getTopNShips(listShipAndData, 3);

 
        assertEquals(shipList2.get(0).getShip(), ts.getListOfShip().get(0));
        assertEquals(shipList2.get(1).getShip(), ts.getListOfShip().get(1));
        assertEquals(shipList2.get(2).getShip(), ts.getListOfShip().get(2));


    }

    @Test
    void getTopNShipsTest2() throws ParseException{
        MostTravelledShips mts = new MostTravelledShips();

        List <ShipAndData> shipList2 = new ArrayList<>();
        shipList2.add(listShipAndData.get(2));
        shipList2.add(listShipAndData.get(0));
        shipList2.add(listShipAndData.get(1));

        TopShips ts = mts.getTopNShips(listShipAndData, 3, "31/12/2020 21:30", "31/12/2020 22:00");

 
        assertEquals(0, ts.getListOfDistances().get(0));


    }

    @Test
    void getTopNShipsTest3() throws ParseException{
        MostTravelledShips mts = new MostTravelledShips();

        List <ShipAndData> shipList2 = new ArrayList<>();
        shipList2.add(listShipAndData.get(2));
        shipList2.add(listShipAndData.get(0));
        shipList2.add(listShipAndData.get(1));


        TopShips ts = mts.getTopNShips(listShipAndData, 2, "31/12/2020 21:00", "31/12/2020 22:00", 70);

 
        assertEquals(2, ts.getListOfDistances().size());



    }



    @Test
    void orderListsTest(){
        MostTravelledShips mts = new MostTravelledShips();

        assertEquals(topShipsOrdered.getListOfDistances(), mts.orderLists(topShipsUnordered, 0, 3, 0, 0).getListOfDistances());
        assertEquals(topShipsOrdered.getListOfSOG(), mts.orderLists(topShipsUnordered, 0, 3, 0, 0).getListOfSOG());
        
        assertEquals(topShipsOrdered.getListOfShip().get(0).getMMSI(), mts.orderLists(topShipsUnordered, 0, 3, 0, 0).getListOfShip().get(0).getMMSI());
        assertEquals(topShipsOrdered.getListOfShip().get(1).getMMSI(), mts.orderLists(topShipsUnordered, 0, 3, 0, 0).getListOfShip().get(1).getMMSI());
        assertEquals(topShipsOrdered.getListOfShip().get(2).getMMSI(), mts.orderLists(topShipsUnordered, 0, 3, 0, 0).getListOfShip().get(2).getMMSI());
    }

    @Test
    void getTotalPerShipTest(){
        MostTravelledShips mts = new MostTravelledShips();
        List<ShipPositonData> posList = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("###.###");
        posList.add(listShipAndData.get(0).getShipPositonData().get(0));
        posList.add(listShipAndData.get(0).getShipPositonData().get(1));
        posList.add(listShipAndData.get(0).getShipPositonData().get(2)); 
        
        assertEquals(df.format(209.329), df.format(mts.getTotalPerShip(posList)));
    }

    
}
