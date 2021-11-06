package lapr.project.model.Ships;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    private final Ship testShip;

    public ShipTest() {
        this.testShip = new Ship(1,"210950000", "VARAMO", "9395044", "C4SQ2", 70,
                166, 25, 9.5, "0", 0, new Generator(0,0,0,0));
    }

    @Test
    void getId() {
        assertEquals(testShip.getId(), 1);
    }

    @Test
    void setId() {
        int initialID = testShip.getId();
        testShip.setId(12345);

        assertEquals(testShip.getId(), 12345);
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
    void getTransceiverClass() {
        assertEquals(testShip.getTransceiverClass(),"0");
    }

    @Test
    void setTransceiverClass() {
        String initialTransceiver = testShip.getTransceiverClass();
        testShip.setTransceiverClass("0");

        assertEquals(testShip.getTransceiverClass(), "0");
        testShip.setTransceiverClass(initialTransceiver);

    }

    @Test
    void getLoadCapacity() {
    }

    @Test
    void setLoadCapacity() {
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
    void getGenerator() {
        assertNotNull(testShip.getGenerator());
    }

    @Test
    void setGenerator() {
        int shipId = testShip.getId();
        Generator initialGen = testShip.getGenerator();

        Generator newGenerator = new Generator(123, 0,0,shipId);
        testShip.setGenerator(newGenerator);

        assertEquals(testShip.getGenerator(), newGenerator);
        testShip.setGenerator(initialGen);
    }

    @Test
    void isMMSIValidTrue() {
        assertTrue(testShip.isMMSIValid("123456789"));
    }

    @Test
    void isMMSIValidFalse() {
        assertFalse(testShip.isMMSIValid("1234567"));
    }

    @Test
    void isIMOValidTrue() {
        assertTrue(testShip.isIMOValid("1234561"));
    }

    @Test
    void isIMOValidFalse() {
        assertFalse(testShip.isMMSIValid("1234"));
    }
}