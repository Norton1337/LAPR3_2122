package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.model.containers.Container;
import lapr.project.model.locals.Locals;

class OperationTest {

    Operation operation;
    Operation operation2;
    CargoManifest cargoManifest;
    Container container;

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

        container = new Container(1534, 4232, 52, 2, "ISO321",
                    24.5, 25.2, 43.2, 465.4, "S",
                    "Repair", "(x,y,z)", true);

        operation = new Operation(container, cargoManifest);
        operation2= new Operation(container, cargoManifest,1,2,3);
        operation.setOperationID("operationID");
        operation2.setOperationID("operationID2");
    }

    @Test
    void constructor2Test(){
        assertEquals("operationID2", operation2.getOperationID());
        assertEquals(container, operation2.getContainer());
        assertEquals(cargoManifest, operation2.getCargoManifest());
        assertEquals(1, operation2.getX());
        assertEquals(2, operation2.getY());
        assertEquals(3, operation2.getZ());
    }


    @Test
    void setAndGetOperationIDTest(){
        assertEquals("operationID", operation.getOperationID());
        operation.setOperationID("newOperationID");
        assertEquals("newOperationID", operation.getOperationID());
    }

    @Test
    void setAndGetContainerTest(){
        Container newContainer = new Container(1534, 4232, 52, 2, "ISO321",
                                24.5, 25.2, 43.2, 465.4, "S",
                                "Repair", "(x,y,z)", true);
        // assertEquals(newContainer.toString(), operation.getContainer().toString());
        assertEquals(newContainer.getCertificates(), operation.getContainer().getCertificates());
        assertEquals(newContainer.getCheckDigit(), operation.getContainer().getCheckDigit());
        assertEquals(newContainer.getContainerIdentification(), operation.getContainer().getContainerIdentification());
        assertEquals(newContainer.getContainerNumber(), operation.getContainer().getContainerNumber());
        assertEquals(newContainer.getContainerPosition(), operation.getContainer().getContainerPosition());
        assertEquals(newContainer.getISOCODE(), operation.getContainer().getISOCODE());
        assertEquals(newContainer.getMaxVolToBePacked(), operation.getContainer().getMaxVolToBePacked());
        assertEquals(newContainer.getMaxWeightToBePacked(), operation.getContainer().getMaxWeightToBePacked());
        assertEquals(newContainer.getMaxWeightWithContainer(), operation.getContainer().getMaxWeightWithContainer());
        assertEquals(newContainer.getRepairRecomendations(), operation.getContainer().getRepairRecomendations());
        assertEquals(newContainer.getShipID(), operation.getContainer().getShipID());
        assertEquals(newContainer.getWeightContainer(), operation.getContainer().getWeightContainer());

        Container newContainer2 = new Container(123, 345, 3, 2, "ISO322",
                                26.5, 27.2, 23.2, 123.4, "S",
                                "Repair", "(x,y,z)", true);
        operation.setContainer(newContainer2);
        assertEquals(newContainer2.getCertificates(), operation.getContainer().getCertificates());
        assertEquals(newContainer2.getCheckDigit(), operation.getContainer().getCheckDigit());
        assertEquals(newContainer2.getContainerIdentification(), operation.getContainer().getContainerIdentification());
        assertEquals(newContainer2.getContainerNumber(), operation.getContainer().getContainerNumber());
        assertEquals(newContainer2.getContainerPosition(), operation.getContainer().getContainerPosition());
        assertEquals(newContainer2.getISOCODE(), operation.getContainer().getISOCODE());
        assertEquals(newContainer2.getMaxVolToBePacked(), operation.getContainer().getMaxVolToBePacked());
        assertEquals(newContainer2.getMaxWeightToBePacked(), operation.getContainer().getMaxWeightToBePacked());
        assertEquals(newContainer2.getMaxWeightWithContainer(), operation.getContainer().getMaxWeightWithContainer());
        assertEquals(newContainer2.getRepairRecomendations(), operation.getContainer().getRepairRecomendations());
        assertEquals(newContainer2.getShipID(), operation.getContainer().getShipID());
        assertEquals(newContainer2.getWeightContainer(), operation.getContainer().getWeightContainer());
    }

    @Test
    void setAndGetCargoManifestTest(){
        assertEquals(cargoManifest.toString(), operation.getCargoManifest().toString());
        
        Locals currentLocal2 = new Locals("Europe", "Poland", 67890, "Gdánsk", "54.393333,18.67");
        Locals nextLocal2 = new Locals("Europe", "Portugal", 12345, "Leixões", "41.1827759,-8.7205652");
        Vehicle vehicle2 = new Vehicle("truck");
        vehicle2.setVehicleID("thisVehicleID");
        String date2 = "31/12/2020";
        String operationType2="load";
        CargoManifest newCargoManifest = new CargoManifest(currentLocal2, vehicle2, nextLocal2, date2, operationType2);
        newCargoManifest.setCargoManifestID("newCargoManifestID");
        operation.setCargoManifest(newCargoManifest);
        assertEquals(newCargoManifest.toString(), operation.getCargoManifest().toString());

    }
    
    @Test
    void setAndGetXTest(){
        operation.setX(1);
        assertEquals(1, operation.getX());
    }

    @Test
    void setAndGetYTest(){
        operation.setY(2);
        assertEquals(2, operation.getY());
    }

    @Test
    void setAndGetZTest(){
        operation.setZ(2);
        assertEquals(2, operation.getZ());
    }
   

}
