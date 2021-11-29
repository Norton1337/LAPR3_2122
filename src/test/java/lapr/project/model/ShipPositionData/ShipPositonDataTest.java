package lapr.project.model.ShipPositionData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.model.ship_position_data.ShipPositonData;

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
        assertEquals("1", testShipPosition.getId());
    }

    @Test
    void setId() {
        String initialId = testShipPosition.getId();
        testShipPosition.setId("725");

        assertEquals("725", testShipPosition.getId());
        testShipPosition.setId(initialId);
    }

    @Test
    void getBaseDateTime() {
        assertEquals("31/12/2020", testShipPosition.getBaseDateTime());
    }

    @Test
    void setBaseDateTime() {
        String initialBaseDateTime = testShipPosition.getBaseDateTime();
        testShipPosition.setBaseDateTime("2/7/2018");

        assertEquals("2/7/2018",testShipPosition.getBaseDateTime());
        testShipPosition.setBaseDateTime(initialBaseDateTime);
    }

    @Test
    void getCoordinates() {
        assertEquals("42.97875,-66.97001", testShipPosition.getCoordinates());
    }

    @Test
    void setCoordinates() {
        String initialCoordinates = testShipPosition.getCoordinates();
        testShipPosition.setCoordinates("20.82103, 74.96304");

        assertEquals("20.82103, 74.96304",testShipPosition.getCoordinates());
        testShipPosition.setCoordinates(initialCoordinates);
    }

    @Test
    void getSog() {
        assertEquals(12.9, testShipPosition.getSog());
    }

    @Test
    void setSog() {
        double initialSog = testShipPosition.getSog();
        testShipPosition.setSog(46.3);

        assertEquals(46.3, testShipPosition.getSog());
        testShipPosition.setSog(initialSog);
    }

    @Test
    void getCog() {
        assertEquals(13.1, testShipPosition.getCog());
    }

    @Test
    void setCog() {
        double initialCog = testShipPosition.getCog();
        testShipPosition.setCog(24.7);

        assertEquals(24.7,testShipPosition.getCog());
        testShipPosition.setCog(initialCog);

    }

    @Test
    void getHeading() {
        assertEquals(355,testShipPosition.getHeading());
    }

    @Test
    void setHeading() {
        int initialHeading = testShipPosition.getHeading();
        testShipPosition.setHeading(216);

        assertEquals(216, testShipPosition.getHeading());
        testShipPosition.setHeading(initialHeading);
    }

    @Test
    void getPositon() {
        assertEquals("0", testShipPosition.getPositon());
    }

    @Test
    void setPositon() {
        String initialPosition = testShipPosition.getPositon();
        testShipPosition.setPositon("24");

        assertEquals("24", testShipPosition.getPositon());
        testShipPosition.setPositon(initialPosition);
    }

    @Test
    void getTranscieverClass() {
        assertEquals("B", testShipPosition.getTranscieverClass());
    }

    @Test
    void setTranscieverClass() {
        String initialTransceiver = testShipPosition.getTranscieverClass();
        testShipPosition.setTranscieverClass("G");

        assertEquals("G", testShipPosition.getTranscieverClass());
        testShipPosition.setTranscieverClass(initialTransceiver);
    }

    @Test
    void getShipId() {
        assertEquals("13", testShipPosition.getShipId());
    }

    @Test
    void setShipId() {
        String initialId = testShipPosition.getShipId();
        testShipPosition.setShipId("7");

        assertEquals("7", testShipPosition.getShipId());
        testShipPosition.setShipId(initialId);
    }

    @Test
    void testToString() {
        assertTrue(testShipPosition.toString().contains("31/12/2020"));
    }
}