package lapr.project.model.ShipPositionData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShipPositonDataTest {

    private final ShipPositonData testShipPosition ;

    public ShipPositonDataTest(){
        this.testShipPosition = new ShipPositonData("31/12/2020", "42.97875,-66.97001",12.9,
                13.1,355,"0","B");
    }

    @BeforeEach
    public void setUp(){
        testShipPosition.setId("1");
        testShipPosition.setShipId("13");
    }

    @Test
    void getId() {
        assertEquals(testShipPosition.getId(), "1");
    }

    @Test
    void setId() {
        String initialId = testShipPosition.getId();
        testShipPosition.setId("725");

        assertEquals(testShipPosition.getId(), "725");
        testShipPosition.setId(initialId);
    }

    @Test
    void getBaseDateTime() {
        assertEquals(testShipPosition.getBaseDateTime(), "31/12/2020");
    }

    @Test
    void setBaseDateTime() {
        String initialBaseDateTime = testShipPosition.getBaseDateTime();
        testShipPosition.setBaseDateTime("2/7/2018");

        assertEquals(testShipPosition.getBaseDateTime(),"2/7/2018");
        testShipPosition.setBaseDateTime(initialBaseDateTime);
    }

    @Test
    void getCoordinates() {
        assertEquals(testShipPosition.getCoordinates(), "42.97875,-66.97001");
    }

    @Test
    void setCoordinates() {
        String initialCoordinates = testShipPosition.getCoordinates();
        testShipPosition.setCoordinates("20.82103, 74.96304");

        assertEquals(testShipPosition.getCoordinates(),"20.82103, 74.96304");
        testShipPosition.setCoordinates(initialCoordinates);
    }

    @Test
    void getSog() {
        assertEquals(testShipPosition.getSog(), 12.9);
    }

    @Test
    void setSog() {
        double initialSog = testShipPosition.getSog();
        testShipPosition.setSog(46.3);

        assertEquals(testShipPosition.getSog(), 46.3);
        testShipPosition.setSog(initialSog);
    }

    @Test
    void getCog() {
        assertEquals(testShipPosition.getCog(), 13.1);
    }

    @Test
    void setCog() {
        double initialCog = testShipPosition.getCog();
        testShipPosition.setCog(24.7);

        assertEquals(testShipPosition.getCog(),24.7);
        testShipPosition.setCog(initialCog);

    }

    @Test
    void getHeading() {
        assertEquals(testShipPosition.getHeading(),355);
    }

    @Test
    void setHeading() {
        int initialHeading = testShipPosition.getHeading();
        testShipPosition.setHeading(216);

        assertEquals(testShipPosition.getHeading(), 216);
        testShipPosition.setHeading(initialHeading);
    }

    @Test
    void getPositon() {
        assertEquals(testShipPosition.getPositon(), "0");
    }

    @Test
    void setPositon() {
        String initialPosition = testShipPosition.getPositon();
        testShipPosition.setPositon("24");

        assertEquals(testShipPosition.getPositon(), "24");
        testShipPosition.setPositon(initialPosition);
    }

    @Test
    void getTranscieverClass() {
        assertEquals(testShipPosition.getTranscieverClass(), "B");
    }

    @Test
    void setTranscieverClass() {
        String initialTransceiver = testShipPosition.getTranscieverClass();
        testShipPosition.setTranscieverClass("G");

        assertEquals(testShipPosition.getTranscieverClass(), "G");
        testShipPosition.setTranscieverClass(initialTransceiver);
    }

    @Test
    void getShipId() {
        assertEquals(testShipPosition.getShipId(), "13");
    }

    @Test
    void setShipId() {
        String initialId = testShipPosition.getShipId();
        testShipPosition.setShipId("7");

        assertEquals(testShipPosition.getShipId(), "7");
        testShipPosition.setShipId(initialId);
    }

    @Test
    void testToString() {
        assertTrue(testShipPosition.toString().contains("31/12/2020"));
    }
}