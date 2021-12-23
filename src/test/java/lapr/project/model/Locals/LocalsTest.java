package lapr.project.model.Locals;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.model.locals.Locals;

class LocalsTest {
    private Locals portsAndWarehouses;

    @BeforeEach
    void setup(){
        portsAndWarehouses = new Locals("Europe", "Portugal", 12345, "Leixões", "41.1827759,-8.7205652");
    }

    @Test
    void setAndGetIdTest(){
        assertNull(portsAndWarehouses.getId());
        portsAndWarehouses.setId("12345");
        assertEquals("12345", portsAndWarehouses.getId());
    }

    @Test
    void setAndGetContinentTest(){
        assertEquals("Europe", portsAndWarehouses.getContinent());
        portsAndWarehouses.setContinent("Africa");
        assertEquals("Africa", portsAndWarehouses.getContinent());
    }

    @Test
    void setAndGetCountryTest(){
        assertEquals("Portugal", portsAndWarehouses.getCountry());
        portsAndWarehouses.setCountry("Poland");
        assertEquals("Poland", portsAndWarehouses.getCountry());
    }

    @Test
    void setAndGetCodeTest(){
        assertEquals(12345, portsAndWarehouses.getCode());
        portsAndWarehouses.setCode(67890);
        assertEquals(67890, portsAndWarehouses.getCode());
    }

    @Test
    void setAndGetPortNameTest(){
        assertEquals("Leixões", portsAndWarehouses.getPortName());
        portsAndWarehouses.setPortName("Port of Gdańsk");
        assertEquals("Port of Gdańsk", portsAndWarehouses.getPortName());
    }

    @Test
    void setAndGetCoordinatesTest(){
        assertEquals("41.1827759,-8.7205652",portsAndWarehouses.getCoordinates());
        portsAndWarehouses.setCoordinates("54.393333,18.67");
        assertEquals("54.393333,18.67", portsAndWarehouses.getCoordinates());
    }

    @Test
    void setAndGetShipIdTest(){
        assertNull(portsAndWarehouses.getShipId());
        portsAndWarehouses.setShipId("112233");
        assertEquals("112233", portsAndWarehouses.getShipId());
    }

    @Test
    void toStringTest(){
        assertTrue(portsAndWarehouses.toString().contains("code=12345"));
    }

    @Test
    void compareToTest(){
        assertEquals(0, portsAndWarehouses.compareTo(portsAndWarehouses));
    }

   
}
