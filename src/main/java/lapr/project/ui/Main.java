package lapr.project.ui;

import lapr.project.controller.DataToBstController;
import lapr.project.controller.ListAllShipsInfoController;
import lapr.project.controller.ModelControllers.GeneratorController;
import lapr.project.controller.ModelControllers.PortsAndWarehousesController;
import lapr.project.controller.ModelControllers.ShipController;
import lapr.project.controller.ModelControllers.ShipPositionDataController;
import lapr.project.controller.ShipSummaryController;
import lapr.project.data.DBScripts.DataHandler;
import lapr.project.data.mocks.GeneratorDBMock;
import lapr.project.data.mocks.PortsAndWarehousesDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.data.mocks.ShipPositionDataDBMock;
import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.data.BSTFiles.BST;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.controller.HelperClasses.ShipSummary;
import lapr.project.model.PortsAndWarehouses.PortsAndWarehouses;
import lapr.project.utils.Utils;

import static lapr.project.utils.Utils.printList;

class Main {

    public static void main(String[] args) throws IOException, SQLException, ParseException {

        //DB
        ShipDBMock shipDBMock = new ShipDBMock();
        GeneratorDBMock generatorDBMock = new GeneratorDBMock();
        ShipPositionDataDBMock shipPositionDataDBMock = new ShipPositionDataDBMock();
        PortsAndWarehousesDBMock portsAndWarehousesDBMock = new PortsAndWarehousesDBMock();

        //CONTROLLERS DO MODEL
        ShipController shipController = new ShipController(shipDBMock, generatorDBMock);
        ShipPositionDataController shipPositionDataController = new ShipPositionDataController(shipDBMock, shipPositionDataDBMock);
        GeneratorController generatorController = new GeneratorController(shipDBMock, generatorDBMock);
        PortsAndWarehousesController portsAndWarehousesController = new PortsAndWarehousesController(shipDBMock, portsAndWarehousesDBMock);

        //CONTROLLERS
        DataToBstController dataToBstController = new DataToBstController();
        ListAllShipsInfoController listAllShipsInfoController = new ListAllShipsInfoController();


        //LEITURA DE FICHEIRO
        ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController);
        shipUI.importShips("Docs/Input/bships.csv");


        portsAndWarehousesController.addPortAndWarehouse(new PortsAndWarehouses("Europe","Cyprus",10136,
                "Larnaca","34.91666667,33.65"));
        portsAndWarehousesController.addPortAndWarehouse(new PortsAndWarehouses("Europe","Malta",10138,
                "Marsaxlokk","35.84194,14.54306"));
        portsAndWarehousesController.addPortAndWarehouse(new PortsAndWarehouses("Europe","Denmark",10358,
                "Aarhus","56.15,10.21666667"));


        printList(portsAndWarehousesController.getAllPortsAndWharehouse());


        //dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        //dataToBstController.populateBST();

        //BST t = dataToBstController.getShipBst();
        // System.out.println(t);
        /*
        System.out.println(shipSummaryController.toString());


        ShipAndData dataByMMSI = dataToBstController.getShipDetails("636015178");
        ShipAndData dataByIMO = dataToBstController.getShipDetails("IMO9601833");
        ShipAndData dataByCallSign = dataToBstController.getShipDetails("A8ZC7");
        System.out.println(dataByMMSI.toString());
        System.out.println(dataByIMO.toString());
        System.out.println(dataByCallSign.toString());

         */

        /*
        List<ShipAndData> andDataList = new ArrayList<>();

        for(Object elems : t.inOrder()){
            andDataList.add((ShipAndData) elems);
        }


        long startTime = System.currentTimeMillis();
        Utils.printList(listAllShipsInfoController.pairShips(andDataList));
        long stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime);




        //printList(listAllShipsInfoController.shipLog(andDataList));
        //System.out.println(dataToBstController.populateBST();

        /*
        List<ShipAndData> shipList = new ArrayList<>();

        for (int i = 0; i < shipController.getAllShips().size(); i++) {
            shipList.add(dataToBstController.getShipAndDataByMMSI(shipController.getAllShips().get(i).getMMSI()));
        }*/
        /*
        ShipAndData dataByMMSI = dataToBstController.getShipDetails("636015178");
        
        ShipSummaryController summaryController = new ShipSummaryController(dataByMMSI);
        ShipSummary shipSummary = summaryController.getShipSummary();
        System.out.println(summaryController.toString());
        */
//        MostTravelledShips mts = new MostTravelledShips();
//        TopShips ts = mts.getTopNShips(shipList, 5);
//

        DataHandler data = new DataHandler();
        data.scriptRunner("Docs/Database/CreateTable.sql");
    }
}
