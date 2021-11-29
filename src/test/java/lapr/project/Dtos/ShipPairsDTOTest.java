package lapr.project.Dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShipPairsDTOTest {
    

    ShipPairsDTO shipPairsDTO;

    @BeforeEach
    void setup(){
        shipPairsDTO = new ShipPairsDTO("ship1MMSI", "ship2MMSI", "ship1Mov", "ship2Mov", "1.234", "5.678");
    }


    @Test
    void getandSetShip1MMSITest() {
        assertEquals("ship1MMSI", shipPairsDTO.getShip1MMSI());
        shipPairsDTO.setShip1MMSI("newMMSI");
        assertEquals("newMMSI", shipPairsDTO.getShip1MMSI());
    }


    @Test
    void getAndSetShip2MMSITest() {
        assertEquals("ship2MMSI", shipPairsDTO.getShip2MMSI());
        shipPairsDTO.setShip2MMSI("newMMSI");
        assertEquals("newMMSI", shipPairsDTO.getShip2MMSI());
    }

    @Test
    void getAndSetShip1MovTest() {
        assertEquals("ship1Mov", shipPairsDTO.getShip1Mov());
        shipPairsDTO.setShip1Mov("newShipMov");
        assertEquals("newShipMov", shipPairsDTO.getShip1Mov());
    }


    @Test
    void getAndSetShip2MovTest() {
        assertEquals("ship2Mov", shipPairsDTO.getShip2Mov());
        shipPairsDTO.setShip2Mov("newShipMov");
        assertEquals("newShipMov", shipPairsDTO.getShip2Mov());
    }

    @Test
    void getAndSetShip1TraveldistanceTest() {
       assertEquals("1.234", shipPairsDTO.getShip1Traveldistance());
       shipPairsDTO.setShip1Traveldistance("1234");
       assertEquals("1234", shipPairsDTO.getShip1Traveldistance());
    }



    @Test
    void getAndSetShip2TraveldistanceTest() {
        assertEquals("5.678", shipPairsDTO.getShip2Trabeldistance());
        shipPairsDTO.setShip2Trabeldistance("5678");
        assertEquals("5678", shipPairsDTO.getShip2Trabeldistance());
    }



    @Test
    void toStringTest() {
        String string = "ship1MMSI    ship2MMSI      ship1Mov      1,234      ship2Mov      5,678";
        assertEquals(string, shipPairsDTO.toString());
    }

}
