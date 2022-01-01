package lapr.project.controller.MostTravelledShips;

import lapr.project.controller.DataToBstController;
import lapr.project.controller.DataToKDTreeController;
import lapr.project.controller.ListAllShipsInfoController;
import lapr.project.controller.ToMatrixController;
import lapr.project.controller.model_controllers.*;
import lapr.project.data.graph_files.AdjacencyMatrixGraph;
import lapr.project.data.mocks.*;
import lapr.project.model.locals.Locals;
import lapr.project.ui.CountryUI;
import lapr.project.ui.PortsAndWarehousesUI;
import lapr.project.ui.SeadistUI;
import lapr.project.ui.ShipUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.printList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestControll {

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

    //CONTROLLERS DO MODEL
    VehiclesController vehiclesController = new VehiclesController(vehiclesDBMock, shipDBMock, trucksDBMock);
    ShipController shipController = new ShipController(shipDBMock, generatorDBMock);
    ShipPositionDataController shipPositionDataController = new ShipPositionDataController(shipDBMock, shipPositionDataDBMock);
    GeneratorController generatorController = new GeneratorController(shipDBMock, generatorDBMock);
    LocalsController localsController = new LocalsController(countryDBMock, localsDBMock);

    //CONTROLLERS
    DataToBstController dataToBstController = new DataToBstController();
    ListAllShipsInfoController listAllShipsInfoController = new ListAllShipsInfoController();
    DataToKDTreeController dataToKDTreeController = new DataToKDTreeController();
    CountryController countryController =  new CountryController(countryDBMock, bordersDBMock, localsDBMock);
    SeadistController seadistController = new SeadistController(localsDBMock, seadistDBMock);
    ToMatrixController matrixController = new ToMatrixController(localsDBMock, seadistDBMock, countryDBMock, bordersDBMock);

    //LEITURA DE FICHEIRO
    ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController, vehiclesController);
    PortsAndWarehousesUI portsAndWarehousesUI = new PortsAndWarehousesUI(localsController);
    CountryUI countryUI = new CountryUI(countryController);
    SeadistUI seadistUI = new SeadistUI(seadistController);




    @BeforeEach
    void setup(){
        countryUI.importCountriesAndBorders("Docs/Input/countries.csv", "Docs/Input/borders.csv");

        shipUI.importShips("Docs/Input/bships.csv");
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        portsAndWarehousesUI.importPorts("Docs/Input/bports.csv");


        LinkedList<Locals> portsAndWarehouses = localsController.getAllLocals();
        dataToKDTreeController.populateTree(portsAndWarehouses);

        seadistUI.importSeadist("Docs/Input/seadists.csv");


        //printList(portsAndWarehousesController.getAllPorts());
        //printList(portsAndWarehousesDBMock.getAllPortsAndWarehouses());

    }

    @Test
    void test(){
        //printList(countryController.getAllCountries());
        //printList(portsAndWarehousesDBMock.getAllPortsAndWarehouses());


        //printList(seadistController.getAllSeadist());
        //printList(portsAndWarehousesController.getAllPorts());

        matrixController.buildMatrix(3);
        matrixController.printMatrix();
    }

    @Test
    void leixoesTest(){
        AdjacencyMatrixGraph<Locals, Double> matrix = matrixController.buildMatrix(3);
        Locals leixoes = localsController.getLocalWithName("Leixoes");
        List<Object> outgoingVerticesList = matrix.outgoingVertices(leixoes);
        List<String> expectResult = Arrays.asList("Setubal", "Barcelona", "Dunkirk", "Horta", "Ponta Delgada", "Valencia", "Funchal");

        for(Object elems : outgoingVerticesList){
            Locals conv = (Locals) elems;
            int index = expectResult.indexOf(conv.getName());
            assertTrue(index != -1);
        }

        assertEquals(expectResult.size(), outgoingVerticesList.size());
    }


}
