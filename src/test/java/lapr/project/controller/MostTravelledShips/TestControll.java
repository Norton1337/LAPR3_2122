package lapr.project.controller.MostTravelledShips;

import lapr.project.controller.DataToBstController;
import lapr.project.controller.DataToKDTreeController;
import lapr.project.controller.ListAllShipsInfoController;
import lapr.project.controller.ToMatrixController;
import lapr.project.controller.helper_classes.CountryColour;
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

import java.util.*;

import static lapr.project.utils.Utils.*;
import static org.junit.jupiter.api.Assertions.*;

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
    CountryController countryController = new CountryController(countryDBMock, bordersDBMock, localsDBMock);
    SeadistController seadistController = new SeadistController(localsDBMock, seadistDBMock);
    ToMatrixController matrixController = new ToMatrixController(localsDBMock, seadistDBMock, countryDBMock, bordersDBMock);

    //LEITURA DE FICHEIRO
    ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController, vehiclesController);
    PortsAndWarehousesUI portsAndWarehousesUI = new PortsAndWarehousesUI(localsController);
    CountryUI countryUI = new CountryUI(countryController);
    SeadistUI seadistUI = new SeadistUI(seadistController);


    @BeforeEach
    void setup() {
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
    void test() {
        AdjacencyMatrixGraph<Locals, Double> t = matrixController.buildFreightNetwork(3);

        if (Objects.equals(readFromProp("debug", "src/main/resources/application.properties"), "1")){

            //matrixController.printFreightNetworkMatrix();
            matrixController.printLandMaritime();
        }
    }

    @Test
    void leixoesTest() {
        AdjacencyMatrixGraph<Locals, Double> matrix = matrixController.buildFreightNetwork(3);
        Locals leixoes = localsController.getLocalWithName("Leixoes");
        List<Object> outgoingVerticesList = matrix.outgoingVertices(leixoes);
        List<String> expectResult = Arrays.asList("Setubal", "Barcelona", "Dunkirk", "Horta", "Ponta Delgada", "Valencia", "Funchal");

        for (Object elems : outgoingVerticesList) {
            Locals conv = (Locals) elems;
            int index = expectResult.indexOf(conv.getName());
            assertTrue(index != -1);
        }

        assertEquals(expectResult.size(), outgoingVerticesList.size());
    }


    @Test
    void colourMatrix() {
        List<CountryColour> expResult = new LinkedList<>();
        expResult.add(new CountryColour("Portugal", 0));
        expResult.add(new CountryColour("Spain", 1));
        expResult.add(new CountryColour("France", 0));
        expResult.add(new CountryColour("Luxembourg", 3));

        matrixController.buildMatrixToColour();
        List<CountryColour> result = matrixController.colorMatrix();

        if (Objects.equals(readFromProp("debug", "src/main/resources/application.properties"), "1")){
            printList(result);
        }

        //System.out.printf("%s   %s  %s\n",result.get(0), expResult.get(0), result.get(0).getCountry().equals(expResult.get(0).getCountry()));

    }


    @Test
    void portsTest(){
        // Create new matrix for tests
        AdjacencyMatrixGraph<Locals, Double> t = matrixController.buildFreightNetwork(3);
        Map<Locals, Double> portsList;


        portsList = matrixController.centralPorts(t, 3);


        if (Objects.equals(readFromProp("debug", "src/main/resources/application.properties"), "1")){
            printMap(portsList);
        }

        /*
        for (Locals ports : portsList.keySet()){
            //System.out.println(ports);
            if (ports.getType().equals("Port")){
                portsList.replace(ports,   portsList.get(ports) +1);
            }
        }
         System.out.println(portsList);

         */

    }

    @Test
    void pathsTest(){
        AdjacencyMatrixGraph<Locals, Double> t = matrixController.buildFreightNetwork(3);
        List<Locals> locals = new ArrayList<>();
        List<String> places = new ArrayList<>();
        places.add("Prague");
        places.add("Paris");
        //places.add("Vienna");
        //locals = matrixController.shortestThroughNPlaces(t, "Madrid", "Barcelona", places);
        //printList(locals);
        /*


        LinkedList<Locals> path = new LinkedList<>();

        var madrid = localsController.getLocalWithName("Madrid");
        var lisbon = localsController.getLocalWithName("Lisbon");

        var genoa = localsController.getLocalWithName("Genoa");
        var paris =localsController.getLocalWithName("Paris");

        EdgeAsDoubleGraphAlgorithms.shortestPath(t, genoa, paris,path);
        System.out.println("Genoa -> Paris");
        printList(path);


        EdgeAsDoubleGraphAlgorithms.shortestPath(t, paris, madrid,path);
        System.out.println("Paris -> Madrid");
        printList(path);

        EdgeAsDoubleGraphAlgorithms.shortestPath(t, madrid, lisbon,path);
        System.out.println("Madrid -> Lisbon");
        printList(path);

         */
    }

    @Test
    void centralPortsTest(){

        AdjacencyMatrixGraph<Locals, Double> matrix = new AdjacencyMatrixGraph<>();

        Locals barcelona = localsController.getLocalWithName("Barcelona");
        Locals liverpool = localsController.getLocalWithName("Liverpool");
        Locals brest = localsController.getLocalWithName("Brest");
        Locals gdanks = localsController.getLocalWithName("Gdansk");
        Locals hamburg = localsController.getLocalWithName("Hamburg");
        Locals genoa = localsController.getLocalWithName("Genoa");

        matrix.insertVertex(barcelona);
        matrix.insertVertex(liverpool);
        matrix.insertVertex(brest);
        matrix.insertVertex(gdanks);
        matrix.insertVertex(hamburg);
        matrix.insertVertex(genoa);

        matrix.insertEdge(barcelona, liverpool, 35.0);
        matrix.insertEdge(barcelona, brest, 15.0);
        matrix.insertEdge(barcelona, genoa, 10.0);
        matrix.insertEdge(brest, liverpool, 10.0);
        matrix.insertEdge(brest, genoa, 30.0);
        matrix.insertEdge(barcelona, hamburg, 15.0);

        Map<Locals, Double> centralPortsMap = new LinkedHashMap<>();
        Map<Locals, Double> correctMap = new LinkedHashMap<>();

        correctMap.put(barcelona, 10.0);
        correctMap.put(brest,6.0);

        centralPortsMap = matrixController.centralPorts(matrix, 2);

        assertEquals(centralPortsMap, correctMap);
        printMap(centralPortsMap);



    }

    @Test
    void maritimePathMethodTest(){

        AdjacencyMatrixGraph<Locals, Double> maritimeMatrix = new AdjacencyMatrixGraph<>();

        Locals liverpool = localsController.getLocalWithName("Liverpool");
        Locals brest = localsController.getLocalWithName("Brest");
        Locals genoa = localsController.getLocalWithName("Genoa");
        Locals barcelona = localsController.getLocalWithName("Barcelona");
        //Locals rome = localsController.getLocalWithName("Rome");
        Locals madrid = localsController.getLocalWithName("Madrid");

        maritimeMatrix.insertVertex(liverpool);
        maritimeMatrix.insertVertex(brest);
        maritimeMatrix.insertVertex(genoa);
        maritimeMatrix.insertVertex(barcelona);
        maritimeMatrix.insertVertex(madrid); // Capital

        maritimeMatrix.insertEdge(barcelona, liverpool, 55.0);
        maritimeMatrix.insertEdge(barcelona, genoa, 45.0);
        maritimeMatrix.insertEdge(barcelona, madrid, 70.0);
        maritimeMatrix.insertEdge(liverpool, brest, 35.0);
        maritimeMatrix.insertEdge(genoa, brest, 50.0);

        //System.out.println(maritimeMatrix);

        List<Locals> correctPath = new LinkedList<>();

        correctPath.add(0, barcelona);
        correctPath.add(1, liverpool);
        correctPath.add(2, brest);

        List<Locals> maritimePath = matrixController.maritimePath(maritimeMatrix, barcelona, brest);
        List<Locals> maritimePath2 = matrixController.maritimePath(maritimeMatrix, barcelona, madrid);

        //printList(maritimePath);

        assertEquals(maritimePath,correctPath);
        assertNull(maritimePath2);


    }

    @Test
    void landPathMethodTest(){

        AdjacencyMatrixGraph<Locals, Double> landMatrix = new AdjacencyMatrixGraph<>();

        Locals valencia = localsController.getLocalWithName("Valencia"); //P
        Locals bourgas = localsController.getLocalWithName("Bourgas"); //P
        Locals madrid = localsController.getLocalWithName("Madrid");
        Locals paris = localsController.getLocalWithName("Paris");
        Locals oslo = localsController.getLocalWithName("Oslo");
        Locals berlin = localsController.getLocalWithName("Berlin");
        Locals prague = localsController.getLocalWithName("Prague");

        landMatrix.insertVertex(valencia);
        landMatrix.insertVertex(bourgas);
        landMatrix.insertVertex(madrid);
        landMatrix.insertVertex(paris);
        landMatrix.insertVertex(oslo);
        landMatrix.insertVertex(berlin);
        landMatrix.insertVertex(prague);

        landMatrix.insertEdge(valencia, madrid, 25.0);
        landMatrix.insertEdge(valencia, paris, 40.0);
        landMatrix.insertEdge(bourgas, berlin, 70.0);
        landMatrix.insertEdge(bourgas, paris, 120.0);

        landMatrix.insertEdge(madrid, oslo, 45.0);
        landMatrix.insertEdge(madrid, prague, 55.0);
        landMatrix.insertEdge(oslo, berlin, 40.0);
        landMatrix.insertEdge(prague, berlin, 25.0);
        landMatrix.insertEdge(berlin, paris, 35.0);

        //System.out.println(landMatrix);

        List<Locals> landPath1 = matrixController.landPath(landMatrix, madrid, berlin); // C - C
        List<Locals> landPath2 = matrixController.landPath(landMatrix, oslo, bourgas); // C - P
        List<Locals> landPath3 = matrixController.landPath(landMatrix, valencia, berlin); // P - C
        List<Locals> landPath4 = matrixController.landPath(landMatrix, valencia, bourgas); // P - P

        /**
         * Origin and destiny Capital
         */
        List<Locals> correctPath1 = new LinkedList<>();
        correctPath1.add(0, madrid);
        correctPath1.add(1, prague);
        correctPath1.add(2, berlin);

        assertEquals(landPath1, correctPath1);
        //printList(landPath1);

        /**
         *  Origin Capital and destiny Port
         */
        List<Locals> correctPath2 = new LinkedList<>();
        correctPath2.add(0, oslo);
        correctPath2.add(1, berlin);
        correctPath2.add(2, bourgas);

        assertEquals(landPath2, correctPath2);
        //printList(landPath2);

        /**
         * Origin Port and destiny Capital
         */

        List<Locals> correctPath3 = new LinkedList<>();
        correctPath3.add(0, valencia);
        correctPath3.add(1, paris);
        correctPath3.add(2, berlin);

        assertEquals(landPath3, correctPath3);
        //printList(landPath3);


        /**
         * Origin and destiny Port
         */
        List<Locals> correctPath4 = new LinkedList<>();
        correctPath4.add(0, valencia);
        correctPath4.add(1, paris);
        correctPath4.add(2, berlin);
        correctPath4.add(3, bourgas);

        assertEquals(landPath4, correctPath4);
        //printList(landPath4);
    }

    @Test
    void shortestThroughNPlacesTest(){

        AdjacencyMatrixGraph<Locals, Double> matrix = new AdjacencyMatrixGraph<>();

        Locals madrid = localsController.getLocalWithName("Madrid");
        Locals london = localsController.getLocalWithName("London");
        Locals hamburg = localsController.getLocalWithName("Hamburg");
        Locals paris = localsController.getLocalWithName("Paris");
        Locals rome = localsController.getLocalWithName("Rome");
        Locals prague = localsController.getLocalWithName("Prague");
        Locals warsaw = localsController.getLocalWithName("Warsaw");

        matrix.insertVertex(madrid);
        matrix.insertVertex(london);
        matrix.insertVertex(hamburg);
        matrix.insertVertex(paris);
        matrix.insertVertex(rome);
        matrix.insertVertex(prague);
        matrix.insertVertex(warsaw);

        matrix.insertEdge(madrid, london, 10.0);
        matrix.insertEdge(madrid, rome, 80.0);
        matrix.insertEdge(london, paris, 25.0);
        matrix.insertEdge(paris, rome, 15.0);
        matrix.insertEdge(paris, hamburg, 10.0);
        matrix.insertEdge(paris, prague, 30.0);
        matrix.insertEdge(prague, rome, 10.0);
        matrix.insertEdge(prague, warsaw, 30.0);

        List<Locals> path = new LinkedList<>();
        List<Locals> correctPath = new LinkedList<>();
        List<String> placesToPass = new LinkedList<>();

        placesToPass.add("Hamburg");
        placesToPass.add("Rome");

        correctPath.add(0, warsaw);
        correctPath.add(1, prague);
        correctPath.add(2, rome);
        correctPath.add(3, paris);
        correctPath.add(4, hamburg);
        correctPath.add(5, paris);
        correctPath.add(6, london);
        correctPath.add(7, madrid);

        path = matrixController.shortestThroughNPlaces(matrix, warsaw, madrid, placesToPass);

        assertEquals(path, correctPath);

        //printList(path);

    }

}
