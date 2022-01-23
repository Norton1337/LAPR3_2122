package lapr.project.model.helperClasses;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import lapr.project.controller.helper_classes.CountryColour;

class CountryColourTest {
    CountryColour countryColour;

    @BeforeEach
    void setup(){
        countryColour = new CountryColour("country", 0);
    }
    
    @Test
    void setAndGetCountry(){
        countryColour.setCountry("country");
        assertEquals("country", countryColour.getCountry());
    }

    @Test
    void setAndGetColour(){
        countryColour.setColour(0);
        assertEquals(0, countryColour.getColour());
    }

    @Test
    void toStringTest(){
        String string = "CountryColour{country='country', colour=0}";
        assertEquals(string, countryColour.toString());
    }

}
