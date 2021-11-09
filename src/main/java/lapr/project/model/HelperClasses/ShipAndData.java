package lapr.project.model.HelperClasses;

import lapr.project.model.ShipPositionData.ShipPositonData;
import lapr.project.model.Ships.Ship;
import lapr.project.utils.BST;

import java.util.List;

import static lapr.project.utils.Utils.ToInt;

public class ShipAndData implements Comparable<ShipAndData>{

    private Ship ship;
    private List<ShipPositonData> shipPositonData;

    public ShipAndData(Ship ship, List<ShipPositonData> shipPositonData) {
        this.ship = ship;
        this.shipPositonData = shipPositonData;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public List<ShipPositonData> getShipPositonData() {
        return shipPositonData;
    }

    public void setShipPositonData(List<ShipPositonData> shipPositonData) {
        this.shipPositonData = shipPositonData;
    }


        /*
    @Override
    public String toString() {
        return "ShipAndData{" +
                "ship=" + ship +
                ", shipPositonData=" + shipPositonData + '}' + '\n';
    }

         */




    @Override
    public int compareTo(ShipAndData obj) {
        return obj.getShip().getMMSI().compareTo(this.ship.getMMSI());
    }

}
