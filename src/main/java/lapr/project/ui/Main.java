package lapr.project.ui;

import lapr.project.controller.DataToBstController;
import lapr.project.controller.ModelControllers.GeneratorController;
import lapr.project.controller.ModelControllers.ShipController;
import lapr.project.controller.ModelControllers.ShipPositionDataController;
import lapr.project.controller.MostTravelledShips.MostTravelledShips;
import lapr.project.controller.MostTravelledShips.TopShips;
import lapr.project.data.mocks.GeneratorDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.data.mocks.ShipPositionDataDBMock;
import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.BSTFolder.BST;
import static lapr.project.utils.Utils.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


class Main {


    public static void main(String[] args) throws IOException, SQLException {

        //DB
        ShipDBMock shipDBMock = new ShipDBMock();
        GeneratorDBMock generatorDBMock = new GeneratorDBMock();
        ShipPositionDataDBMock shipPositionDataDBMock = new ShipPositionDataDBMock();


        //CONTROLLERS
        ShipController shipController = new ShipController(shipDBMock, generatorDBMock);
        ShipPositionDataController shipPositionDataController = new ShipPositionDataController(shipDBMock, shipPositionDataDBMock);
        GeneratorController generatorController = new GeneratorController(shipDBMock, generatorDBMock);
        DataToBstController dataToBstController = new DataToBstController();



        ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController);

        

        long startTime = System.currentTimeMillis();
        shipUI.importShips("Docs/Input/bships.csv");


        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        BST t = dataToBstController.getShipBst();
        // System.out.println(t);

        
        long stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime);
        
        ShipAndData s =  dataToBstController.getShipAndDataByMMSI("636015178");
        



        //System.out.println(dataToBstController.populateBST();



        /*
        List <ShipAndData> shipList = new ArrayList<>();
        
        for (int i = 0; i < shipController.getAllShips().size(); i++) {
            shipList.add(dataToBstController.getShipAndDataByMMSI(shipController.getAllShips().get(i).getMMSI()));
            shipList.get(i).setShipPositonData(orderedByTime(shipList.get(shipList.size()-1).getShipPositonData()));
            
        }
        
        
        
        MostTravelledShips mts = new MostTravelledShips();
        TopShips ts = mts.getTopNShips(shipList, 5);
        System.out.println(ts.getListOfDistances());
        // System.out.println(ts.getListOfShip());
        
        // System.out.println(orderedByTime(dataToBstController.getShipAndDataByMMSI("352142000").getShipPositonData()).get(0));
        // System.out.println(orderedByTime(dataToBstController.getShipAndDataByMMSI("352142000").getShipPositonData()).get(dataToBstController.getShipAndDataByMMSI("352142000").getShipPositonData().size()-1).getCoordinates());
        // System.out.println(orderedByTime(dataToBstController.getShipAndDataByMMSI("352142000").getShipPositonData()).get(dataToBstController.getShipAndDataByMMSI("352142000").getShipPositonData().size()-2).getCoordinates());
        // System.out.println(orderedByTime(dataToBstController.getShipAndDataByMMSI("352142000").getShipPositonData()).get(dataToBstController.getShipAndDataByMMSI("352142000").getShipPositonData().size()-3).getCoordinates());
         for (int i = 0; i < orderedByTime(dataToBstController.getShipAndDataByMMSI("352142000").getShipPositonData()).size(); i++) {
             System.out.println(orderedByTime(dataToBstController.getShipAndDataByMMSI("352142000").getShipPositonData()).get(i));
         }
        */
    }
}

