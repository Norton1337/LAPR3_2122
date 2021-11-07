package lapr.project.data.mocks;

import lapr.project.model.ShipPositionData.IDB.IShipPositionDataDB;
import lapr.project.model.ShipPositionData.ShipPositonData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ShipPositionDataDBMock implements IShipPositionDataDB {

    List<ShipPositonData> shipPositonData = new LinkedList<>();

    @Override
    public List<ShipPositonData> getShipData() {
        return new LinkedList<>(shipPositonData);
    }

    @Override
    public boolean addShipData(ShipPositonData shipData) {
        int availableShipPositionId = getAvailableShipPositionId();
        shipData.setId(availableShipPositionId);
        return shipPositonData.add(shipData);
    }

    @Override
    public boolean removeDataFromShip(ShipPositonData shipData) {
        return shipPositonData.remove(shipData);
    }

    @Override
    public Integer getAvailableShipPositionId() {
        List<Integer> idList = new ArrayList<>();
        List<ShipPositonData> shipPositonDataList = getShipData();

        if(shipPositonDataList.isEmpty()){
            return 0;
        }

        for(ShipPositonData elems : shipPositonDataList){
            idList.add(elems.getId());
        }

        return Collections.max(idList)+1;
    }

    @Override
    public List<ShipPositonData> getAllPositionDataFromShip(int shipID) {
        List<ShipPositonData> positonData = new ArrayList<>();

        for(ShipPositonData elems : shipPositonData){
            if(elems.getShipId() == shipID){
                positonData.add(elems);
            }
        }

        if(positonData.isEmpty()){
            return new ArrayList<>();
        }

        return positonData;
    }
}
