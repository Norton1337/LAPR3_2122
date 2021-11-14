package lapr.project.model.Ships;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

    private final Generator testGenerator;


    public GeneratorTest() {
        this.testGenerator = new Generator(2, 32, "1");
    }

    @Test
    void getNumberOfGenerator() {
        assertEquals(testGenerator.getNumberOfGenerator(),2);
    }

    @Test
    void setNumberOfGenerator() {
        int initialNumberOGenerator = testGenerator.getNumberOfGenerator();
        testGenerator.setNumberOfGenerator(15);

        assertEquals(testGenerator.getNumberOfGenerator(), 15);
        testGenerator.setNumberOfGenerator(initialNumberOGenerator);
    }

    @Test
    void getGeneratorsOutput() {
        assertEquals(testGenerator.getGeneratorsOutput(), 32);
    }

    @Test
    void setGeneratorsOutput() {
        double initialGeneratorOutput = testGenerator.getGeneratorsOutput();
        testGenerator.setGeneratorsOutput(81);

        assertEquals(testGenerator.getGeneratorsOutput(), 81);
        testGenerator.setGeneratorsOutput(initialGeneratorOutput);
    }

    @Test
    void getShipId() {
        assertEquals(testGenerator.getShipId(), "1");
    }

    @Test
    void setShipId() {
        String initialShipId = testGenerator.getId();
        testGenerator.setShipId("48");

        assertEquals(testGenerator.getShipId(), "48");
        testGenerator.setShipId(initialShipId);
    }


    @Test
    void setId() {
        String initialId = testGenerator.getId();
        testGenerator.setId("426");

        assertEquals(testGenerator.getId(), "426");
        testGenerator.setId(initialId);
    }

    @Test
    void testToString() {
        assertTrue(testGenerator.toString().contains("32"));
    }
}