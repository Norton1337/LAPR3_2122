package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.model.locals.Locals;
/*
class CargoManifestTest {
    
    CargoManifest cargoManifest;

    @BeforeEach
    void setup(){
        Locals currentLocal = new Locals("Europe", "Portugal", 12345, "Leixões", "41.1827759,-8.7205652");
        Locals nextLocal = new Locals("Europe", "Poland", 67890, "Gdánsk", "54.393333,18.67");
        Vehicle vehicle = new Vehicle("truck");
        vehicle.setVehicleID("thisVehicleID");
        String date = "31/12/2020";
        String operationType="load";
        cargoManifest = new CargoManifest(currentLocal, vehicle, nextLocal, date, operationType);
        cargoManifest.setCargoManifestID("cargoManifestID");
    }

    @Test
    void setAndGetcargoManifestIDTest(){
        assertEquals("cargoManifestID", cargoManifest.getCargoManifestID());
        cargoManifest.setCargoManifestID("newcargoManifestID");
        assertEquals("newcargoManifestID", cargoManifest.getCargoManifestID());
    } 

    @Test
    void setAndGetCurrentLocalTest(){
        Locals thisLocal = new Locals("Europe", "Portugal", 12345, "Leixões", "41.1827759,-8.7205652");
        assertEquals(thisLocal.toString(), cargoManifest.getCurrentLocal().toString());
        Locals newLocal = new Locals("Europe", "Poland", 67890, "Gdánsk", "54.393333,18.67");
        cargoManifest.setCurrentLocal(newLocal);
        assertEquals(newLocal.toString(), cargoManifest.getCurrentLocal().toString());
    }

    @Test 
    void setAndGetVehicleTest(){
        Vehicle thisVehicle = new Vehicle("truck");
        thisVehicle.setVehicleID("thisVehicleID");
        assertEquals(thisVehicle.getVehicleID(), cargoManifest.getVehicle().getVehicleID());
        assertEquals(thisVehicle.getType(), cargoManifest.getVehicle().getType());
        Vehicle newVehicle = new Vehicle("ship");
        newVehicle.setVehicleID("newVehicleID");
        cargoManifest.setVehicle(newVehicle);
        assertEquals(newVehicle.getVehicleID(), cargoManifest.getVehicle().getVehicleID());
        assertEquals(newVehicle.getType(), cargoManifest.getVehicle().getType());
    }

    @Test
    void setAndGetNextLocalTest(){
        Locals nextLocal = new Locals("Europe", "Poland", 67890, "Gdánsk", "54.393333,18.67");
        assertEquals(nextLocal.toString(), cargoManifest.getNextLocal().toString());
        Locals newNextLocal = new Locals("Europe", "Portugal", 12345, "Leixões", "41.1827759,-8.7205652");
        cargoManifest.setNextLocal(newNextLocal);
        assertEquals(newNextLocal.toString(), cargoManifest.getNextLocal().toString());
    }

    @Test
    void setAndGetDateTest(){
        assertEquals("31/12/2020", cargoManifest.getDate());
        cargoManifest.setDate("30/12/2020");
        assertEquals("30/12/2020", cargoManifest.getDate());
    }

    @Test
    void setAndGetOperationTypeTest(){
        assertEquals("load", cargoManifest.getOperationType());
        cargoManifest.setOperationType("unload");
        assertEquals("unload", cargoManifest.getOperationType());
    }

    @Test
    void toStringTest(){
        String string = "CargoManifest{cargoManifestID:"+cargoManifest.getCargoManifestID()+"\n"
                        +"currentLocal:"+cargoManifest.getCurrentLocal()+"\n"
                        +"vehicle:"+cargoManifest.getVehicle()+"\n"
                        +"nextLocal:"+cargoManifest.getNextLocal()+"\n"
                        +"date:"+cargoManifest.getDate()+"\n"
                        +"operationType:"+cargoManifest.getOperationType()+"}";
        assertEquals(string, cargoManifest.toString());
        
            
    }
    



}
 */