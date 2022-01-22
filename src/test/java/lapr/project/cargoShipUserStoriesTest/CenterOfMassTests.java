package lapr.project.cargoShipUserStoriesTest;

import lapr.project.cargoShipUserStories.CenterOfMass;
import lapr.project.cargoShipUserStories.Coords;
import lapr.project.cargoShipUserStories.Shapes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

class CenterOfMassTests {
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

        Coords p31 = new Coords(305, 0, 0);
        Coords p32 = new Coords(305,48.8,0);
        Coords p33 = new Coords(366,48.8,0);
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
    void calculateCentroidTest(){
        Coords centerOfMassOfShip = centerOfMass.calculateCentroid(boatShapes);
        assertEquals(197.55937071395948, centerOfMassOfShip.getX());
        assertEquals(30.47236067033038, centerOfMassOfShip.getY());
        assertEquals(25.625, centerOfMassOfShip.getZ());
    }


    @Test
    void addContainersTest(){
        Coords centerOfMassOfShip = centerOfMass.calculateCentroid(boatShapes);
        
        List<Shapes> newContainers = centerOfMass.addContainers(7.0, 10.0, 5.0, 100, 48.8,centerOfMassOfShip);
        boatShapes.addAll(newContainers);
        Coords newCenterOfMassOfShip = centerOfMass.calculateCentroid(boatShapes);
        assertEquals(String.format("%.2f", centerOfMassOfShip.getX()), String.format("%.2f", newCenterOfMassOfShip.getX()));
        assertEquals(String.format("%.2f", centerOfMassOfShip.getZ()), String.format("%.2f", newCenterOfMassOfShip.getZ()));

    }

    @Test
    void addContainersTest2(){
        Coords centerOfMassOfShip = centerOfMass.calculateCentroid(boatShapes);
        
        List<Shapes> newContainers = centerOfMass.addContainers(10.0, 10.0, 5.0, 1, 48.8,centerOfMassOfShip);
        boatShapes.addAll(newContainers);
        Coords newCenterOfMassOfShip = centerOfMass.calculateCentroid(boatShapes);
        assertEquals(String.format("%.2f", centerOfMassOfShip.getX()), String.format("%.2f", newCenterOfMassOfShip.getX()));
        assertEquals(String.format("%.2f", centerOfMassOfShip.getZ()), String.format("%.2f", newCenterOfMassOfShip.getZ()));

    }

    @Test
    void addContainersTest3(){
        Coords centerOfMassOfShip = centerOfMass.calculateCentroid(boatShapes);
        
        List<Shapes> newContainers = centerOfMass.addContainers(10.0, 7.0, 5.0, 2050, 48.8,centerOfMassOfShip);
        boatShapes.addAll(newContainers);
        Coords newCenterOfMassOfShip = centerOfMass.calculateCentroid(boatShapes);
        assertEquals(String.format("%.2f", centerOfMassOfShip.getX()), String.format("%.2f", newCenterOfMassOfShip.getX()));
        assertEquals(String.format("%.2f", centerOfMassOfShip.getZ()), String.format("%.2f", newCenterOfMassOfShip.getZ()));

    }



}
