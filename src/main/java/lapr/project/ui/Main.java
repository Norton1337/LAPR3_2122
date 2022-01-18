package lapr.project.ui;

import lapr.project.controller.DataToBstController;
import lapr.project.controller.DataToKDTreeController;
import lapr.project.controller.ListAllShipsInfoController;
import lapr.project.controller.ToMatrixController;
import lapr.project.controller.model_controllers.*;
import lapr.project.data.mocks.*;
import lapr.project.model.locals.Locals;
import lapr.project.utils.Utils;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;

import static lapr.project.utils.Utils.printList;

class Main {

    public static void main(String[] args) throws IOException, SQLException, ParseException {

        //DB
        VehiclesDBMock vehiclesDBMock = new VehiclesDBMock();
        TrucksDBMock trucksDBMock = new TrucksDBMock();
        ShipDBMock shipDBMock = new ShipDBMock();
        GeneratorDBMock generatorDBMock = new GeneratorDBMock();
        ShipPositionDataDBMock shipPositionDataDBMock = new ShipPositionDataDBMock();
        LocalsDBMock localsDBMock = new LocalsDBMock();
        CountryDBMock countryDBMock = new CountryDBMock();
        BordersDBMock bordersDBMock = new BordersDBMock();
        SeadistDBMock seadistDBMock = new SeadistDBMock();
        OperationDBMock operationDBMock = new OperationDBMock();
        CargoManifestDBMock cargoManifestDBMock = new CargoManifestDBMock();
        ContainerDBMock containerDBMock = new ContainerDBMock();


        //CONTROLLERS DO MODEL
        VehiclesController vehiclesController = new VehiclesController(vehiclesDBMock, shipDBMock, trucksDBMock);
        ShipController shipController = new ShipController(shipDBMock, generatorDBMock);
        ShipPositionDataController shipPositionDataController = new ShipPositionDataController(shipDBMock, shipPositionDataDBMock);
        GeneratorController generatorController = new GeneratorController(shipDBMock, generatorDBMock);
        LocalsController localsController = new LocalsController(countryDBMock, localsDBMock);
        CargoManifestController cargoManifestController = new CargoManifestController(vehiclesDBMock, cargoManifestDBMock, operationDBMock);
        ContainerController containerController = new ContainerController(containerDBMock);
        OperationController operationController = new OperationController(operationDBMock,localsDBMock,cargoManifestDBMock,containerDBMock);
        TruckController truckController = new TruckController(trucksDBMock);

        //CONTROLLERS
        DataToBstController dataToBstController = new DataToBstController();
        ListAllShipsInfoController listAllShipsInfoController = new ListAllShipsInfoController();
        DataToKDTreeController dataToKDTreeController = new DataToKDTreeController();
        CountryController countryController = new CountryController(countryDBMock, bordersDBMock, localsDBMock);
        SeadistController seadistController = new SeadistController(localsDBMock, seadistDBMock);
        ToMatrixController matrixController = new ToMatrixController(localsDBMock, seadistDBMock, countryDBMock, bordersDBMock);


        //LEITURA DE FICHEIRO
        ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController, vehiclesController);
        PortsAndWarehousesUI portsAndWarehousesUI = new PortsAndWarehousesUI(localsController);
        CountryUI countryUI = new CountryUI(countryController);
        SeadistUI seadistUI = new SeadistUI(seadistController);
        ContainerUI containerUI = new ContainerUI(containerController);
        WarehouseUI warehouseUI = new WarehouseUI(localsController);
        CargoManifestUI cargoManifestUI = new CargoManifestUI(cargoManifestController);
        OperationsUI operationsUI = new OperationsUI(operationController);
        TruckUI truckUI = new TruckUI(vehiclesController);

        //SETUP
        countryUI.importCountriesAndBorders("Docs/Input/countries.csv", "Docs/Input/borders.csv");

        truckUI.importTrucks("Docs/Input/truck.csv");
        shipUI.importShips("Docs/Input/bships.csv");
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();


        portsAndWarehousesUI.importPorts("Docs/Input/bports.csv");


        LinkedList<Locals> portsAndWarehouses = localsController.getAllLocals();
        dataToKDTreeController.populateTree(portsAndWarehouses);

        seadistUI.importSeadist("Docs/Input/seadists.csv");

        containerUI.importContainers("Docs/Input/container.csv");
        warehouseUI.importWarehouses("Docs/Input/warehouses.csv");
        cargoManifestUI.importCargoManifest("Docs/Input/cargoManifest.csv");
        operationsUI.importOperations("Docs/Input/operations.csv");

        printList(Utils.cargosOrderedByTime(cargoManifestController.getAllCargoManifest()));
        //printList(vehiclesController.getAllVehicles());
        //printList(cargoManifestController.containers_to_offload("229767000"));
        System.out.println("\n\n\n\n");
        printList(vehiclesController.getAllShips());
        //printList(vehiclesController.getAllTrucks());
        //printList(operationController.getAllOperations());

    }
}
