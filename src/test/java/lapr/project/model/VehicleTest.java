package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VehicleTest {
    
    Vehicle vehicle;

    @BeforeEach
    void setup(){
        vehicle=new Vehicle("truck");
        vehicle.setVehicleID("vehicleID");
    }

    @Test
    void setAndGetVehicleIDTest() {
        assertEquals("vehicleID", vehicle.getVehicleID());
        vehicle.setVehicleID("newVehicleID");
        assertEquals("newVehicleID", vehicle.getVehicleID());
    }


    @Test
    void setAndGetTypeTest() {
        assertEquals("truck", vehicle.getType());
        vehicle.setType("ship");
        assertEquals("ship", vehicle.getType());
    }

}
