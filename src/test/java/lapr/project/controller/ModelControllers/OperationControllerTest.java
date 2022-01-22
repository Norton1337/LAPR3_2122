package lapr.project.controller.ModelControllers;

import lapr.project.controller.model_controllers.*;
import lapr.project.data.mocks.*;
import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.locals.Locals;
import lapr.project.ui.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OperationControllerTest {

    //DB
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
    //Controller
    LocalsController localsController = new LocalsController(countryDBMock,localsDBMock);
    CargoManifestController cargoManifestController = new CargoManifestController(vehiclesDBMock
            ,cargoManifestDBMock,operationDBMock);
    VehiclesController vehiclesController = new VehiclesController(vehiclesDBMock,shipDBMock,trucksDBMock);
    ShipController shipController = new ShipController(shipDBMock,generatorDBMock);
    OperationController operationController = new OperationController(operationDBMock,containerDBMock,localsController
            ,cargoManifestController,shipController,vehiclesController);
    ContainerController containerController = new ContainerController(containerDBMock,cargoManifestController
            ,operationController,vehiclesController,localsController, leasingDBMock);
    ShipPositionDataController shipPositionDataController = new ShipPositionDataController(shipDBMock,shipPositionDataDBMock);
    GeneratorController generatorController = new GeneratorController(shipDBMock,generatorDBMock);

    //Leitura de Ficheiro
    PortsAndWarehousesUI portsAndWarehousesUI = new PortsAndWarehousesUI(localsController);
    WarehouseUI warehouseUI = new WarehouseUI(localsController);
    ContainerUI containerUI = new ContainerUI(containerController);
    CargoManifestUI cargoManifestUI = new CargoManifestUI(cargoManifestController);
    OperationsUI operationsUI = new OperationsUI(operationController);
    ShipUI shipUI = new ShipUI(shipController,shipPositionDataController,generatorController,vehiclesController);
    TruckUI truckUI = new TruckUI(vehiclesController);


    @BeforeEach
    void setup(){
        shipUI.importShips("Docs/Input/bships.csv");
        truckUI.importTrucks("Docs/Input/truck.csv");
        portsAndWarehousesUI.importPorts("Docs/Input/bports.csv");
        warehouseUI.importWarehouses("Docs/Input/warehouses.csv");
        containerUI.importContainers("Docs/Input/container.csv");
        cargoManifestUI.importCargoManifest("Docs/Input/cargoManifest.csv");
        operationsUI.importOperations("Docs/Input/operations.csv");
    }

    @Test
    void operationControllerTest(){
        OperationController operationController = new OperationController(operationDBMock,containerDBMock,
                localsController,cargoManifestController,shipController,vehiclesController);
    }

    @Test
    void addOperationTest(){
        CargoManifest newCargoManifest = new CargoManifest("NextLocal","CurrentLocal"
                ,"2022-13-02","Load","buska");
        cargoManifestController.addCargoManifest(newCargoManifest,"78-28-VR");
        Locals newWarehouse = new Locals("Portugal",222,"Canedo","22.98,23.45");
        localsController.addWarehouse(newWarehouse,"224","400");
        newWarehouse.setPortId("PortId");
        //TODO
    }
}
