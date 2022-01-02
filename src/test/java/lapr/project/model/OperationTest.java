package lapr.project.model;


import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.containers.Container;
import lapr.project.model.operation.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationTest {

    Operation operation;
    Operation operation2;
    CargoManifest cargoManifest;
    Container container;

    @BeforeEach
    void setup() {

        String nextLocal = "Gdánsk";
        String date = "31/12/2020";
        String operationType = "load";
        cargoManifest = new CargoManifest(nextLocal, date, operationType);
        cargoManifest.setId("cargoManifestID");

        operation = new Operation(1, 2, 3);
        operation.setId("operationID");
        operation.setOperation_warehouse("operation_warehouse");
    }

    @Test
    void setAndGetOperationWarehouseTest() {
        assertEquals("operation_warehouse", operation.getOperation_warehouse());
        operation.setOperation_warehouse("operation_warehouse2");
        assertEquals("operation_warehouse2", operation.getOperation_warehouse());
    }

    @Test
    void setAndGetIdTest() {
        assertEquals("operationID", operation.getId());
        operation.setId("newOperationID");
        assertEquals("newOperationID", operation.getId());
    }

    @Test
    void setAndGetContainerIdTest() {
        operation.setContainerId("containerId");
        assertEquals("containerId", operation.getContainerId());
    }

    @Test
    void setAndGetCargoManifestIdTest() {
        operation.setCargoManifestId("cargoManifestId");
        assertEquals("cargoManifestId", operation.getCargoManifestId());
    }

    @Test
    void setAndGetXTest() {
        operation.setX(1);
        assertEquals(1, operation.getX());
    }

    @Test
    void setAndGetYTest() {
        operation.setY(2);
        assertEquals(2, operation.getY());
    }

    @Test
    void setAndGetZTest() {
        operation.setZ(2);
        assertEquals(2, operation.getZ());
    }

}