package lapr.project.data.mocks;

import lapr.project.model.ShipPositionData.IDB.IShipPositionDataDB;
import lapr.project.model.ShipPositionData.ShipPositonData;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.randomUUID;

public class ShipPositionDataDBMock implements IShipPositionDataDB {

    List<ShipPositonData> shipPositonData = new LinkedList<>();

    @Override
    public List<ShipPositonData> getShipData() {
        return new LinkedList<>(shipPositonData);
    }

    @Override
    public boolean addShipData(ShipPositonData shipData) {
        shipData.setId(randomUUID());
        return shipPositonData.add(shipData);
    }

    @Override
    public boolean removeDataFromShip(ShipPositonData shipData) {
        return shipPositonData.remove(shipData);
    }


    @Override
    public List<ShipPositonData> getAllPositionDataFromShip(int shipID) {
        List<ShipPositonData> positonData = new ArrayList<>();

        for(ShipPositonData elems : shipPositonData){
            if(elems.getShipId().equals(shipID)){
                positonData.add(elems);
            }
        }

        if(positonData.isEmpty()){
            return new ArrayList<>();
        }

        return positonData;
    }
}
