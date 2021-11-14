package lapr.project.model.HelperClasses;

import lapr.project.model.ShipPositionData.ShipPositonData;
import lapr.project.model.Ships.Ship;

import java.util.ArrayList;
import java.util.List;

public class ShipAndData implements Comparable<ShipAndData>{

    private Ship ship;
    private List<ShipPositonData> shipPositonData;

    public ShipAndData(Ship ship, List<ShipPositonData> shipPositonData) {
        this.ship = ship;
        this.shipPositonData = shipPositonData;
    }

    public ShipAndData() {
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public List<ShipPositonData> getShipPositonData() {
        return new ArrayList<>(shipPositonData);
    }

    public void setShipPositonData(List<ShipPositonData> shipPositonData) {
        this.shipPositonData = shipPositonData;
    }



    @Override
    public String toString() {
        return "ShipAndData{" +
                "ship=" + ship +
                 '}' + '\n';
    }






    @Override
    public int compareTo(ShipAndData obj) {
        return obj.getShip().getMMSI().compareTo(this.ship.getMMSI());
    }

}
