package lapr.project.model;

import lapr.project.model.vehicle.Vehicles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleTest {


    Vehicles vehicle;

    @BeforeEach
    void setup() {
        vehicle = new Vehicles("truck");
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
    void toStringTest() {
        String string = "Vehicles{vehiclesId='vehiclesId', type='truck'}";
        assertEquals(string, vehicle.toString());
    }
}
