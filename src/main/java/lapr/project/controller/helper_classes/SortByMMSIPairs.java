package lapr.project.controller.helper_classes;

import java.util.Comparator;

import lapr.project.dtos.ShipPairsDTO;

public class SortByMMSIPairs implements Comparator<ShipPairsDTO> {

    @Override
    public int compare(ShipPairsDTO o1, ShipPairsDTO o2) {
        return o1.getShip1MMSI().compareTo(o2.getShip2MMSI());
    }
}
