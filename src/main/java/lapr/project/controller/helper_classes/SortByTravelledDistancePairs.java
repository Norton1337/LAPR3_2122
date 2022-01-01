package lapr.project.controller.helper_classes;

import lapr.project.dtos.ShipPairsDTO;

import java.util.Comparator;

public class SortByTravelledDistancePairs implements Comparator<ShipPairsDTO> {

    @Override
    public int compare(ShipPairsDTO o1, ShipPairsDTO o2) {
        double ship1Diff = Math.abs(Double.parseDouble(o1.getShip1Traveldistance()) - Double.parseDouble(o1.getShip2Trabeldistance()));
        double ship2Diff = Math.abs(Double.parseDouble(o2.getShip1Traveldistance()) - Double.parseDouble(o2.getShip2Trabeldistance()));
        return String.valueOf(ship2Diff).compareTo(String.valueOf(ship1Diff));
    }

}





