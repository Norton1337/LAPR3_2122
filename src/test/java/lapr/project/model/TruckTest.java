package lapr.project.model;

import lapr.project.model.truck.Truck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TruckTest {

    Truck truck;

    @BeforeEach
    void setup() {
        truck = new Truck("plate");
        truck.setTruckId("truckId");
    }

    @Test
    void setAndGetTruckIdTest() {
        assertEquals("truckId", truck.getTruckId());
        truck.setTruckId("newTruckId");
        assertEquals("newTruckId", truck.getTruckId());
    }

    @Test
    void setAndGetTruckPlateTest() {
        assertEquals("plate", truck.getPlate());
        truck.setPlate("newPlate");
        assertEquals("newPlate", truck.getPlate());
    }

    @Test
    void toStringTest(){
        String string = "Truck{truckId='truckId', plate='plate'}";
        assertEquals(string, truck.toString());
    }

}
