package lapr.project.controller;

import lapr.project.data.bst_files.AVL;
import lapr.project.model.helper_classes.ShipAndData;
import lapr.project.model.ship_position_data.ShipPositonData;
import lapr.project.model.ships.Ship;

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
            List<ShipPositonData> shipPositonDataList = new ArrayList<>();
            for(ShipPositonData elemsPos : shipsData){
                if(elems.getId().equals(elemsPos.getShipId())){
                    shipPositonDataList.add(elemsPos);
                }
            }

            //FASTER SPEED?
            orderedByTime(shipPositonDataList);

            this.allData.add(new ShipAndData(elems, shipPositonDataList));
        }

    }

    public void populateBST(){

        for(ShipAndData elems : this.allData){
            shipBst.insert(elems);
        }

    }

    public List<ShipAndData> getAllData() {
        return allData;
    }

    public AVL<ShipAndData> getShipBst() {
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

        ShipAndData shipAndData;

        if (data.length() == 9){

            shipAndData = getShipAndDataByMMSI(data);
        }
        else if (data.length() == 10){

            shipAndData = getShipAndDataByIMO(data);
        }

        else{

            shipAndData = getShipDataByCallSign(data);
        }
        return shipAndData;
    }



}
