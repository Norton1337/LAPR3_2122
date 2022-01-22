package lapr.project.cargoShipUserStoriesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.cargoShipUserStories.Coords;
import lapr.project.cargoShipUserStories.Shapes;

class ShapesTest {
    
    Shapes shapes;

    @BeforeEach
    void setup(){
        Coords coords1 = new Coords(1, 1, 1);
        Coords coords2 = new Coords(2, 2, 2);
        Coords coords3 = new Coords(3, 3, 3);
        shapes = new Shapes(coords1, coords2, coords3, 5.0, true);
    }

    @Test
    void testException(){
        Coords p11 = new Coords(0, 0, 0);
        Coords p12 = new Coords(0,0,0);
        Coords p13 = new Coords(0,1,0);
        
        try {
            new Shapes(p11, p12, p13, 51.25, true);
        } catch (Exception e) {
            assertTrue(true);
        }


        Coords p21 = new Coords(0, 0, 0);
        Coords p22 = new Coords(0,0,0);
        Coords p23 = new Coords(1,0,0);
        
        try {
            new Shapes(p21, p22, p23, 51.25, true);
        } catch (Exception e) {
            assertTrue(true);
        }


    }


    @Test
    void intersectsTest(){
        Coords p11 = new Coords(0, 48.8, 0);
        Coords p12 = new Coords(21,0,0);
        Coords p13 = new Coords(21,48.8,0);
        Shapes shape1 = new Shapes(p11, p12, p13, 51.25, true);

        Coords p21 = new Coords(21, 48.8, 0);
        Coords p22 = new Coords(21,0,0);
        Coords p23 = new Coords(305,0,0);
        Shapes shape2 = new Shapes(p21, p22, p23, 51.25, false);
        assertFalse(shape1.intersects(shape2));
        assertFalse(shape2.intersects(shape1));
        Coords p31 = new Coords(91, 48, 5);
        Coords p32 = new Coords(133,48,5);
        Coords p33 = new Coords(91,90,5);
        Shapes shape3 = new Shapes(p31, p32, p33, 41.25, false);

        assertTrue(shape3.intersects(shape2));
        assertTrue(shape2.intersects(shape3));

        Coords p41 = new Coords(1, 0, 0);
        Coords p42 = new Coords(1,1,0);
        Coords p43 = new Coords(0,1,0);
        Shapes shape4 = new Shapes(p41, p42, p43, 5, false);

        Coords p51 = new Coords(1,5, 0);
        Coords p52 = new Coords(1,10,0);
        Coords p53 = new Coords(0,10,0);
        Shapes shape5 = new Shapes(p51, p52, p53, 5, false);
        
        assertFalse(shape4.intersects(shape5));
        assertFalse(shape5.intersects(shape4));


        
    }

    @Test
    void setAndGetP123Test(){
        Coords coords1 = new Coords(4, 4, 4);
        Coords coords2 = new Coords(5, 5, 5);
        Coords coords3 = new Coords(6, 6, 6);
        shapes.setP1(coords1);
        shapes.setP2(coords2);
        shapes.setP3(coords3);
        String s1 = "X: " + coords1.getX() +",\n" + "Y: " + coords1.getY() + ",\n" + "Z: " + coords1.getZ();
        String s2 = "X: " + coords2.getX() +",\n" + "Y: " + coords2.getY() + ",\n" + "Z: " + coords2.getZ();
        String s3 = "X: " + coords3.getX() +",\n" + "Y: " + coords3.getY() + ",\n" + "Z: " + coords3.getZ();
        assertEquals(s1, shapes.getP1().toString());
        assertEquals(s2, shapes.getP2().toString());
        assertEquals(s3, shapes.getP3().toString());
    }

    @Test
    void setAndGetWidthTest(){
        shapes.setWidth(5.0);
        assertEquals(5.0, shapes.getWidth());
    }

    @Test
    void setAndGetTriangleTest(){
        shapes.setIsTriangle(false);
        assertEquals(false, shapes.isTriangle());
    }

    @Test
    void setAndGetVolumeTest(){
        shapes.setVolume(5.0);
        assertEquals(5.0, shapes.getVolume());
    }

}


