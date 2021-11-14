package lapr.project.controller;

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

import java.util.ArrayList;
import java.util.List;

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
    List<ShipAndData> andDataList = new ArrayList<>();




    @BeforeEach
    void beforeAll() {
        shipUI.importShips("Docs/Input/bships.csv");



    }


    @Test
    void shipLogTest(){
        dataToBstController.transformBeforeBST(shipController.getAllShips(), shipPositionDataController.getShipData());
        dataToBstController.populateBST();

        List<ShipAndData> andDataList = new ArrayList<>();

        for(Object elems : dataToBstController.getShipBst().inOrder()){
            andDataList.add((ShipAndData) elems);
        }




        List<ShipsMovementDto> allData = listAllShipsInfoController.shipLog(andDataList);
        Utils.printList(allData);
        assertTrue(allData.size() > 10);

        for(ShipsMovementDto elems : allData){
            ShipAndData shipAndData = dataToBstController.getShipDetails(elems.getMMSI());
            double travelDist = mostTravelledShips.getTotalPerShip(shipAndData.getShipPositonData());

            assertEquals(elems.getMMSI(), shipAndData.getShip().getMMSI());
            assertEquals(elems.getTravelledDistance(), String.valueOf(travelDist));
        }
   }









}