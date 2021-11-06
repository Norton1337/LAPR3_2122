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
        assertEquals(testContainer.getId(), 1534);
    }

    @Test
    void setId() {
        int initialId = testContainer.getId();
        testContainer.setId(283);

        assertEquals(testContainer.getId(), 283);
        testContainer.setId(initialId);
    }

    @Test
    void getContainerIdentification() {
        assertEquals(testContainer.getContainerIdentification(), 4232);
    }

    @Test
    void setContainerIdentification() {
        int initialContainerId = testContainer.getContainerIdentification();
        testContainer.setContainerIdentification(7245);

        assertEquals(testContainer.getContainerIdentification(), 7245);
        testContainer.setContainerIdentification(initialContainerId);
    }

    @Test
    void getContainerNumber() {
        assertEquals(testContainer.getContainerNumber(), 52);
    }

    @Test
    void setContainerNumber() {
        int initialContainerNumber = testContainer.getContainerNumber();
        testContainer.setContainerNumber(26);

        assertEquals(testContainer.getContainerNumber(), 26);
        testContainer.setContainerNumber(initialContainerNumber);
    }

    @Test
    void getCheckDigit() {
        assertEquals(testContainer.getCheckDigit(), 2);
    }

    @Test
    void setCheckDigit() {
        int initialCheckDigit = testContainer.getCheckDigit();
        testContainer.setCheckDigit(12);

        assertEquals(testContainer.getCheckDigit(), 12);
        testContainer.setCheckDigit(initialCheckDigit);
    }

    @Test
    void getISOCODE() {
        assertEquals(testContainer.getISOCODE(),"ISO321");
    }

    @Test
    void setISOCODE() {
        String initialISO = testContainer.getISOCODE();
        testContainer.setISOCODE("ISO751");

        assertEquals(testContainer.getISOCODE(), "ISO751");
        testContainer.setISOCODE(initialISO);
    }

    @Test
    void getMaxWeightWithContainer() {
        assertEquals(testContainer.getMaxWeightWithContainer(), 24.5);
    }

    @Test
    void setMaxWeightWithContainer() {
        double initialMaxWeight = testContainer.getMaxWeightWithContainer();
        testContainer.setMaxWeightWithContainer(83.5);

        assertEquals(testContainer.getMaxWeightWithContainer(), 83.5);
        testContainer.setMaxWeightWithContainer(initialMaxWeight);
    }

    @Test
    void getWeightContainer() {
        assertEquals(testContainer.getWeightContainer(), 25.2);
    }

    @Test
    void setWeightContainer() {
        double initialWeightContainer = testContainer.getWeightContainer();
        testContainer.setWeightContainer(46.1);

        assertEquals(testContainer.getWeightContainer(), 46.1);
        testContainer.setWeightContainer(initialWeightContainer);
    }

    @Test
    void getMaxWeightToBePacked() {
        assertEquals(testContainer.getMaxWeightToBePacked(), 43.2);
    }

    @Test
    void setMaxWeightToBePacked() {
        double initialMaxWeightPacked = testContainer.getMaxWeightToBePacked();
        testContainer.setMaxWeightToBePacked(23.6);

        assertEquals(testContainer.getMaxWeightToBePacked(), 23.6);
        testContainer.setMaxWeightToBePacked(initialMaxWeightPacked);
    }

    @Test
    void getMaxVolToBePacked() {
        assertEquals(testContainer.getMaxVolToBePacked(),465.4);
    }

    @Test
    void setMaxVolToBePacked() {
        double initialMaxVolPacked = testContainer.getMaxVolToBePacked();
        testContainer.setMaxVolToBePacked(247.9);

        assertEquals(testContainer.getMaxVolToBePacked(), 247.9);
        testContainer.setMaxVolToBePacked(initialMaxVolPacked);
    }

    @Test
    void getCertificates() {
        assertEquals(testContainer.getCertificates(), "S");
    }

    @Test
    void setCertificates() {
        String initialCerteficate = testContainer.getCertificates();
        testContainer.setCertificates("E");

        assertEquals(testContainer.getCertificates(), "E");
        testContainer.setCertificates(initialCerteficate);
    }

    @Test
    void getRepairRecomendations() {
        assertEquals(testContainer.getRepairRecomendations(), "Repair");
    }

    @Test
    void setRepairRecomendations() {
        String initialRepair = testContainer.getRepairRecomendations();
        testContainer.setRepairRecomendations("abcdef");

        assertEquals(testContainer.getRepairRecomendations(),"abcdef");
        testContainer.setRepairRecomendations(initialRepair);
    }

    @Test
    void getContainerPosition() {
        assertEquals(testContainer.getContainerPosition(),"(x,y,z)" );
    }

    @Test
    void setContainerPosition() {
        String initialContainerPosition = testContainer.getContainerPosition();
        testContainer.setContainerPosition("(a,b,c)");

        assertEquals(testContainer.getContainerPosition(),"(a,b,c)");
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
        assertEquals(testContainer.getShipID(), 1);
    }

    @Test
    void setShipID() {
        int initialShipId = testContainer.getShipID();
        testContainer.setShipID(5);

        assertEquals(testContainer.getShipID(), 5);
        testContainer.setShipID(initialShipId);
    }
}