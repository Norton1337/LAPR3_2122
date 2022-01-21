package lapr.project.cargoShipUserStoriesTest;

import lapr.project.cargoShipUserStories.CenterOfMass;
import lapr.project.cargoShipUserStories.Coords;
import lapr.project.cargoShipUserStories.Shapes;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

class CenterOfMassTests {
    CenterOfMass centerOfMass;

    ArrayList<Shapes> boatShapes = new ArrayList<>();


    @BeforeEach
    void setup(){
        Coords p11 = new Coords(0, 48.8, 0);
        Coords p12 = new Coords(25,0,0);
        Coords p13 = new Coords(25,48.8,0);
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
        System.out.println("Shape " +(1)+ " and " +(2) + ": " + shape1.intersects(shape2)); 
        boatShapes.add(shape1);
        boatShapes.add(shape2);
        boatShapes.add(shape3);
        boatShapes.add(shape4);
        boatShapes.add(shape5);

        // for (int i = 0; i < boatShapes.size(); i++) {
        //     for(int k=0;k<boatShapes.size();k++){
        //         if(i!=k)
        //             System.out.println("Shape " +(i+1)+ " and " +(k+1) + ": " + boatShapes.get(i).intersects(boatShapes.get(k))); 
        //     }
        // }

        
        centerOfMass = new CenterOfMass(boatShapes);
    }
    // TODO Fix this tests
    /*
    @Test
    void calculateCentroidTest(){
        Coords centerOfMassOfShip = centerOfMass.calculateCentroid();
        System.out.println(centerOfMassOfShip.toString());
    //     assertEquals(197.56, centerOfMassOfShip.getX());
    //     assertEquals(30.47, centerOfMassOfShip.getY());
    //     assertEquals(25.62, centerOfMassOfShip.getZ());
    }


    @Test
    void addContainersTest(){
        Coords centerOfMassOfShip = centerOfMass.calculateCentroid();
        
        centerOfMass.addContainers(10.0, 10.0, 5.0, 100, centerOfMassOfShip);

    }

     */
}
