package lapr.project.controller.MostTravelledShips;

import lapr.project.controller.helper_classes.KMTravelledCalculator;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KMTravelledCalculatorTest {

    DecimalFormat df = new DecimalFormat("###.######");


    @Test
    void calculateTest() {
        KMTravelledCalculator calculator = new KMTravelledCalculator();
        assertEquals(df.format(5054.94879), df.format(calculator.calculate("-11.27406", "120.44982", "-46.06957", "154.96855")));
    }


    @Test
    void convertToRadiansTest() {
        KMTravelledCalculator calculator = new KMTravelledCalculator();

        assertEquals(df.format(0), df.format(calculator.convertToRadians(0)));

        assertEquals(df.format(0.785398), df.format(calculator.convertToRadians(45)));

        assertEquals(df.format(0.872665), df.format(calculator.convertToRadians(50)));

        assertEquals(df.format(1.570796), df.format(calculator.convertToRadians(90)));

        assertEquals(df.format(2.617994), df.format(calculator.convertToRadians(150)));

    }

}
