package lapr.project.model.Containers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ContainerTest {

    private final Container testContainer;

    public ContainerTest() {
        this.testContainer = new Container(1534, 4232, 52, 2, "ISO321",
                24.5, 25.2, 43.2, 465.4, "S",
                "Repair", "(x,y,z)", true);
        testContainer.setShipID(1);
    }


    @Test
    void getId() {
        assertEquals(1534, testContainer.getId());
    }

    @Test
    void setId() {
        int initialId = testContainer.getId();
        testContainer.setId(283);

        assertEquals(283, testContainer.getId());
        testContainer.setId(initialId);
    }

    @Test
    void getContainerIdentification() {
        assertEquals(4232, testContainer.getContainerIdentification());
    }

    @Test
    void setContainerIdentification() {
        int initialContainerId = testContainer.getContainerIdentification();
        testContainer.setContainerIdentification(7245);

        assertEquals(7245, testContainer.getContainerIdentification());
        testContainer.setContainerIdentification(initialContainerId);
    }

    @Test
    void getContainerNumber() {
        assertEquals(52, testContainer.getContainerNumber());
    }

    @Test
    void setContainerNumber() {
        int initialContainerNumber = testContainer.getContainerNumber();
        testContainer.setContainerNumber(26);

        assertEquals(26, testContainer.getContainerNumber());
        testContainer.setContainerNumber(initialContainerNumber);
    }

    @Test
    void getCheckDigit() {
        assertEquals(2, testContainer.getCheckDigit());
    }

    @Test
    void setCheckDigit() {
        int initialCheckDigit = testContainer.getCheckDigit();
        testContainer.setCheckDigit(12);

        assertEquals(12, testContainer.getCheckDigit());
        testContainer.setCheckDigit(initialCheckDigit);
    }

    @Test
    void getISOCODE() {
        assertEquals("ISO321",testContainer.getISOCODE());
    }

    @Test
    void setISOCODE() {
        String initialISO = testContainer.getISOCODE();
        testContainer.setISOCODE("ISO751");

        assertEquals("ISO751", testContainer.getISOCODE());
        testContainer.setISOCODE(initialISO);
    }

    @Test
    void getMaxWeightWithContainer() {
        assertEquals(24.5, testContainer.getMaxWeightWithContainer());
    }

    @Test
    void setMaxWeightWithContainer() {
        double initialMaxWeight = testContainer.getMaxWeightWithContainer();
        testContainer.setMaxWeightWithContainer(83.5);

        assertEquals(83.5, testContainer.getMaxWeightWithContainer());
        testContainer.setMaxWeightWithContainer(initialMaxWeight);
    }

    @Test
    void getWeightContainer() {
        assertEquals(25.2, testContainer.getWeightContainer());
    }

    @Test
    void setWeightContainer() {
        double initialWeightContainer = testContainer.getWeightContainer();
        testContainer.setWeightContainer(46.1);

        assertEquals(46.1, testContainer.getWeightContainer());
        testContainer.setWeightContainer(initialWeightContainer);
    }

    @Test
    void getMaxWeightToBePacked() {
        assertEquals(43.2, testContainer.getMaxWeightToBePacked());
    }

    @Test
    void setMaxWeightToBePacked() {
        double initialMaxWeightPacked = testContainer.getMaxWeightToBePacked();
        testContainer.setMaxWeightToBePacked(23.6);

        assertEquals(23.6, testContainer.getMaxWeightToBePacked());
        testContainer.setMaxWeightToBePacked(initialMaxWeightPacked);
    }

    @Test
    void getMaxVolToBePacked() {
        assertEquals(465.4,testContainer.getMaxVolToBePacked());
    }

    @Test
    void setMaxVolToBePacked() {
        double initialMaxVolPacked = testContainer.getMaxVolToBePacked();
        testContainer.setMaxVolToBePacked(247.9);

        assertEquals(247.9, testContainer.getMaxVolToBePacked());
        testContainer.setMaxVolToBePacked(initialMaxVolPacked);
    }

    @Test
    void getCertificates() {
        assertEquals("S", testContainer.getCertificates());
    }

    @Test
    void setCertificates() {
        String initialCerteficate = testContainer.getCertificates();
        testContainer.setCertificates("E");

        assertEquals("E", testContainer.getCertificates());
        testContainer.setCertificates(initialCerteficate);
    }

    @Test
    void getRepairRecomendations() {
        assertEquals("Repair", testContainer.getRepairRecomendations());
    }

    @Test
    void setRepairRecomendations() {
        String initialRepair = testContainer.getRepairRecomendations();
        testContainer.setRepairRecomendations("abcdef");

        assertEquals("abcdef",testContainer.getRepairRecomendations());
        testContainer.setRepairRecomendations(initialRepair);
    }

    @Test
    void getContainerPosition() {
        assertEquals("(x,y,z)",testContainer.getContainerPosition() );
    }

    @Test
    void setContainerPosition() {
        String initialContainerPosition = testContainer.getContainerPosition();
        testContainer.setContainerPosition("(a,b,c)");

        assertEquals("(a,b,c)",testContainer.getContainerPosition());
        testContainer.setContainerPosition(initialContainerPosition);
    }

    @Test
    void isRefrigerated() {
        assertTrue(testContainer.isRefrigerated());
    }

    @Test
    void setRefrigerated() {
        boolean initialRef = testContainer.isRefrigerated();
        testContainer.setRefrigerated(false);

        assertFalse(testContainer.isRefrigerated());
        testContainer.setRefrigerated(initialRef);
    }

    @Test
    void getShipID() {
        assertEquals(1, testContainer.getShipID());
    }

    @Test
    void setShipID() {
        int initialShipId = testContainer.getShipID();
        testContainer.setShipID(5);

        assertEquals(5, testContainer.getShipID());
        testContainer.setShipID(initialShipId);
    }
}