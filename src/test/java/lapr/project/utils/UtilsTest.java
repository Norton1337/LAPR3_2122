package lapr.project.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static lapr.project.utils.Utils.ToDouble;
import static lapr.project.utils.Utils.ToInt;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void toInt() {
        assertEquals(0, ToInt("NA"));
        assertEquals(324, ToInt("324"));
    }

    @Test
    void toDouble() {
        assertEquals(0.0, ToDouble("NA"));
        assertEquals(324.0, ToDouble("324"));
    }

    @Test
    void printList() {

        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(2);
        list.add(1);
        list.add(7);

        assertTrue(Utils.printList(list));
    }

    @Test
    void randomInt() {
        assertTrue(Utils.randomInt() > 5);
    }


    @Test
    void randomUUID() {
        assertNotNull(Utils.randomUUID());
    }


    @Test
    void convertDate() {
        String coordinates = "01/02/2000 12:59";
        Date date = Utils.convertDate(coordinates);

        assertNotNull(date.getTime());

        String coordinates1 = "testes";
        date = Utils.convertDate(coordinates1);

        assertNull(date);

    }

    @Test
    void convertCoordinates() {
        String coordinates = "47.59931,-122.39163";

        String[] coordinatesArray = Utils.convertCoordinates(coordinates);

        assertEquals("47.59931", coordinatesArray[0]);
        assertEquals("-122.39163", coordinatesArray[1]);
    }

    @Test
    void stripC() {
        String coordinates = "47.59931/-122.39163";

        String[] coordinatesArray = Utils.stripC(coordinates);


        assertEquals("47.59931", coordinatesArray[0]);
        assertEquals("-122.39163", coordinatesArray[1]);
    }

    @Test
    void readFromPropTest(){
        try {
            Utils.readFromProp("debug","null");
        } catch (Exception e) {
            assertTrue(true);
        }
        
        try {
            Utils.readFromProp("debug",null);
        } catch (Exception e) {
            assertTrue(true);
        }

        try {
            Utils.readFromProp(null,"src/main/resources/application.properties");
        } catch (Exception e) {
            assertTrue(true);
        }
        
        assertNull(Utils.readFromProp("nothing","src/main/resources/application.properties"));

    }
}