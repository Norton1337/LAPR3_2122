package lapr.project.model.Ships;

import org.junit.jupiter.api.Test;

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
        assertEquals(testShip.getId(), "1");
    }

    @Test
    void setId() {
        String initialID = testShip.getId();
        testShip.setId("12345");

        assertEquals(testShip.getId(), "12345");
        testShip.setId(initialID);
    }

    @Test
    void getMMSI() {
        assertEquals(testShip.getMMSI(), "210950000");
    }

    @Test
    void setMMSI() {
        String initialMMSI = testShip.getMMSI();
        testShip.setMMSI("210959999");

        assertEquals(testShip.getMMSI(), "210959999");
        testShip.setMMSI(initialMMSI);
    }

    @Test
    void getShipName() {
        assertEquals(testShip.getShipName(), "VARAMO");
    }

    @Test
    void setShipName() {
        String initialShipName = testShip.getShipName();
        testShip.setShipName("ROSAS");

        assertEquals(testShip.getShipName(), "ROSAS");
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

        assertEquals(testShip.getIMO(), "6789321");
        testShip.setIMO(initialIMO);
    }

    @Test
    void getCallSign() {
        assertEquals(testShip.getCallSign(),"C4SQ2" );
    }

    @Test
    void setCallSign() {
        String initialCallSign = testShip.getCallSign();
        testShip.setCallSign("D34R");

        assertEquals(testShip.getCallSign(), "D34R");
        testShip.setCallSign(initialCallSign);
    }

    @Test
    void getVesselType() {
        assertEquals(testShip.getVesselType(), 70);
    }

    @Test
    void setVesselType() {
        int initialVessel = testShip.getVesselType();
        testShip.setVesselType(50);

        assertEquals(testShip.getVesselType(), 50);
        testShip.setVesselType(initialVessel);
    }

    @Test
    void getLength() {
        assertEquals(testShip.getLength(), 166);
    }

    @Test
    void setLength() {
        int initialLength = testShip.getLength();
        testShip.setLength(300);

        assertEquals(testShip.getLength(), 300);
        testShip.setLength(initialLength);
    }

    @Test
    void getWidth() {
        assertEquals(testShip.getWidth(), 25);
    }

    @Test
    void setWidth() {
        int initialWidth = testShip.getWidth();
        testShip.setWidth(120);

        assertEquals(testShip.getWidth(), 120);
        testShip.setWidth(initialWidth);
    }


    @Test
    void getLoadCapacity() {
        assertEquals(testShip.getLoadCapacity(), 0.0);
    }

    @Test
    void setLoadCapacity() {
        double initialLoadCapacity = testShip.getLoadCapacity();
        testShip.setLoadCapacity(4.6);

        assertEquals(testShip.getLoadCapacity(), 4.6);
        testShip.setLoadCapacity(initialLoadCapacity);
    }

    @Test
    void getDraft() {
        assertEquals(testShip.getDraft(), 9.5);
    }

    @Test
    void setDraft() {
        double initialDraft = testShip.getDraft();
        testShip.setDraft(215.3);

        assertEquals(testShip.getDraft(), 215.3);
        testShip.setDraft(initialDraft);
    }

    @Test
    void isMMSIValidTrue() {
        assertTrue(testShip.isMMSIValid("123456789"));
    }

    @Test
    void invalidMMSI() {
        assertThrows(IllegalArgumentException.class, () -> {
            testShip.setMMSI("34567");
        });

    }


    @Test
    void isIMOValidTrue() {
        assertTrue(testShip.isIMOValid("1234561"));
    }

    @Test
    void isIMOValidFalse() {
        assertFalse(testShip.isIMOValid("1234"));
    }


    @Test
    void invalidIMO() {
        assertThrows(IllegalArgumentException.class, () -> {
            testShip.setIMO("9876");
        });

    }

}