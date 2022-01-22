package lapr.project.cargoShipUserStoriesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import lapr.project.cargoShipUserStories.Coords;

class CoordsTest {
    Coords coords;
    
    @BeforeEach
    void setup(){
        coords = new Coords(1, 2, 3);
    }
    
    @Test
    void setAndGetX(){
        coords.setX(5);  
        assertEquals(5, coords.getX());
    }

    @Test
    void setAndGetY(){
        coords.setY(5);  
        assertEquals(5, coords.getY());
    }

    @Test
    void setAndGetZ(){
        coords.setZ(5);  
        assertEquals(5, coords.getZ());
    }

}
