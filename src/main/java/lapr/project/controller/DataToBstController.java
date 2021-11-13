package lapr.project.controller;

import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.model.ShipPositionData.ShipPositonData;
import lapr.project.model.Ships.Ship;
import lapr.project.utils.BSTFiles.AVL;
import lapr.project.utils.BSTFiles.BST;

import java.util.ArrayList;
import java.util.List;

import static lapr.project.utils.Utils.orderedByTime;

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

            //FASTER SPEED?
            orderedByTime(ShipPositonDataList);

            this.allData.add(new ShipAndData(elems, ShipPositonDataList));
        }

        //printList(allData);
    }

    public void populateBST(){

        for(ShipAndData elems : this.allData){
            shipBst.insert(elems);
        }

    }

    public List<ShipAndData> getAllData() {
        return allData;
    }

    public BST getShipBst() {
        return shipBst;
    }

    /**
     * Receives MMSI code, searches and identifies
     * the ship whose MMSI is equal to the one inserted
     * @param mmsi unique number to identify each ship
     * @return the Ship corresponding to the inserted MMSI
     */
    public ShipAndData getShipAndDataByMMSI(String mmsi){

        ShipAndData shipAndData = null;

        for(ShipAndData elems : this.allData){
            if(elems.getShip().getMMSI().equals(mmsi)){
                shipAndData = elems;
            }
        }

        return this.shipBst.find(shipAndData).getElement();
    }

    /**
     * Receives IMO number, searches and identifies
     * the ship whose IMO is equal to the one inserted
     * @param imo unique number of international identification
     * for each ship
     * @return the Ship corresponding to the inserted IMO
     */
    public ShipAndData getShipAndDataByIMO(String imo){

        ShipAndData shipAndData = null;
        for (ShipAndData elems : this.allData){
            if (elems.getShip().getIMO().equals(imo)){
                shipAndData = elems;
            }
        }
        return this.shipBst.find(shipAndData).getElement();
    }

    /**
     * Receives a Call Sign, searches and identifies
     * the ship whose IMO is equal to the one inserted
     * @param callSign ship's unique call sign
     * @return the Ship corresponding to the inserted Call Sign
     */
    public ShipAndData getShipDataByCallSign(String callSign){

        ShipAndData shipAndData = null;

        for (ShipAndData elems : this.allData){
            if (elems.getShip().getCallSign().equals(callSign)){
                shipAndData = elems;
            }
        }
        return this.shipBst.find(shipAndData).getElement();
    }


    /**
     * Receives one of the three unique codes, MMSI,
     * IMO or Call Sign. Verifies witch one of this three
     * fields was inserted and identifies the ship that
     * belongs to that field
     * @param data corresponds to the data inserted, could
     * be MMSI, IMO or Call Sign
     * @return the Ship corresponding to the inserted data
     */
    public ShipAndData getShipDetails(String data){

        ShipAndData shipAndData = null;

        if (data.length() == 9){

            shipAndData = getShipAndDataByMMSI(data);
        }
        if (data.length() == 10){

            shipAndData = getShipAndDataByIMO(data);
        }
        if (data.length() == 5){

            shipAndData = getShipDataByCallSign(data);
        }
        return shipAndData;
    }



}
