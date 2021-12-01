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
        vehicle.setVehiclesId("vehiclesId");
    }

    @Test
    void setAndGetVehicleIDTest() {
        assertEquals("vehiclesId", vehicle.getVehiclesId());
        vehicle.setVehiclesId("newVehiclesID");
        assertEquals("newVehiclesID", vehicle.getVehiclesId());
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
