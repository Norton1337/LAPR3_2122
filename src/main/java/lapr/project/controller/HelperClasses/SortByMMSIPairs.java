package lapr.project.controller.HelperClasses;

import lapr.project.Dtos.ShipPairsDTO;

import java.util.Comparator;

public class SortByMMSIPairs implements Comparator<ShipPairsDTO> {

    @Override
    public int compare(ShipPairsDTO o1, ShipPairsDTO o2) {
        return o1.getShip1MMSI().compareTo(o2.getShip2MMSI());
    }
}
