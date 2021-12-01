package lapr.project.model.Ships;

import org.junit.jupiter.api.Test;

import lapr.project.model.ships.Generator;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

    private final Generator testGenerator;


    public GeneratorTest() {
        this.testGenerator = new Generator(2, 32, "1");
    }

    @Test
    void getNumberOfGenerator() {
        assertEquals(2,testGenerator.getNumberOfGenerator());
    }

    @Test
    void setNumberOfGenerator() {
        int initialNumberOGenerator = testGenerator.getNumberOfGenerator();
        testGenerator.setNumberOfGenerator(15);

        assertEquals(15, testGenerator.getNumberOfGenerator());
        testGenerator.setNumberOfGenerator(initialNumberOGenerator);
    }

    @Test
    void getGeneratorsOutput() {
        assertEquals(32, testGenerator.getGeneratorsOutput());
    }

    @Test
    void setGeneratorsOutput() {
        double initialGeneratorOutput = testGenerator.getGeneratorsOutput();
        testGenerator.setGeneratorsOutput(81);

        assertEquals(81, testGenerator.getGeneratorsOutput());
        testGenerator.setGeneratorsOutput(initialGeneratorOutput);
    }

    @Test
    void getShipId() {
        assertEquals("1", testGenerator.getShipId());
    }

    @Test
    void setShipId() {
        String initialShipId = testGenerator.getId();
        testGenerator.setShipId("48");

        assertEquals("48", testGenerator.getShipId());
        testGenerator.setShipId(initialShipId);
    }


    @Test
    void setId() {
        String initialId = testGenerator.getId();
        testGenerator.setId("426");

        assertEquals("426", testGenerator.getId());
        testGenerator.setId(initialId);
    }

    @Test
    void testToString() {
        assertTrue(testGenerator.toString().contains("32"));
    }
}