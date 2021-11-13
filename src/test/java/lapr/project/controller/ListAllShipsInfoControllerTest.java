package lapr.project.controller;

import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.model.ShipPositionData.ShipPositonData;
import lapr.project.model.Ships.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListAllShipsInfoControllerTest {




    List<Ship> listOfShips = new ArrayList<>();
    List<ShipPositonData> listOfShipPositionData = new ArrayList<>();
    List<ShipPositonData> listOfShipPositionData2 = new ArrayList<>();
    List<ShipAndData> shipAndDataList = new ArrayList<>();





    @BeforeEach
    void startUp(){

        Ship ship1 = new Ship("110950637", "firstShip", "IMO7510259","D34R", 40, 459,735, 425.6, 8 );
        Ship ship2 = new Ship("210950637", "secondShip", "IMO7510260","D34S", 50, 460,785, 321.5, 10);
        //Ship ship3 = new Ship("310950637", "thirdShip", "IMO7510261","D34T", 60, 461,801, 567.2, 12);

        listOfShips.add(ship1);
        listOfShips.add(ship2);
        //listOfShips.add(ship3);

        // Creates position data for ship1
        ShipPositonData shipPositonDataShip11 = new ShipPositonData("31/12/2020", "20.48627, -31.22163", 12.5, 17.4, 385, "p", "A");
        ShipPositonData shipPositonDataShip12 = new ShipPositonData("29/12/2020", "30.48630, -40.22140", 12.5, 17.5, 387, "a", "A");
        ShipPositonData shipPositonDataShip13 = new ShipPositonData("27/12/2020", "40.48633, -31.22150", 12.5, 17.6, 388, "b", "B");
        // adds position data for ship1 to list
        listOfShipPositionData.add(shipPositonDataShip11);
        listOfShipPositionData.add(shipPositonDataShip12);
        listOfShipPositionData.add(shipPositonDataShip13);



        ShipPositonData shipPositonDataShip21 = new ShipPositonData("31/12/2020", "50.48627, -11.22163", 12.5, 15.4, 385, "p", "A");
        ShipPositonData shipPositonDataShip22 = new ShipPositonData("29/12/2020", "60.48630, -20.22140", 18.5, 10.5, 400, "a", "A");
        ShipPositonData shipPositonDataShip23 = new ShipPositonData("27/12/2020", "70.48633, -61.22150", 14.2, 21.6, 246, "b", "B");

        listOfShipPositionData2.add(shipPositonDataShip21);
        listOfShipPositionData2.add(shipPositonDataShip22);
        listOfShipPositionData2.add(shipPositonDataShip23);



        ShipAndData shipAndDataOBJ = new ShipAndData(ship1, listOfShipPositionData); // ShipAndData do primeiro barco com a lista de posições
        ShipAndData shipAndDataOBJ2 = new ShipAndData(ship2, listOfShipPositionData2);
        shipAndDataList.add(shipAndDataOBJ); // adicioina barco1 a list
        shipAndDataList.add(shipAndDataOBJ2);


    }



    @Test
    void shipLogTest(){


        ListAllShipsInfoController listAllShipsInfoController = new ListAllShipsInfoController();
        //listAllShipsInfoController.shipLog(shipAndDataList);

        System.out.println(shipAndDataList.size());

        assertEquals(shipAndDataList.get(0).getShip().getMMSI(), "110950637");
        assertEquals(shipAndDataList.get(1).getShip().getMMSI(),"210950637" );

    }









}