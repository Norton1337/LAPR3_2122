package lapr.project.ui;

import lapr.project.controller.DataToBstController;
import lapr.project.controller.ModelControllers.GeneratorController;
import lapr.project.controller.ModelControllers.ShipController;
import lapr.project.controller.ModelControllers.ShipPositionDataController;
import lapr.project.data.mocks.GeneratorDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.data.mocks.ShipPositionDataDBMock;
import lapr.project.BSTFolder.BST;

import java.io.IOException;
import java.sql.SQLException;

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
        System.out.println(t);


        long stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime);

        dataToBstController.getShipAndDataByMMSI("636015178");


        // System.out.println(shipPositionDataController.getShipData().get(0));


        //System.out.println(dataToBstController.populateBST();
        
        // MostTravelledShips mts = new MostTravelledShips();
        // TopShips ts = mts.getTopNShips(shipController, shipPositionDataController, 5);
        // System.out.println(ts.getListOfDistances());
         
    }
}

