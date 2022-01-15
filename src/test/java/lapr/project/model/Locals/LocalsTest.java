package lapr.project.model.Locals;

import lapr.project.model.locals.Locals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static lapr.project.utils.Utils.readFromProp;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LocalsTest {
    private Locals portsAndWarehouses;

    @BeforeEach
    void setup() {
        portsAndWarehouses = new Locals("Portugal", 12345, "Leixões", "41.1827759,-8.7205652");
        portsAndWarehouses.setLocalCapacity(420);
    }


    @Test
    void setAndGetLocalCapaticyTest() {
        assertEquals(420, portsAndWarehouses.getLocalCapacity());
        portsAndWarehouses.setLocalCapacity(58);
        assertEquals(58, portsAndWarehouses.getLocalCapacity());
    }

    @Test
    void setAndGetIdTest() {
        assertNull(portsAndWarehouses.getId());
        portsAndWarehouses.setId("12345");
        assertEquals("12345", portsAndWarehouses.getId());
    }


    @Test
    void setAndGetCountryTest() {
        assertEquals("Portugal", portsAndWarehouses.getCountryId());
        portsAndWarehouses.setCountryId("Poland");
        assertEquals("Poland", portsAndWarehouses.getCountryId());
    }

    @Test
    void setAndGetCodeTest() {
        assertEquals(12345, portsAndWarehouses.getLocalCode());
        portsAndWarehouses.setLocalCode(67890);
        assertEquals(67890, portsAndWarehouses.getLocalCode());
    }

    @Test
    void setAndGetPortNameTest() {
        assertEquals("Leixões", portsAndWarehouses.getName());
        portsAndWarehouses.setName("Port of Gdańsk");
        assertEquals("Port of Gdańsk", portsAndWarehouses.getName());
    }

    @Test
    void setAndGetCoordinatesTest() {
        assertEquals("41.1827759,-8.7205652", portsAndWarehouses.getCoordinates());
        portsAndWarehouses.setCoordinates("54.393333,18.67");
        assertEquals("54.393333,18.67", portsAndWarehouses.getCoordinates());
    }

    @Test
    void setAndGetVehicleIdTest() {
        assertNull(portsAndWarehouses.getVehicleId());
        portsAndWarehouses.setVehicleId("112233");
        assertEquals("112233", portsAndWarehouses.getVehicleId());
    }

    @Test
    void toStringTest() {
        portsAndWarehouses.setType("Port");
        if (Objects.equals(readFromProp("debug", "src/main/resources/application.properties"), "1")){
            System.out.println(portsAndWarehouses.toString());
        }
        assertTrue(portsAndWarehouses.toString().contains("type='Port'"));
    }


}
