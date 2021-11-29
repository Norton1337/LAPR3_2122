package lapr.project.controller;

import lapr.project.Dtos.ShipPairsDTO;
import lapr.project.Dtos.ShipsMovementDto;
import lapr.project.controller.ModelControllers.GeneratorController;
import lapr.project.controller.ModelControllers.ShipController;
import lapr.project.controller.ModelControllers.ShipPositionDataController;
import lapr.project.data.mocks.GeneratorDBMock;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.data.mocks.ShipPositionDataDBMock;
import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.ui.ShipUI;
import lapr.project.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static lapr.project.utils.Utils.readFromProp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListAllShipsInfoControllerTest {


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
    ListAllShipsInfoController listAllShipsInfoController = new ListAllShipsInfoController();
    MostTravelledShipsController mostTravelledShips = new MostTravelledShipsController();




    @BeforeEach
    void beforeAll() {
        shipUI.importShips("Docs/Input/bships.csv");
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();



    }


    @Test
    void shipLogTest() throws IOException{
        List<ShipAndData> andDataList = new ArrayList<>();

        for(Object elems : dataToBstController.getShipBst().inOrder()){
            andDataList.add((ShipAndData) elems);
        }




        List<ShipsMovementDto> allData = listAllShipsInfoController.shipLog(andDataList);
        if(readFromProp("debug","src/main/resources/application.properties").equals("1"))Utils.printList(allData);
        assertTrue(allData.size() > 10);

        for(ShipsMovementDto elems : allData){
            ShipAndData shipAndData = dataToBstController.getShipDetails(elems.getMMSI());
            double travelDist = mostTravelledShips.getTotalPerShip(shipAndData.getShipPositonData());

            assertEquals(elems.getMMSI(), shipAndData.getShip().getMMSI());
            assertEquals(elems.getTravelledDistance(), String.valueOf(travelDist));
        }
   }


    @Test
    void pairShips() throws IOException {

        List<ShipAndData> andDataList = new ArrayList<>();

        for(Object elems : dataToBstController.getShipBst().inOrder()){
            andDataList.add((ShipAndData) elems);
        }

        System.out.println(andDataList.size());

        if(readFromProp("debug","src/main/resources/application.properties").equals("1"))System.out.println("Ship1MMSI    Ship2MMSI      Movs      TravelDist  Movs     TravelDist");
        List<ShipPairsDTO> pairShips = listAllShipsInfoController.pairShips(andDataList);
        if(readFromProp("debug","src/main/resources/application.properties").equals("1"))Utils.printList(pairShips);

        List<ShipPairsDTO> expectResult = new ArrayList<>();
        expectResult.add(new ShipPairsDTO("366759530","366772760","1217","1238","78","335,179"));


        assertTrue(pairShips.size() > 0);
        boolean flag = false;

        for(ShipPairsDTO elems : pairShips){
            if (elems.getShip1MMSI().equals(expectResult.get(0).getShip1MMSI()) &&
                    elems.getShip2MMSI().equals(expectResult.get(0).getShip2MMSI()) &&
                    Math.abs(Double.parseDouble(elems.getShip1Traveldistance())-Double.parseDouble(expectResult.get(0).getShip1Traveldistance().replace(",",".")))<1.0  &&
                    Math.abs(Double.parseDouble( elems.getShip2Trabeldistance())-Double.parseDouble(expectResult.get(0).getShip2Trabeldistance().replace(",",".")))<1.0 ) {
                flag = true;


                break;
            }
        }

        assertTrue(flag);

    }
}