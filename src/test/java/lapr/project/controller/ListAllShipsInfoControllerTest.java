package lapr.project.controller;

import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.model.ShipPositionData.ShipPositonData;
import lapr.project.model.Ships.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ListAllShipsInfoControllerTest {


    List<Ship> listOfShips = new ArrayList<>();

    List<ShipPositonData> listOfShipPositionData = new ArrayList<>();
    List<ShipAndData> shipAndData = new ArrayList<>();



    @BeforeEach
    void startUp(){

        Ship ship1 = new Ship("110950637", "firstShip", "IMO7510259","D34R", 40, 459,735, 425.6, 8 );
        Ship ship2 = new Ship("210950637", "secondShip", "IMO7510260","D34S", 50, 460,785, 321.5, 10);
        Ship ship3 = new Ship("310950637", "thirdShip", "IMO7510261","D34T", 60, 461,801, 567.2, 12);

        listOfShips.add(ship1);
        listOfShips.add(ship2);
        listOfShips.add(ship3);

        ShipPositonData shipPositonDataShip11 = new ShipPositonData("31/12/2020", "20.48627, -31.22163", 12.5, 17.4, 385, "p", "A");
        ShipPositonData shipPositonDataShip12 = new ShipPositonData("29/12/2020", "30.48630, -40.22140", 12.5, 17.5, 387, "a", "A");
        ShipPositonData shipPositonDataShip13 = new ShipPositonData("27/12/2020", "40.48633, -31.22150", 12.5, 17.6, 388, "b", "B");

        listOfShipPositionData.add(shipPositonDataShip11);
        listOfShipPositionData.add(shipPositonDataShip12);
        listOfShipPositionData.add(shipPositonDataShip13);

        ShipAndData shipAndData = new ShipAndData(ship1, listOfShipPositionData); // ShipAndData do primeiro barco com a lista de posições
    }

    @Test
    void shipLogTest(){

        ListAllShipsInfoController listAllShipsInfoController = new ListAllShipsInfoController();

    }



}