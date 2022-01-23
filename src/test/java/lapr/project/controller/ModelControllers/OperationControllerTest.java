package lapr.project.controller.ModelControllers;

import lapr.project.controller.model_controllers.*;
import lapr.project.data.mocks.*;
import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.containers.Container;
import lapr.project.model.locals.Locals;
import lapr.project.model.operation.Operation;
import lapr.project.ui.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static lapr.project.utils.Utils.printMap;
import static lapr.project.utils.Utils.printList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OperationControllerTest {

    // DB
    OperationDBMock operationDBMock = new OperationDBMock();
    CargoManifestDBMock cargoManifestDBMock = new CargoManifestDBMock();
    ContainerDBMock containerDBMock = new ContainerDBMock();
    CountryDBMock countryDBMock = new CountryDBMock();
    LocalsDBMock localsDBMock = new LocalsDBMock();
    TrucksDBMock trucksDBMock = new TrucksDBMock();
    ShipDBMock shipDBMock = new ShipDBMock();
    VehiclesDBMock vehiclesDBMock = new VehiclesDBMock();
    GeneratorDBMock generatorDBMock = new GeneratorDBMock();
    ShipPositionDataDBMock shipPositionDataDBMock = new ShipPositionDataDBMock();
    LeasingDBMock leasingDBMock = new LeasingDBMock();
    UsersDBMock usersDBMock = new UsersDBMock();
    ClientDBMock clientDBMock = new ClientDBMock();

    // Controller
    LocalsController localsController = new LocalsController(countryDBMock, localsDBMock);

    VehiclesController vehiclesController = new VehiclesController(vehiclesDBMock, shipDBMock, trucksDBMock);
    ShipController shipController = new ShipController(shipDBMock, generatorDBMock);
    CargoManifestController cargoManifestController = new CargoManifestController(vehiclesDBMock, cargoManifestDBMock,
            operationDBMock, shipController);
    OperationController operationController = new OperationController(operationDBMock, containerDBMock,
            localsController, cargoManifestController, shipController, vehiclesController);
    ContainerController containerController = new ContainerController(containerDBMock, cargoManifestController,
            operationController, vehiclesController, localsController, clientDBMock, leasingDBMock, usersDBMock);
    ShipPositionDataController shipPositionDataController = new ShipPositionDataController(shipDBMock,
            shipPositionDataDBMock);
    GeneratorController generatorController = new GeneratorController(shipDBMock, generatorDBMock);

    // Leitura de Ficheiro
    PortsAndWarehousesUI portsAndWarehousesUI = new PortsAndWarehousesUI(localsController);
    WarehouseUI warehouseUI = new WarehouseUI(localsController);
    ContainerUI containerUI = new ContainerUI(containerController);
    CargoManifestUI cargoManifestUI = new CargoManifestUI(cargoManifestController);
    OperationsUI operationsUI = new OperationsUI(operationController);
    ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController, vehiclesController);
    TruckUI truckUI = new TruckUI(vehiclesController);

    public OperationControllerTest() {
        shipUI.importShips("Docs/Input/bships.csv");
        truckUI.importTrucks("Docs/Input/truck.csv");
        portsAndWarehousesUI.importPorts("Docs/Input/bports.csv");
        warehouseUI.importWarehouses("Docs/Input/warehouses.csv");
        containerUI.importContainers("Docs/Input/container.csv");
        cargoManifestUI.importCargoManifest("Docs/Input/cargoManifest.csv");
        operationsUI.importOperations("Docs/Input/operations.csv");
    }

    @Test
    void operationControllerTest() {
        OperationController operationController = new OperationController(operationDBMock, containerDBMock,
                localsController, cargoManifestController, shipController, vehiclesController);
    }

    @Test
    void addOperationTest() {
        CargoManifest newCargoManifest = new CargoManifest("NextLocal", "153624", "2022-13-02", "Load", "buska");
        cargoManifestController.addCargoManifest(newCargoManifest, "78-28-VR");
        Locals newWarehouse = new Locals("Portugal", 222, "Canedo", "22.98,23.45");
        newWarehouse.setId("id");
        newWarehouse.setPortId("123456");
        localsController.addWarehouse(newWarehouse, "224", "400");

        Container container = new Container(12345, 0, null, 0, 0, 0, 0, null, null, null);

        Operation operation = new Operation(1, 2, 3);
        operation.setCargoManifestId("cargoManifestId");
        operation.setContainerId("containerId");
        operation.setOperation_warehouse("operation_warehouse");
        operation.setId("id");

        assertTrue(operationController.addOperation(operation, container.getContainerNumber() + "",
                newCargoManifest.getCargo_recon(), newWarehouse.getLocalCode() + ""));

    }

    @Test
    void addOperationTest2() {
        CargoManifest newCargoManifest = new CargoManifest("NextLocal", "153624", "2022-13-02", "UnLoad", "buska");
        cargoManifestController.addCargoManifest(newCargoManifest, "78-28-VR");
        Locals newWarehouse = new Locals("Portugal", 222, "Canedo", "22.98,23.45");
        newWarehouse.setId("id");
        newWarehouse.setPortId("123456");
        localsController.addWarehouse(newWarehouse, "224", "400");

        Container container = new Container(12345, 0, null, 0, 0, 0, 0, null, null, null);

        Operation operation = new Operation(1, 2, 3);
        operation.setCargoManifestId("cargoManifestId");
        operation.setContainerId("containerId");
        operation.setOperation_warehouse("operation_warehouse");
        operation.setId("id");

        assertTrue(operationController.addOperation(operation, container.getContainerNumber() + "",
                newCargoManifest.getCargo_recon(), newWarehouse.getLocalCode() + ""));

    }

    @Test
    void getOccupancyRateTest() {
        assertEquals(0, operationController.getOccupancyRateAndContainersLeavingNextMonth(13012).size());
    }

    @Test
    void portMapTest() {
        List<String> list = new ArrayList<>();
        list.add("There aren't any operations on the port in that month");
        assertEquals(list, operationController.portMap(13012, "2020-12-12 12:10:00"));
        assertEquals(18, operationController.portMap(10358, "2022-01-01 00:00:00").size());
    }

    @Test
    void getOccupancyRateAndContainersLeavingNextMonthTest() {
        assertEquals(1, operationController.getOccupancyRateAndContainersLeavingNextMonth(246265).size());
    }

}
