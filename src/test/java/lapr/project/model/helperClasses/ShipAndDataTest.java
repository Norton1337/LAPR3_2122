package lapr.project.model.helperClasses;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.model.ShipPositionData.ShipPositonData;
import lapr.project.model.Ships.Ship;

class ShipAndDataTest {
    Ship ship = new Ship("211331640", "shipName", "IMO1234567", "callSign", 70, 295, 32, 13.6, 79.0);
    
    List<ShipPositonData> shipPositionDataList = new ArrayList<>();
    ShipAndData shipAndData = new ShipAndData(ship, shipPositionDataList);
    ShipPositonData spd = new ShipPositonData("31/12/2020 21:02", "33.0/-118.4523", 8.6, 42.6, 44, "?", "B");
    ShipPositonData spd1 = new ShipPositonData("31/12/2020 21:00", "33.5/-118.1295", 8.6, 42.6, 44, "?", "B");
    ShipPositonData spd2 = new ShipPositonData("31/12/2020 22:30", "34.7856/-118.4597", 8.6, 42.6, 44, "?", "B");

    @BeforeEach
    void startUp(){
        this.shipPositionDataList.add(spd);
        this.shipPositionDataList.add(spd1);
        this.shipPositionDataList.add(spd2);
        ship.setId("123");
        this.shipAndData.setShip(ship);
        this.shipAndData.setShipPositonData(shipPositionDataList);
    }

    @Test
    void toStringTest(){
        assertEquals("ShipAndData{ship=Ship{id=123, MMSI='211331640', shipName='shipName', IMO='IMO1234567', callSign='callSign', vesselType=70, length=295, width=32, draft=13.6, loadCapacity=79.0}}\n", shipAndData.toString());
    }
}
