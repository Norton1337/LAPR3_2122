package lapr.project.cargoShipUserStoriesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.cargoship_stories.CenterOfMass;
import lapr.project.cargoship_stories.Coords;
import lapr.project.cargoship_stories.Shapes;
import lapr.project.cargoship_stories.VesselSink;

class VesselSinkTest {
    VesselSink vesselSink = new VesselSink();
    CenterOfMass centerOfMass;

    ArrayList<Shapes> boatShapes = new ArrayList<>();
    @BeforeEach
    void setup(){
        Coords p11 = new Coords(0, 48.8, 0);
        Coords p12 = new Coords(21,0,0);
        Coords p13 = new Coords(21,48.8,0);
        Shapes shape1 = new Shapes(p11, p12, p13, 51.25, true);

        Coords p21 = new Coords(21, 48.8, 0);
        Coords p22 = new Coords(21,0,0);
        Coords p23 = new Coords(305,0,0);
        Shapes shape2 = new Shapes(p21, p22, p23, 51.25, false);

        Coords p31 = new Coords(305, 48.8, 0);
        Coords p32 = new Coords(305,48.8,0);
        Coords p33 = new Coords(366,0,0);
        Shapes shape3 = new Shapes(p31, p32, p33, 51.25, true);

        Coords p41 = new Coords(91, 48.8, 5);
        Coords p42 = new Coords(133,48.8,5);
        Coords p43 = new Coords(91,90,5);
        Shapes shape4 = new Shapes(p41, p42, p43, 41.25, false);

        Coords p51 = new Coords(260, 48.8, 5);
        Coords p52 = new Coords(280,48.8,5);
        Coords p53 = new Coords(260,90,5);
        Shapes shape5 = new Shapes(p51, p52, p53, 41.25, false);


        boatShapes.add(shape1);
        boatShapes.add(shape2);
        boatShapes.add(shape3);
        boatShapes.add(shape4);
        boatShapes.add(shape5);
        centerOfMass = new CenterOfMass(boatShapes);
    }

    @Test
    void getHeightDifferenceTest(){

        Coords centerOfMassOfShip = centerOfMass.calculateCentroid(boatShapes);
        List<Shapes> withContainers = centerOfMass.addContainers(7.0, 10.0, 5.0, 1000, 48.8,centerOfMassOfShip);
        withContainers.addAll(boatShapes);
        double height = vesselSink.getHeightDifference(250, boatShapes, withContainers);
        String heightString = String.format("%.2f", height);

        assertEquals(String.format("%.2f", 0.03), heightString);

    }

    @Test
    void getPressureExertedTest(){
        Coords centerOfMassOfShip = centerOfMass.calculateCentroid(boatShapes);
        List<Shapes> withContainers = centerOfMass.addContainers(7.0, 10.0, 5.0, 1000, 48.8,centerOfMassOfShip);
        withContainers.addAll(boatShapes);
        double pressure = vesselSink.getPressureExerted(withContainers,250);
        String pressureString = String.format("%.2f", pressure);
        assertEquals(String.format("%.2f", 169409.94), pressureString);
    }

    @Test
    void getMassPlacedTest(){
        assertEquals(500000.0, vesselSink.getMassPlaced(1000, 500));
    }

}
