package lapr.project.controller;

import lapr.project.controller.model_controllers.GeneratorController;
import lapr.project.controller.model_controllers.ShipController;
import lapr.project.controller.model_controllers.ShipPositionDataController;
import lapr.project.controller.model_controllers.VehiclesController;
import lapr.project.data.mocks.*;
import lapr.project.dtos.ShipPairsDTO;
import lapr.project.dtos.ShipsMovementDto;
import lapr.project.model.helper_classes.ShipAndData;
import lapr.project.ui.ShipUI;
import lapr.project.utils.Utils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static lapr.project.utils.Utils.readFromProp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListAllShipsInfoControllerTest {


    //DB
    VehiclesDBMock vehiclesDBMock;
    ShipDBMock shipDBMock;
    TrucksDBMock trucksDBMock;
    GeneratorDBMock generatorDBMock;
    ShipPositionDataDBMock shipPositionDataDBMock;

    //CONTROLLERS
    VehiclesController vehiclesController;
    ShipController shipController;
    ShipPositionDataController shipPositionDataController;
    GeneratorController generatorController;
    DataToBstController dataToBstController;
    ShipUI shipUI;
    ListAllShipsInfoController listAllShipsInfoController;
    MostTravelledShipsController mostTravelledShips;


    public ListAllShipsInfoControllerTest() {
        this.vehiclesDBMock = new VehiclesDBMock();
        this.shipDBMock = new ShipDBMock();
        this.trucksDBMock = new TrucksDBMock();
        this.generatorDBMock = new GeneratorDBMock();
        this.shipPositionDataDBMock = new ShipPositionDataDBMock();
        this.vehiclesController = new VehiclesController(vehiclesDBMock, shipDBMock, trucksDBMock);
        this.shipController = new ShipController(shipDBMock, generatorDBMock);
        this.shipPositionDataController = new ShipPositionDataController(shipDBMock, shipPositionDataDBMock);
        this.generatorController = new GeneratorController(shipDBMock, generatorDBMock);
        this.dataToBstController = new DataToBstController();
        this.shipUI = new ShipUI(shipController, shipPositionDataController, generatorController, vehiclesController);
        this.listAllShipsInfoController  = new ListAllShipsInfoController();
        this.mostTravelledShips = new MostTravelledShipsController();


        this.shipUI.importShips("Docs/Input/bships.csv");
        this.dataToBstController.transformBeforeBST(vehiclesController.getAllShips(), shipPositionDataController.getShipData());
        this.dataToBstController.populateBST();
    }


    @Test
    void shipLogTest() {
        List<ShipAndData> andDataList = new ArrayList<>();

        for(Object elems : dataToBstController.getShipBst().inOrder()){
            andDataList.add((ShipAndData) elems);
        }




        List<ShipsMovementDto> allData = listAllShipsInfoController.shipLog(andDataList);
        if(Objects.equals(readFromProp("debug", "src/main/resources/application.properties"), "1"))Utils.printList(allData);
        assertTrue(allData.size() > 10);

        for(ShipsMovementDto elems : allData){
            ShipAndData shipAndData = dataToBstController.getShipDetails(elems.getMMSI());
            double travelDist = mostTravelledShips.getTotalPerShip(shipAndData.getShipPositonData());

            assertEquals(elems.getMMSI(), shipAndData.getShip().getMMSI());
            assertEquals(elems.getTravelledDistance(), String.valueOf(travelDist));
        }
   }


    @Test
    void pairShips() {

        List<ShipAndData> andDataList = new ArrayList<>();

        for(Object elems : dataToBstController.getShipBst().inOrder()){
            andDataList.add((ShipAndData) elems);
        }
        System.out.println(andDataList.size());

        if(Objects.equals(readFromProp("debug", "src/main/resources/application.properties"), "1"))System.out.println("Ship1MMSI    Ship2MMSI      Movs      TravelDist  Movs     TravelDist");
        List<ShipPairsDTO> pairShips = listAllShipsInfoController.pairShips(andDataList);
        if(Objects.equals(readFromProp("debug", "src/main/resources/application.properties"), "1"))Utils.printList(pairShips);

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