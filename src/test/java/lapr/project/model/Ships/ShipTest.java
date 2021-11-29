package lapr.project.model.Ships;

import org.junit.jupiter.api.Test;

import lapr.project.model.ships.Ship;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    private final Ship testShip;

    public ShipTest() {
        this.testShip = new Ship("210950000", "VARAMO", "9395044", "C4SQ2", 70,
                166, 25, 9.5, 0.0);
    }

    @Test
    void constructorTest(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Ship("34567", "VARAMO", "9395044", "C4SQ2", 70, 166, 25, 9.5, 0.0);
        });
    }

    @Test
    void getId() {
        testShip.setId("1");
        assertEquals("1", testShip.getId());
    }

    @Test
    void setId() {
        String initialID = testShip.getId();
        testShip.setId("12345");

        assertEquals("12345", testShip.getId());
        testShip.setId(initialID);
    }

    @Test
    void getMMSI() {
        assertEquals("210950000", testShip.getMMSI());
    }

    @Test
    void setMMSI() {
        String initialMMSI = testShip.getMMSI();
        testShip.setMMSI("210959999");

        assertEquals("210959999", testShip.getMMSI());
        testShip.setMMSI(initialMMSI);
    }

    @Test
    void getShipName() {
        assertEquals("VARAMO", testShip.getShipName());
    }

    @Test
    void setShipName() {
        String initialShipName = testShip.getShipName();
        testShip.setShipName("ROSAS");

        assertEquals("ROSAS", testShip.getShipName());
        testShip.setShipName(initialShipName);
    }

    @Test
    void getIMO() {
        assertEquals(testShip.getIMO(), "9395044" );
    }

    @Test
    void setIMO() {
        String initialIMO = testShip.getIMO();
        testShip.setIMO("6789321");

        assertEquals("6789321", testShip.getIMO());
        testShip.setIMO(initialIMO);
    }

    @Test
    void getCallSign() {
        assertEquals("C4SQ2",testShip.getCallSign() );
    }

    @Test
    void setCallSign() {
        String initialCallSign = testShip.getCallSign();
        testShip.setCallSign("D34R");

        assertEquals("D34R", testShip.getCallSign());
        testShip.setCallSign(initialCallSign);
    }

    @Test
    void getVesselType() {
        assertEquals(70, testShip.getVesselType());
    }

    @Test
    void setVesselType() {
        int initialVessel = testShip.getVesselType();
        testShip.setVesselType(50);

        assertEquals(50, testShip.getVesselType());
        testShip.setVesselType(initialVessel);
    }

    @Test
    void getLength() {
        assertEquals(166, testShip.getLength());
    }

    @Test
    void setLength() {
        int initialLength = testShip.getLength();
        testShip.setLength(300);

        assertEquals(300, testShip.getLength());
        testShip.setLength(initialLength);
    }

    @Test
    void getWidth() {
        assertEquals(25, testShip.getWidth());
    }

    @Test
    void setWidth() {
        int initialWidth = testShip.getWidth();
        testShip.setWidth(120);

        assertEquals(120, testShip.getWidth());
        testShip.setWidth(initialWidth);
    }


    @Test
    void getLoadCapacity() {
        assertEquals(0.0, testShip.getLoadCapacity());
    }

    @Test
    void setLoadCapacity() {
        double initialLoadCapacity = testShip.getLoadCapacity();
        testShip.setLoadCapacity(4.6);

        assertEquals(4.6, testShip.getLoadCapacity());
        testShip.setLoadCapacity(initialLoadCapacity);
    }

    @Test
    void getDraft() {
        assertEquals(9.5, testShip.getDraft());
    }

    @Test
    void setDraft() {
        double initialDraft = testShip.getDraft();
        testShip.setDraft(215.3);

        assertEquals(215.3, testShip.getDraft());
        testShip.setDraft(initialDraft);
    }

    @Test
    void isMMSIValidTrue() {
        assertTrue(Ship.isMMSIValid("123456789"));
    }

    @Test
    void invalidMMSI() {
        assertThrows(IllegalArgumentException.class, () -> {
            testShip.setMMSI("34567");
        });

    }


    @Test
    void isIMOValidTrue() {
        assertTrue(Ship.isIMOValid("1234561"));
    }

    @Test
    void isIMOValidFalse() {
        assertFalse(Ship.isIMOValid("1234"));
    }


    @Test
    void invalidIMO() {
        assertThrows(IllegalArgumentException.class, () -> {
            testShip.setIMO("9876");
        });

    }

}