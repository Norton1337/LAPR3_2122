package lapr.project.model;

import lapr.project.model.cargoManifest.CargoManifest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CargoManifestTest {

    CargoManifest cargoManifest;

    @BeforeEach
    void setup() {
        String nextLocal = "Gdánsk";
        String date = "31/12/2020";
        String operationType = "load";
        cargoManifest = new CargoManifest(nextLocal, date, operationType);
        cargoManifest.setId("cargoManifestID");
    }

    @Test
    void setAndGetIdTest() {
        assertEquals("cargoManifestID", cargoManifest.getId());
        cargoManifest.setId("newcargoManifestID");
        assertEquals("newcargoManifestID", cargoManifest.getId());
    }

    @Test
    void setAndGetCurrentLocalIdTest() {
        cargoManifest.setCurrentLocalId("thisLocalID");
        assertEquals("thisLocalID", cargoManifest.getCurrentLocalId().toString());
    }

    @Test
    void setAndGetVehicleIdTest() {
        cargoManifest.setVehicleId("vehicleID");
        assertEquals("vehicleID", cargoManifest.getVehicleId());
    }

    @Test
    void setAndGetNextLocalTest() {
        assertEquals("Gdánsk", cargoManifest.getNextLocal());
        cargoManifest.setNextLocal("Leixões");
        assertEquals("Leixões", cargoManifest.getNextLocal());
    }

    @Test
    void setAndGetDateTest() {
        assertEquals("31/12/2020", cargoManifest.getDate());
        cargoManifest.setDate("30/12/2020");
        assertEquals("30/12/2020", cargoManifest.getDate());
    }

    @Test
    void setAndGetOperationTypeTest() {
        assertEquals("load", cargoManifest.getOperationType());
        cargoManifest.setOperationType("unload");
        assertEquals("unload", cargoManifest.getOperationType());
    }
}
 