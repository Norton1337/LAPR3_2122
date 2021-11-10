package lapr.project.controller.MostTravelledShips;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import lapr.project.model.Ships.Ship;
import lapr.project.ui.ShipUI;
import lapr.project.controller.DataToBstController;
import lapr.project.controller.ModelControllers.GeneratorController;
import lapr.project.controller.ModelControllers.ShipController;
import lapr.project.controller.ModelControllers.ShipPositionDataController;
import lapr.project.data.mocks.GeneratorDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.data.mocks.ShipPositionDataDBMock;
import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.model.ShipPositionData.ShipPositonData;
import static lapr.project.utils.Utils.*;

class MostTravelledShipsTest {
    private List<Ship> listOfShips = new ArrayList<>();
    private List<Ship> listOfShips2 = new ArrayList<>();
    private List<Double> listOfDistances = new ArrayList<>();
    private List<Double> listOfDistances2 = new ArrayList<>();
    private List<Double> listOfSOG = new ArrayList<>();
    private List<Double> listOfSOG2 = new ArrayList<>();
    
    private TopShips topShipsOrdered;
    private TopShips topShipsUnordered;

    //DB
    private ShipDBMock shipDBMock = new ShipDBMock();
    private GeneratorDBMock generatorDBMock = new GeneratorDBMock();
    private ShipPositionDataDBMock shipPositionDataDBMock = new ShipPositionDataDBMock();


    //CONTROLLERS
    private ShipController shipController = new ShipController(shipDBMock, generatorDBMock);
    private ShipPositionDataController shipPositionDataController = new ShipPositionDataController(shipDBMock, shipPositionDataDBMock);
    private GeneratorController generatorController = new GeneratorController(shipDBMock, generatorDBMock);
    private DataToBstController dataToBstController = new DataToBstController();


    private ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController);

    

    
    


    @BeforeEach
    void startUp(){
        listOfShips.add(new Ship("211331642", "shipName3", "IMO3", "callSign3", 13, 5, 5, 62.5, 90.5));
        listOfShips.add(new Ship("211331641", "shipName2", "IMO2", "callSign2", 12, 5, 5, 61.5, 80.6));
        listOfShips.add(new Ship("211331640", "shipName1", "IMO1", "callSign1", 11, 5, 5, 60.5, 70.8));
        
        listOfShips2.add(new Ship("211331642", "shipName3", "IMO3", "callSign3", 13, 5, 5, 62.5, 90.5));
        listOfShips2.add(new Ship("211331640", "shipName1", "IMO1", "callSign1", 11, 5, 5, 60.5, 70.8));
        listOfShips2.add(new Ship("211331641", "shipName2", "IMO2", "callSign2", 12, 5, 5, 61.5, 80.6));

        listOfDistances.add(300.5);
        listOfDistances.add(200.5);
        listOfDistances.add(100.5);

        listOfDistances2.add(300.5);
        listOfDistances2.add(100.5);
        listOfDistances2.add(200.5);

        listOfSOG.add(30.8);
        listOfSOG.add(20.8);
        listOfSOG.add(10.8);
        
        listOfSOG2.add(30.8);
        listOfSOG2.add(10.8);
        listOfSOG2.add(20.8);
        

        topShipsOrdered = new TopShips(listOfShips, listOfDistances, listOfSOG);
        topShipsUnordered = new TopShips(listOfShips2, listOfDistances2, listOfSOG2);

        shipUI.importShips("Docs/Input/bships.csv");
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();
    }


    @Test
    void getTopNShipsTest(){
        MostTravelledShips mts = new MostTravelledShips();

        List <ShipAndData> shipList = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            shipList.add(dataToBstController.getShipAndDataByMMSI(shipController.getAllShips().get(i).getMMSI()));
            shipList.get(i).setShipPositonData(orderedByTime(shipList.get(shipList.size()-1).getShipPositonData()));
        }

        List <ShipAndData> shipList2 = new ArrayList<>();
        shipList2.add(dataToBstController.getShipAndDataByMMSI(shipController.getAllShips().get(0).getMMSI()));
        shipList2.get(0).setShipPositonData(orderedByTime(shipList2.get(shipList2.size()-1).getShipPositonData()));
        shipList2.add(dataToBstController.getShipAndDataByMMSI(shipController.getAllShips().get(1).getMMSI()));
        shipList2.get(1).setShipPositonData(orderedByTime(shipList2.get(shipList2.size()-1).getShipPositonData()));
        shipList2.add(dataToBstController.getShipAndDataByMMSI(shipController.getAllShips().get(3).getMMSI()));
        shipList2.get(2).setShipPositonData(orderedByTime(shipList2.get(shipList2.size()-1).getShipPositonData()));
        shipList2.add(dataToBstController.getShipAndDataByMMSI(shipController.getAllShips().get(2).getMMSI()));
        shipList2.get(3).setShipPositonData(orderedByTime(shipList2.get(shipList2.size()-1).getShipPositonData()));
        shipList2.add(dataToBstController.getShipAndDataByMMSI(shipController.getAllShips().get(4).getMMSI()));
        shipList2.get(4).setShipPositonData(orderedByTime(shipList2.get(shipList2.size()-1).getShipPositonData()));

        TopShips ts = mts.getTopNShips(shipList, 5);

 
        assertEquals(shipList2.get(0).getShip(), ts.getListOfShip().get(0));
        assertEquals(shipList2.get(1).getShip(), ts.getListOfShip().get(1));
        assertEquals(shipList2.get(2).getShip(), ts.getListOfShip().get(2));
        assertEquals(shipList2.get(3).getShip(), ts.getListOfShip().get(3));
        assertEquals(shipList2.get(4).getShip(), ts.getListOfShip().get(4));

    }

    @Test
    void orderListsTest(){
        MostTravelledShips mts = new MostTravelledShips();

        assertEquals(topShipsOrdered.getListOfDistances(), mts.orderLists(topShipsUnordered, 0, 3, 0, 0).getListOfDistances());
        assertEquals(topShipsOrdered.getListOfSOG(), mts.orderLists(topShipsUnordered, 0, 3, 0, 0).getListOfSOG());
        
        assertEquals(topShipsOrdered.getListOfShip().get(0).getMMSI(), mts.orderLists(topShipsUnordered, 0, 3, 0, 0).getListOfShip().get(0).getMMSI());
        assertEquals(topShipsOrdered.getListOfShip().get(1).getMMSI(), mts.orderLists(topShipsUnordered, 0, 3, 0, 0).getListOfShip().get(1).getMMSI());
        assertEquals(topShipsOrdered.getListOfShip().get(2).getMMSI(), mts.orderLists(topShipsUnordered, 0, 3, 0, 0).getListOfShip().get(2).getMMSI());
    }

    @Test
    void getTotalPerShipTest(){
        MostTravelledShips mts = new MostTravelledShips();
        List<ShipPositonData> posList = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("###.###");
        shipUI.importShips("Docs/Input/bships.csv");
        posList.add(shipPositionDataController.getShipData().get(0));
        posList.add(shipPositionDataController.getShipData().get(1));
        posList.add(shipPositionDataController.getShipData().get(2)); 
        
        assertEquals(df.format(465.587), df.format(mts.getTotalPerShip(posList)));
    }

    
}
