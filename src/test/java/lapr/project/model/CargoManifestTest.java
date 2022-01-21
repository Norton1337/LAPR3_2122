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
        cargoManifest.setVehicleId("vehicleID");
        cargoManifest.setCurrentLocalId("thisLocalID");
        cargoManifest.setCargo_recon("buk3h");
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
        assertEquals("thisLocalID", cargoManifest.getCurrentLocalId());
        cargoManifest.setCurrentLocalId("thisLocalID2");
        assertEquals("thisLocalID2", cargoManifest.getCurrentLocalId().toString());
    }

    @Test
    void setAndGetCargoReconTest(){
        assertEquals("buk3h", cargoManifest.getCargo_recon());
        cargoManifest.setCargo_recon("buk3g");
        assertEquals("buk3g", cargoManifest.getCargo_recon());
    }


    @Test
    void setAndGetVehicleIdTest() {
        assertEquals("vehicleID", cargoManifest.getVehicleId());
        cargoManifest.setVehicleId("vehicleID2");
        assertEquals("vehicleID2", cargoManifest.getVehicleId());
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

    @Test
    void toStringTest() {
        String string = "CargoManifest{" +
                "id='cargoManifestID'" +
                ", nextLocal='Gdánsk'" +
                ", date='31/12/2020'" +
                ", operationType='load'" +
                ", currentLocalId='thisLocalID'" +
                ", vehicleId='vehicleID'" +
                ", cargo_recon='buk3h'" +
                '}';
        assertEquals(string, cargoManifest.toString());
    }
}
 