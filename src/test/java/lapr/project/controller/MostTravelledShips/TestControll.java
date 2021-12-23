package lapr.project.controller.MostTravelledShips;

import lapr.project.controller.DataToBstController;
import lapr.project.controller.DataToKDTreeController;
import lapr.project.controller.ListAllShipsInfoController;
import lapr.project.controller.model_controllers.*;
import lapr.project.data.mocks.*;
import lapr.project.model.locals.Locals;
import lapr.project.ui.CountryUI;
import lapr.project.ui.PortsAndWarehousesUI;
import lapr.project.ui.ShipUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static lapr.project.utils.Utils.printList;

public class TestControll {

    //DB
    VehiclesDBMock vehiclesDBMock = new VehiclesDBMock();
    TrucksDBMock trucksDBMock = new TrucksDBMock();
    ShipDBMock shipDBMock = new ShipDBMock();
    GeneratorDBMock generatorDBMock = new GeneratorDBMock();
    ShipPositionDataDBMock shipPositionDataDBMock = new ShipPositionDataDBMock();
    PortsAndWarehousesDBMock portsAndWarehousesDBMock = new PortsAndWarehousesDBMock();
    CountryDBMock countryDBMock = new CountryDBMock();
    BordersDBMock bordersDBMock = new BordersDBMock();

    //CONTROLLERS DO MODEL
    VehiclesController vehiclesController = new VehiclesController(vehiclesDBMock, shipDBMock, trucksDBMock);
    ShipController shipController = new ShipController(shipDBMock, generatorDBMock);
    ShipPositionDataController shipPositionDataController = new ShipPositionDataController(shipDBMock, shipPositionDataDBMock);
    GeneratorController generatorController = new GeneratorController(shipDBMock, generatorDBMock);
    PortsAndWarehousesController portsAndWarehousesController = new PortsAndWarehousesController(countryDBMock, portsAndWarehousesDBMock);

    //CONTROLLERS
    DataToBstController dataToBstController = new DataToBstController();
    ListAllShipsInfoController listAllShipsInfoController = new ListAllShipsInfoController();
    DataToKDTreeController dataToKDTreeController = new DataToKDTreeController();
    CountryController countryController =  new CountryController(countryDBMock, bordersDBMock);


    //LEITURA DE FICHEIRO
    ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController, vehiclesController);
    PortsAndWarehousesUI portsAndWarehousesUI = new PortsAndWarehousesUI(portsAndWarehousesController);

    CountryUI countryUI = new CountryUI(countryController);




    @BeforeEach
    void setup(){
        countryUI.importCountriesAndBorders("Docs/Input/countries.csv", "Docs/Input/borders.csv");

        shipUI.importShips("Docs/Input/bships.csv");
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        portsAndWarehousesUI.importPorts("Docs/Input/sports.csv");
        LinkedList<Locals> portsAndWarehouses = portsAndWarehousesController.getAllPortsAndWharehouse();
        portsAndWarehouses.add(portsAndWarehouses.get(6));
        dataToKDTreeController.populateTree(portsAndWarehouses);

    }

    @Test
    void test(){
        printList(portsAndWarehousesDBMock.getAllPortsAndWarehouses());
    }


}
