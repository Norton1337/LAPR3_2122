package lapr.project.ui;

import lapr.project.controller.GeneratorController;
import lapr.project.controller.ShipController;
import lapr.project.controller.ShipPositionDataController;
import lapr.project.data.mocks.GeneratorDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.data.mocks.ShipPositionDataDBMock;
import lapr.project.model.MostTravelledShips.MostTravelledShips;
import lapr.project.model.MostTravelledShips.TopShips;

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



        ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController);



        long startTime = System.currentTimeMillis();
        shipUI.importShips("Docs/Input/bships.csv");
        long stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime);
        // System.out.println(shipPositionDataController.getShipData().get(0));

        /*
        MostTravelledShips mts = new MostTravelledShips();
        TopShips ts = mts.getTopNShips(shipController, shipPositionDataController, 5);
        System.out.println(ts.getListOfDistances());
         */
    }
}

