package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.model.country.Country;

class CountryTest {
    Country newCountry;

    @BeforeEach
    void setup(){
        newCountry = new Country("continent", "alpha2Code", "alpha3Code", "countryName", "population", "capital", "coordinates");
        newCountry.setId("id");
    }

    @Test
    void setAndGetIdTest(){
        assertEquals("id", newCountry.getId());
        newCountry.setId("id2");
        assertEquals("id2", newCountry.getId());
    }

    @Test
    void setAndGetContinentTest(){
        assertEquals("continent", newCountry.getContinent());
        newCountry.setContinent("continent2");
        assertEquals("continent2", newCountry.getContinent());
    }

    @Test
    void setAndGetAlpha2CodeTest(){
        assertEquals("alpha2Code", newCountry.getAlpha2Code());
        newCountry.setAlpha2Code("alpha2Code2");
        assertEquals("alpha2Code2", newCountry.getAlpha2Code());
    }

    @Test
    void setAndGetAlpha3CodeTest(){
        assertEquals("alpha3Code", newCountry.getAlpha3Code());
        newCountry.setAlpha3Code("alpha3Code2");
        assertEquals("alpha3Code2", newCountry.getAlpha3Code());
    }

    @Test
    void setAndGetCountryNameTest(){
        assertEquals("countryName", newCountry.getCountryName());
        newCountry.setCountryName("countryName2");
        assertEquals("countryName2", newCountry.getCountryName());
    }

    @Test
    void setAndGetPopulationTest(){
        assertEquals("population", newCountry.getPopulation());
        newCountry.setPopulation("population2");
        assertEquals("population2", newCountry.getPopulation());
    }

    @Test
    void setAndGetCapitalTest(){
        assertEquals("capital", newCountry.getCapital());
        newCountry.setCapital("capital2");
        assertEquals("capital2", newCountry.getCapital());
    }

    @Test
    void setAndGetCoordinatesTest(){
        assertEquals("coordinates", newCountry.getCoordinates());
        newCountry.setCoordinates("coordinates2");
        assertEquals("coordinates2", newCountry.getCoordinates());
    }

    @Test
    void toStringTest() {
        String string = "Country{" +
                        "id='id'" +
                        ", continent='continent'" +
                        ", alpha2Code='alpha2Code'" +
                        ", alpha3Code='alpha3Code'" +
                        ", countryName='countryName'" +
                        ", population='population'" +
                        ", capital='capital'" +
                        ", coordinates='coordinates'" +
                        '}';
        assertEquals(string, newCountry.toString());
    }
}
