package lapr.project.ui;

import lapr.project.controller.DataToBstController;
import lapr.project.controller.DataToKDTreeController;
import lapr.project.controller.ListAllShipsInfoController;
import lapr.project.controller.ToMatrixController;
import lapr.project.controller.model_controllers.*;
import lapr.project.data.*;
import lapr.project.data.mocks.*;
import lapr.project.model.locals.Locals;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;

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
        OperationController operationController = new OperationController(operationDBMock,localsDBMock,cargoManifestDBMock,containerDBMock);

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
        //CargoManifesUI = new CargoManifesUI();


        //SETUP
        countryUI.importCountriesAndBorders("Docs/Input/countries.csv", "Docs/Input/borders.csv");

        shipUI.importShips("Docs/Input/bships.csv");
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        portsAndWarehousesUI.importPorts("Docs/Input/bports.csv");


        LinkedList<Locals> portsAndWarehouses = localsController.getAllLocals();
        dataToKDTreeController.populateTree(portsAndWarehouses);

        seadistUI.importSeadist("Docs/Input/seadists.csv");



    }
}
