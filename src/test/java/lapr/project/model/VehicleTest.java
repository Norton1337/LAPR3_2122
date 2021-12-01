package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.model.vehicle.Vehicles;

class VehicleTest {

    
    Vehicles vehicle;

    @BeforeEach
    void setup(){
        vehicle=new Vehicles("truck");
        vehicle.setId("vehiclesId");
    }

    @Test
    void setAndGetVehicleIDTest() {
        assertEquals("vehiclesId", vehicle.getId());
        vehicle.setId("newVehiclesID");
        assertEquals("newVehiclesID", vehicle.getId());
    }


    @Test
    void setAndGetTypeTest() {
        assertEquals("truck", vehicle.getType());
        vehicle.setType("ship");
        assertEquals("ship", vehicle.getType());
    }

    @Test
    void toStringTest(){
        String string = "Vehicles{vehiclesId='vehiclesId', type='truck'}";
        assertEquals(string, vehicle.toString());
    }
}
