package lapr.project.model.Containers;

import lapr.project.model.containers.Container;
import lapr.project.utils.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ContainerTest {

    private final Container testContainer;

    public ContainerTest() {
        this.testContainer = new Container(200031, 7, "CBCU", 26.5,
                1.0, 1.5, 30.0, "Certificate XPTO",
                "Please contact XPTO", "refrigerated");

        testContainer.setId(Utils.randomUUID());
    }


    @Test
    void getId() {
        assertTrue(testContainer.getId().contains("-"));
    }

    @Test
    void setId() {
        String initialId = testContainer.getId();
        testContainer.setId("283");

        assertEquals("283", testContainer.getId());
        testContainer.setId(initialId);
    }


    @Test
    void getContainerNumber() {
        assertEquals(200031, testContainer.getContainerNumber());
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
        assertEquals(7, testContainer.getCheckDigit());
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
        assertEquals("CBCU", testContainer.getISOCODE());
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
        assertEquals(26.5, testContainer.getContainerGross());
    }

    @Test
    void setMaxWeightWithContainer() {
        double initialMaxWeight = testContainer.getContainerGross();
        testContainer.setContainerGross(83.5);

        assertEquals(83.5, testContainer.getContainerGross());
        testContainer.setContainerGross(initialMaxWeight);
    }

    @Test
    void getWeightContainer() {
        assertEquals(1.0, testContainer.getContainerTare());
    }

    @Test
    void setWeightContainer() {
        double initialWeightContainer = testContainer.getContainerTare();
        testContainer.setContainerTare(46.1);

        assertEquals(46.1, testContainer.getContainerTare());
        testContainer.setContainerTare(initialWeightContainer);
    }

    @Test
    void getMaxWeightToBePacked() {
        assertEquals(1.5, testContainer.getContainerPayload());
    }

    @Test
    void setMaxWeightToBePacked() {
        double initialMaxWeightPacked = testContainer.getContainerPayload();
        testContainer.setContainerPayload(23.6);

        assertEquals(23.6, testContainer.getContainerPayload());
        testContainer.setContainerPayload(initialMaxWeightPacked);
    }

    @Test
    void getMaxVolToBePacked() {
        assertEquals(30.0, testContainer.getContainerVolume());
    }

    @Test
    void setMaxVolToBePacked() {
        double initialMaxVolPacked = testContainer.getContainerVolume();
        testContainer.setContainerVolume(247.9);

        assertEquals(247.9, testContainer.getContainerVolume());
        testContainer.setContainerVolume(initialMaxVolPacked);
    }

    @Test
    void getCertificates() {
        assertEquals("Certificate XPTO", testContainer.getCertificates());
    }

    @Test
    void setCertificates() {
        String initialCertificates = testContainer.getCertificates();
        testContainer.setCertificates("E");

        assertEquals("E", testContainer.getCertificates());
        testContainer.setCertificates(initialCertificates);
    }

    @Test
    void getRepairRecommendations() {
        assertEquals("Please contact XPTO", testContainer.getRepairRecommendations());
    }

    @Test
    void setRepairRecomendations() {
        String initialRepair = testContainer.getRepairRecommendations();
        testContainer.setRepairRecommendations("abcdef");

        assertEquals("abcdef", testContainer.getRepairRecommendations());
        testContainer.setRepairRecommendations(initialRepair);
    }

    @Test
    void getContainerType() {
        assertEquals("refrigerated", testContainer.getContainerType());
    }

    @Test
    void setContainerType() {
        String initialType = testContainer.getContainerType();
        testContainer.setContainerType("not refrigerated");

        assertEquals("not refrigerated", testContainer.getContainerType());
        testContainer.setRepairRecommendations(initialType);
    }
}