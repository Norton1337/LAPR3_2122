package lapr.project.controller;

import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.model.ShipPositionData.ShipPositonData;
import lapr.project.model.Ships.Ship;
import lapr.project.BSTFolder.AVL;
import lapr.project.BSTFolder.BST;

import java.util.ArrayList;
import java.util.List;

public class DataToBstController {

    private AVL<ShipAndData> shipBst;
    private List<ShipAndData> allData;

    public DataToBstController() {
        this.shipBst = new AVL<>();
        this.allData = new ArrayList<>();
    }

    public void transformBeforeBST(List<Ship> ships, List<ShipPositonData> shipsData){


        for(Ship elems : ships){
            List<ShipPositonData> ShipPositonDataList = new ArrayList<>();
            for(ShipPositonData elemsPos : shipsData){
                if(elems.getId().equals(elemsPos.getShipId())){
                    ShipPositonDataList.add(elemsPos);
                }
            }

            this.allData.add(new ShipAndData(elems, ShipPositonDataList));
        }

        //printList(allData);
    }

    public void populateBST(){

        for(ShipAndData elems : this.allData){
            shipBst.insert(elems);
        }

    }

    public BST getShipBst() {
        return shipBst;
    }


    public void getShipAndDataByMMSI(String mmsi){

        ShipAndData shipAndData = null;

        for(ShipAndData elems : this.allData){
            if(elems.getShip().getMMSI().equals(mmsi)){
                shipAndData = elems;
            }
        }

        System.out.println(this.shipBst.find(shipAndData).getElement());
    }


}
