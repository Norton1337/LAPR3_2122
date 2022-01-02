package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.model.borders.Borders;

class BordersTest {
    
    Borders newBorder;

    @BeforeEach
    void setup(){
        newBorder = new Borders("country1", "country2");
        newBorder.setId("id");
    }

    @Test
    void setAndGetIdTest(){
        assertEquals("id", newBorder.getId());
        newBorder.setId("id2");
        assertEquals("id2", newBorder.getId());
    }

    @Test
    void setAndGetCountry1IdTest(){
        assertEquals("country1", newBorder.getCountry1Id());
        newBorder.setCountry1Id("id1");
        assertEquals("id1", newBorder.getCountry1Id());
    }

    @Test
    void setAndGetCountry2IdTest(){
        assertEquals("country2", newBorder.getCountry2Id());
        newBorder.setCountry2Id("id2");
        assertEquals("id2", newBorder.getCountry2Id());
    }

    @Test
    void toStringTest() {
        String string = "Borders{" +
                        "Id='id'" +
                        ", country1Id='country1'" +
                        ", country2Id='country2'" +
                        '}';
        assertEquals(string, newBorder.toString());
    }
}
