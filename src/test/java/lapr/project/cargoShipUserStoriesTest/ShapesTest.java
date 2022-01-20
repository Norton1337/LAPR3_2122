package lapr.project.cargoShipUserStoriesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.cargoShipUserStories.Coords;
import lapr.project.cargoShipUserStories.Shapes;

public class ShapesTest {
    
    Shapes shapes;

    @BeforeEach
    void setup(){
        Coords coords1 = new Coords(1, 1, 1);
        Coords coords2 = new Coords(2, 2, 2);
        Coords coords3 = new Coords(3, 3, 3);
        shapes = new Shapes(coords1, coords2, coords3, 5.0, true);
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


