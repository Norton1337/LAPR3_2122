package lapr.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.model.helper_classes.ShipAndData;
import lapr.project.model.ship_position_data.ShipPositonData;
import lapr.project.model.ships.Ship;

class TemporalPositionalMessagesControllerTest {
    
    Ship ship = new Ship("211331640", "shipName", "IMO1234567", "callSign", 70, 295, 32, 13.6, 79.0);
    List<ShipPositonData> shipPositionDataList = new ArrayList<>();
    ShipAndData shipAndData = new ShipAndData(ship, shipPositionDataList);
    ShipPositonData spd = new ShipPositonData("31/12/2020 21:02", "33.68384/-118.4523", 8.6, 42.6, 44, "?", "B");
    ShipPositonData spd1 = new ShipPositonData("31/12/2020 21:00", "33.50465/-118.1295", 8.6, 42.6, 44, "?", "B");
    ShipPositonData spd2 = new ShipPositonData("31/12/2020 22:30", "33.785623/-118.4597", 8.6, 42.6, 44, "?", "B");
    
    List<ShipAndData> listShipAndData = new ArrayList<>();


    @BeforeEach
    void startUp(){
        this.shipPositionDataList.add(spd);
        this.shipPositionDataList.add(spd1);
        this.shipPositionDataList.add(spd2);

        this.shipAndData.setShip(ship);
        this.shipAndData.setShipPositonData(shipPositionDataList);

        this.listShipAndData.add(shipAndData);
        this.listShipAndData.add(shipAndData);
        this.listShipAndData.add(shipAndData);
    }

    @Test
    void getSingleShipsAndDataTest(){
        List<ShipPositonData> correctOrderPosList = new ArrayList<>();
        correctOrderPosList.add(spd1);
        correctOrderPosList.add(spd);
        correctOrderPosList.add(spd2);
        ShipAndData correctOrderSAD = new ShipAndData(ship, correctOrderPosList);
        
        TemporalPositionalMessagesController tpm = new TemporalPositionalMessagesController();
        assertEquals(correctOrderSAD.getShip(), tpm.getSingleShipsAndData(this.shipAndData).getShip());
        assertEquals(correctOrderSAD.getShipPositonData(), tpm.getSingleShipsAndData(this.shipAndData).getShipPositonData());
    }

    @Test
    void getAllShipsAndData(){
        List<ShipPositonData> correctOrderPosList = new ArrayList<>();
        correctOrderPosList.add(spd1);
        correctOrderPosList.add(spd);
        correctOrderPosList.add(spd2);
        ShipAndData correctOrderSAD = new ShipAndData(ship, correctOrderPosList);
        List<ShipAndData> sadList = new ArrayList<>();
        sadList.add(correctOrderSAD);
        sadList.add(correctOrderSAD);
        sadList.add(correctOrderSAD);

        TemporalPositionalMessagesController tpm = new TemporalPositionalMessagesController();
        
        for (int i = 0; i < sadList.size(); i++) {
            assertEquals(sadList.get(i).getShip(), tpm.getAllShipsAndData(this.listShipAndData).get(i).getShip());
            assertEquals(sadList.get(i).getShipPositonData(), tpm.getAllShipsAndData(this.listShipAndData).get(i).getShipPositonData());    
        }
        
    }

    @Test
    void getAllPositionalDataInPeriodTest() throws ParseException{
        TemporalPositionalMessagesController tpm = new TemporalPositionalMessagesController();

        assertEquals(1, tpm.getAllPositionalDataInPeriod(this.shipAndData, "31/12/2020 21:01", "31/12/2020 21:59").size());
        assertEquals(2, tpm.getAllPositionalDataInPeriod(this.shipAndData, "31/12/2020 21:00", "31/12/2020 21:59").size());
        assertEquals(0, tpm.getAllPositionalDataInPeriod(this.shipAndData, "31/12/2020 21:00", "31/12/2020 20:00").size());
        
    }
    @Test
    void getAllPositionalDataInDateTest() throws ParseException{
        TemporalPositionalMessagesController tpm = new TemporalPositionalMessagesController();

        assertEquals(this.spd1, tpm.getAllPositionalDataInDate(this.shipAndData, "31/12/2020 21:00").get(0));
        assertEquals(0, tpm.getAllPositionalDataInDate(this.shipAndData, "31/12/2020 00:00").size());
    }
}
